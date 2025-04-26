package org.com.spectorassignment.exception.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.com.spectorassignment.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ServiceExceptionCode implements ErrorCode {

    ALREADY_EXIST_MEMBER(HttpStatus.BAD_REQUEST,"ALREADY_EXIST_MEMBER", "이미 가입된 이메일 입니다. email : [%s]"),
    NOT_REGISTRATION_MEMBER(HttpStatus.BAD_REQUEST,"NOT_REGISTRATION_MEMBER", "이미 가입된 이메일이 없습니다. email : [%s]"),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST,"NOT_MATCH_PASSWORD", "비밀번호가 잘못되었습니다."),
    INVALID_ROLE_TYPE(HttpStatus.BAD_REQUEST, "INVALID_ROLE_TYPE", "manager와 user외에는 들어갈수가 없습니다."),
    BAD_REQUEST_BODY(HttpStatus.BAD_REQUEST, "BAD_REQUEST_BODY", "유효성검사에 실패하였습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR","서버 에러입니다"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "ACCESS_DENIED","접근 권한이 없습니다."),
    ANSWER_YES_NO(HttpStatus.BAD_REQUEST, "ANSWER_YES_NO","이 질문은 YES/NO로만 답할수 있습니다."),
    ANSWER_ALREADY_EXISTS(HttpStatus.CONFLICT, "ANSWER_ALREADY_EXISTS", "이미 응답한 질문입니다. %s"),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "INVALID_JWT", "유효하지 않은 토큰입니다."),
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "EXPIRED_JWT", "토큰이 만료되었습니다."),
    NOT_FOUND_QUESTION(HttpStatus.NOT_FOUND, "NOT_FOUND_QUESTION", "유효하지 않은 토큰입니다."),
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "QUESTION_NOT_FOUND" , "등록된 질문이 없습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return this.code;
    }

}
