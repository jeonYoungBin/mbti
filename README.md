### ERD 설계
   - member(회원),question(질문),answer(답변) 테이블로 설계를 하였으며, answer 테이블은 각각 1 : N 구조로 가져갔습니다.
     
   ![스크린샷 2025-04-26 오후 12 10 41](https://github.com/user-attachments/assets/c7c760c8-865c-47c7-9d5b-cd73c9358009)



### 프로젝트 개요
- 이 프로젝트는 MBTI를 등록/조회하는 API 서버입니다.
  Spring Boot 기반으로 Spring Data JPA를 활용한 ORM, H2가 연동되어 있으며, RESTful API 스타일로 구성되어 있습니다.
- 테스트는 swagger를 이용하여 진행하였습니다.
  - swagger URL : http://localhost:8080/swagger-ui/index.html# 

### 디렉토리/패키지 구조




### 전체 아키텍처 구조
- config: security, swagger config를 설정하였습니다.
- controller: 클라이언트 요청을 받고 응답을 처리합니다.
- service: 비즈니스 로직을 담당하며 트랜잭션 단위로 처리됩니다.
- repository: Spring Data JPA 기반으로 DB에 접근합니다.
- domain : request, response dto를 정의 했습니다.
- entity : 각 테이블의 ORM을 정의 했습니다.
- exception : 예러 발생시 RestControllerAdvice를 활용하여 Exception Handling 합니다.
- initDb : 서비스가 올라가고 디비에 초기값을 넣어 줍니다.
- util : 암복호화, JWT 피싱등 공통으로 사용가능한 기능을 정의하였습니다. 

### 기술 및 도구 명세
   
| 항목    | 사용 기술                                   |
| ---------- | ------------------------------------ |
| 프레임워크 | Spring Boot 3.1.4                |
| 데이터베이스 기술 | Spring Data JPA (Hibernate)                |
| 문서화 | Swagger(OpenAPI 3.0)               |
| 사용 언어 | JAVA 17               |

### 실행 방법 및 테스트 과정
   1)  H2 DB는 1.4.200 버전으로 설치
      - url : jdbc:h2:tcp://localhost/~/test
   2) 최초 실행시
     - application.yml -> ddl-auto: create 설정
   3) 환경 변수 설정
     - JWTSECRET=
        
   4) 테스트 시나리오
      ##### 회원 가입
      
      ###### request

      ![스크린샷 2025-04-26 오후 12 39 19](https://github.com/user-attachments/assets/6f062c03-7e3f-4744-9606-19e06b93718e)

      ###### response

      ![스크린샷 2025-04-26 오후 12 38 22](https://github.com/user-attachments/assets/edaa35b0-5be7-466d-a37f-471cacbf5b58)

      ##### 로그인

      ###### request

      ![스크린샷 2025-04-26 오후 12 46 38](https://github.com/user-attachments/assets/7c54caa0-1326-45c4-b899-23232d2c0913)


      ###### response
      
      ![스크린샷 2025-04-26 오후 12 47 13](https://github.com/user-attachments/assets/c53f93e7-7a45-4812-8ab2-19546ad25052)

      ##### Authorize에 토큰 인입
      
      ![스크린샷 2025-04-26 오후 12 50 06](https://github.com/user-attachments/assets/c1ce4214-bda1-42f0-92de-11c3aa724e6a)

      ##### 질문 조회

      ###### response

      ![스크린샷 2025-04-26 오후 12 51 08](https://github.com/user-attachments/assets/478628fd-a5f1-411d-ac1c-4d06b3bb578a)

      ##### MBTI 질문 답변

      ###### request

      ![스크린샷 2025-04-26 오후 12 56 03](https://github.com/user-attachments/assets/40d074c5-ddaa-4ba9-ae59-8077e93b6fe0)

      ###### response

      ![스크린샷 2025-04-26 오후 1 01 00](https://github.com/user-attachments/assets/4e0ff01c-86f3-4c5a-b482-7e627d2b932c)

      ##### 회원 MBTI 질문 답변 조회(ROLE이 MANAGER인 회원으로 재로그인)

      ##### response

      ![스크린샷 2025-04-26 오후 1 05 51](https://github.com/user-attachments/assets/60afbfb0-707f-4bc9-b9b4-070df89a639f)





    

      
