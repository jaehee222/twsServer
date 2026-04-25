# 🏆 Today Watching Live

**Spring-React Native 기반 스포츠 직관 기록 어플리케이션**

## 📖 프로젝트 소개

내가 사랑하는 팀의 경기, 그 열정과 감동을 일기로 남기다.  
경기장에서 느꼈던 환희와 아쉬움을 기록하고,  
팀을 향한 응원의 순간들을 한곳에 담는 특별한 일기 어플리케이션입니다.

---

## ⏳ 개발 기간

- **2024.03.17 ~ 2024.09.01**

---

## 👥 멤버 구성

- **공통 작업**: 서비스 화면 설계, 기능 설계, 통합 및 형상 관리
- **<a href="https://github.com/jaehee222)">이재희</a>**: 게시글(Ticket) CRUD, 스프링 서버 초기 구축, 로그인, 내 정보 관리
- **<a href="https://github.com/prohwww">전혜원</a>**: 응원하는 팀(MyTeam) 기능, DB 구축, 회원가입, 비밀번호 변경, 캘린더 기능

---

## 💻 개발 환경

- **언어**: JAVA (17.0.7)
- **백엔드**: Spring Framework
- **프론트엔드**: React Native (react-native-cli 2.0.1)
- **IDE**: Visual Studio Code, IntelliJ
- **Database**: MySQL
- **ORM**: Hibernate, JPA (Java Persistence API)

---

## 🔑 주요 기능

### 1. 로그인

<img src="https://github.com/user-attachments/assets/196b9972-1e23-4f23-a615-94c2780fd071" width="200" height="300" />

- 사용자가 입력한 **아이디와 비밀번호**를 DB에서 확인하여 일치하는 경우 로그인 성공.
- 로그인 성공 시 **세션에 사용자 아이디 저장**.

### 2. 회원가입

<img src="https://github.com/user-attachments/assets/3c3c2664-ac45-4b4e-997b-143ffa19a625" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/2b7b6636-47da-4e00-bc31-afba29467e44" width="200" height="300" />

- 입력된 정보를 DB에서 검증하여 **중복된 아이디**가 없을 경우, 새로운 사용자 정보 등록.

### 3. 티켓 관리 (CRUD)
<img src="https://github.com/user-attachments/assets/d85dcc6d-dbfe-412c-96f5-5900de742281" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/f79739a1-ba7b-48c8-94ab-984b4416c5a5" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/81821e6f-2969-400b-ac64-1ed3726fbc7e" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/36ddd52d-be3a-477a-9048-a7c2d055389d" width="200" height="300" />

- 사용자는 직관한 경기의 내용을 **기록, 수정, 삭제**할 수 있습니다.
- 응원하는 팀(MyTeam)과 연동하여 **경기 결과(승, 무, 패)**를 스티커로 표시합니다.

### 4. 캘린더 기능
<img src="https://github.com/user-attachments/assets/0f375e06-5f36-4f94-bba9-816532949628" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/e24350a1-ff60-402f-821e-782379ee8c67" width="200" height="300" />

- 월별로 기록된 직관 일정을 **캘린더 화면**에서 조회 가능.
- 특정 날짜를 클릭하여 그날의 직관 기록에 쉽게 접근할 수 있습니다.

### 5. 내 정보 관리
<img src="https://github.com/user-attachments/assets/109c0e06-330b-4199-a8ce-c7eb77843e57" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/38165eb2-ca7b-4240-9d0c-9b0f47bd51f0" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/dfa7c872-4836-4212-aea4-d54eb9037faf" width="200" height="300" />

- 사용자는 **닉네임**과 **비밀번호**를 변경할 수 있습니다.
- 닉네임 변경 시 **중복 여부**를 확인하여 중복되지 않은 경우에만 변경 가능합니다.

### 6. 응원하는 팀 관리
<img src="https://github.com/user-attachments/assets/6c96de0e-f9e1-4701-a259-4725a670f31b" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/1974fd8d-3e78-4184-b619-21f585a6e143" width="200" height="300" />
<img src="https://github.com/user-attachments/assets/169bb730-b43f-478e-af6b-4d86dd407c90" width="200" height="300" />

- 사용자는 응원하는 팀을 **추가, 삭제**할 수 있으며, 축구, 야구, 농구, 배구 팀을 지원합니다.
- 응원하는 팀의 **승률 및 통계 정보**를 제공합니다.

---

## 📂 프로젝트 구조 및 주요 기능

```plaintext
├── backend/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ ├── com/example/project/
│ │ │ │ │ ├── controller/ # API 요청 처리 (TicketController, UserController, MyTeamController…)
│ │ │ │ │ ├── dto/ # 데이터 전송 객체 (UserDto, TicketDto…)
│ │ │ │ │ ├── entity/ # 데이터베이스 엔티티 클래스 (UserEntity, TicketEntity, MyTeamEntity…)
│ │ │ │ │ ├── exception/ # 예외 처리 (ValidationException, GlobalExceptionHandler)
│ │ │ │ │ ├── repository/ # DB 연동 (TicketRepository, UserRepository…)
│ │ │ │ │ ├── service/ # 비즈니스 로직 (TicketService, UserService…)
│ │ │ └── resources/
│ │ │ └── application.yml # 데이터베이스 및 환경 설정
│ └── build.gradle # Gradle 빌드 스크립트 (종속성 및 빌드 설정)
│
├── frontend/
│ ├── src/
│ │ ├── LoginScreen.js # 사용자 로그인 화면
│ │ ├── TicketScreen.js # 직관 기록 관리 화면
│ │ ├── CalendarScreen.js # 직관 기록 캘린더 화면
│ │ ├── … # 이외 기능 화면
│ │ ├── map.js # 공통 UI 이미지 및 매핑 변수 정의
│ │ ├── style.js # UI 컴포넌트 스타일 정의
│ │ ├── NewApp.js # 메인 애플리케이션 파일
│ └── package.json # 프론트엔드 종속성 및 스크립트 정의
```

### 1. Backend (Spring Boot) - [<img src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" width="20" height="20" alt="GitHub Repo"/> twsServer](https://github.com/jaehee222/twsServer)

- **Ticket (Controller, Service, Dto, Entity, Repository)**: 사용자가 직관한 경기 기록을 CRUD 방식으로 관리.
    - 경기 기록 생성, 수정, 삭제 기능 제공.
    - 세션을 통한 사용자 인증 처리 및 필수 데이터 검증.
    - 관련 클래스:
        - `TicketController`: 경기 기록 요청 처리, 세션 기반 사용자 인증 및 유효성 검사.
        - `TicketService`: 비즈니스 로직 구현, 티켓 생성/조회/삭제 기능 처리.
        - `TicketDTO`: 클라이언트와 서버 간 데이터 전송 객체.
        - `TicketEntity`: 데이터베이스와 매핑되는 엔티티 클래스.
        - `TicketRepository`: JPA를 통한 DB 연동.
          
- **User (Controller, Service, Dto, Entity, Repository)**: 사용자 인증 및 정보 관리.
    - 로그인, 회원가입, 닉네임/비밀번호 수정, 아이디/닉네임 중복 체크, 이메일 확인 등
    - 세션을 통해 로그인 상태를 관리하며, 회원가입 시 필수 정보 검증을 수행.
    - 관련 클래스:
        - `UserController`: 사용자 요청 처리, 아이디/닉네임 중복 체크, 로그인/로그아웃, 회원가입 등 API 제공.
        - `UserService`: 사용자 관련 비즈니스 로직 구현, 아이디/닉네임 중복 검사, 로그인 처리.
        - `UserDTO`: 클라이언트와 서버 간 데이터 전송 객체.
        - `User`: 데이터베이스 엔티티, 사용자 정보 (아이디, 비밀번호, 닉네임 등) 매핑.
        - `UserRepository`: JPA를 통한 DB 연동, 사용자 정보 저장 및 조회.
          
- **Team (Controller, Service, Dto, Entity, Repository)**: 스포츠 팀 정보 관리.  
    - 팀의 기본 정보 및 통계 데이터를 관리.
    - 특정 팀의 정보 조회 및 모든 팀 목록 조회 기능 제공.
    - 관련 클래스:
        - `TeamController`: 팀 정보 관리 요청 처리, 특정 팀의 정보 조회, 모든 팀 목록 조회 API 제공.
        - `TeamService`: 팀 관련 비즈니스 로직 구현, 팀 정보 조회 및 전체 팀 목록 관리.
        - `TeamDTO`: 클라이언트와 서버 간 데이터 전송 객체.
        - `Team`: 데이터베이스 엔티티, 팀 정보 (팀 번호, 팀 이름, 통계 데이터 등) 매핑.
        - `TeamRepository`: JPA를 통한 DB 연동, 팀 정보 저장 및 조회.
          
- **MyTeam (Controller, Service, Dto, Entity, Repository)**: 사용자가 응원하는 팀 관리.
    - 사용자가 선택한 팀 추가, 삭제 및 응원 팀의 승률 정보 조회 기능 제공.
    - 관련 클래스:
        - `MyTeamController`: 사용자가 응원하는 팀 목록 조회, 새로운 팀 추가, 팀 삭제 및 승률 정보 조회 API 제공.
        - `MyTeamService`: MyTeam 관련 비즈니스 로직 구현, 사용자가 선택한 팀을 추가/삭제하고 승률 정보 관리.
        - `MyTeamDTO`: 클라이언트와 서버 간 데이터 전송 객체.
        - `MyTeamEntity`: 데이터베이스 엔티티, 사용자와 팀의 매핑 정보 관리.
        - `MyTeamRepository`: JPA를 통한 DB 연동, 사용자가 응원하는 팀 정보를 저장 및 조회.  

## 2. Frontend (React Native) - [<img src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" width="20" height="20" alt="GitHub Repo"/> Today-sWatchingLive](https://github.com/prohwww/Today-sWatchingLive)

- **`login.js`**: 사용자가 로그인하는 화면으로, API를 통해 안전하게 인증을 처리합니다.
- **`main.js`**: 사용자가 직관한 경기 기록(티켓)을 한눈에 확인하고, 필요 시 기록을 삭제할 수 있는 메인 화면입니다.
- **`addTicket.js`**: 새로운 직관 기록을 작성하는 화면으로, 경기 결과와 팀 매칭을 통해 상세한 티켓을 생성합니다.
- **`calendar.js`**: 월별로 직관 기록을 확인할 수 있는 캘린더 화면입니다. 특정 날짜의 경기를 쉽게 찾아보세요.
- **`signin.js`**: 신규 사용자를 위한 회원가입 화면입니다. 이메일, 비밀번호, 닉네임을 입력하고 직관 기록을 시작하세요.
- **`findPw.js`**: 비밀번호를 잊어버렸을 때 사용되는 비밀번호 찾기 화면입니다. 이메일을 통해 안전하게 비밀번호를 재설정할 수 있습니다.
- **`start.js`**: 앱을 처음 실행할 때 표시되는 초기 로딩 화면입니다.
- **`myInfo.js`**: 로그인된 사용자의 기본 정보를 확인할 수 있는 화면입니다.
- **`infoSetting.js`**: 사용자 정보 수정 화면으로, 닉네임 및 비밀번호 변경을 지원합니다.
- **`teamList.js`**: 사용자가 응원하는 팀을 조회하고, 새로운 팀을 추가할 수 있는 화면입니다. 축구, 야구, 농구 등 다양한 종목을 지원합니다.
- **`teamAnalysis.js`**: 응원하는 팀의 경기 승률을 분석하여 통계로 보여주는 화면입니다.


### 3. Database (MySQL)

- **`user`**: 사용자 닉네임, 비밀번호, 이메일 등 계정 정보를 저장하고, 직관 기록과 연결합니다.
- **`ticket`**: 사용자가 직관한 경기 기록을 저장하며, 경기 일자, 점수, 팀과의 연동 정보를 포함합니다.
- **`my_team`**: 사용자가 응원하는 팀 정보를 저장하며, 다수의 팀을 추가 및 관리할 수 있습니다.
- **`team`**: KBO, K리그, KOVO, KBL 등 앱에서 제공하는 모든 스포츠 팀 정보를 저장합니다.

---
