package org.com.mbti.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.com.mbti.domain.response.ErrorResponse;
import org.com.mbti.exception.enumtype.ServiceExceptionCode;
import org.com.mbti.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        boolean tokenValidation = false;
        ErrorResponse errorResponse = null;
        try {
            if (token != null && jwtTokenUtil.validate(token)) {
                String email = jwtTokenUtil.getEmail(token);
                String role = jwtTokenUtil.getRole(token);

                // 인증 객체 생성
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        email, null, List.of(new SimpleGrantedAuthority("ROLE_" + role))
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e) {
            tokenValidation = true;
            ServiceExceptionCode errorCode = ServiceExceptionCode.EXPIRED_JWT;
            errorResponse = ErrorResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build();

        } catch (JwtException | IllegalArgumentException e) {
            tokenValidation = true;
            ServiceExceptionCode errorCode = ServiceExceptionCode.INVALID_JWT;
            errorResponse = ErrorResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build();
        } catch (Exception e) {
            throw e;
        } finally {
            if(tokenValidation) {
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getOutputStream().write(responseBytes(errorResponse));
                return;
            }

            filterChain.doFilter(request, response);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    private byte[] responseBytes(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String serialized = objectMapper.writeValueAsString(obj);
        return serialized.getBytes(StandardCharsets.UTF_8);
    }
}
