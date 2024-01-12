# 📝 익명 게시판 프로젝트
![image](https://github.com/ejeonghun/anonymous_board/assets/41509711/3e6a6f58-7587-4587-951e-f470e8572c78)

### 📖Description
익명으로 게시판에 게시글을 작성할 수 있는 백앤드, 프론트앤드 프로젝트

---

### 🔧Stack
<img src="https://img.shields.io/badge/Java-JDK%2017-blue?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/Spring%20Data%20JPA-latest-orange?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/Swagger-v3-brightgreen?style=for-the-badge&logo=swagger&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-8.0.26-blue?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/React-18.2.0-blue?style=for-the-badge&logo=react&logoColor=white">
<img src="https://img.shields.io/badge/Axios-1.6.5-brightgreen?style=for-the-badge&logo=axios&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ%20IDEA-2022.2-blue?style=for-the-badge&logo=intellij-idea&logoColor=white">

---

### ⭐기능
#### 게시판 CRUD
- 게시판 쓰기 : 제목, 본문, 비밀번호
- 게시글 목록 : 글번호, 제목, 작성 시간 최신순, 페이징 없이 한번에 전체 리스트 보여주기
- 게시글 수정/삭제 : 비밀번호를 반드시 입력해서 수정/삭제
#### 댓글 CRUD
- 댓글 쓰기 : 내용, 비밀번호
- 댓글 목록 : 게시글 하단에 최신순으로 출력
- 댓글 삭제 : 비밀번호 확인 후 삭제


익명으로 게시판을 작성하고 비밀번호로 CRUD를 가능하게 한다. 게시글 밑으로 댓글 (작성, 삭제) 기능을 구현하였다.
Springboot로 API 서버 구축, React로 클라이언트에게 View를 제공

### API 명세서
![image](https://github.com/ejeonghun/anonymous_board/assets/41509711/ff077518-2c75-4488-9301-41f81e0bda52)
#### [Swagger UI](http://localhost:8080/swagger-ui/index.html#/)

---

### ERD
![image](https://github.com/ejeonghun/anonymous_board/assets/41509711/9f22eb5c-e385-45d3-aa61-805a5b8b4bb5)

#### DB Init
```
CREATE DATABASE board;
CREATE USER 'application_user'@'%' IDENTIFIED BY 'application';
GRANT ALL PRIVILEGES ON board.* TO 'application_user'@'%';
FLUSH PRIVILEGES;
```
