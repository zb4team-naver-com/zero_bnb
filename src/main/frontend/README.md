## Zerobnb 

- 제로베이스 backend + frontend 프로젝트
- 사용 기술: React, TypeScript, @tanstack/react-query, react-router-dom, axios, styled-components
---
## client 실행하기

현재 실행하는 폴더는 frontend 작업 폴더이고 상위 폴더에 backend 작업 환경 공간이 나눠져 있습니다.
```
cd src/main/frontend
npm i -D react react-router-dom @tanstack/react-query axios styled-components
npm start
```
---
## 컴포넌츠 구조
```
src
├─components  // 컴포넌트 단위로 구분된 폴더, 웹 화면에 보이는 모습
│  ├─auth
│  ├─common
│  ├─host
│  └─layout
├─hooks // 공통으로 사용되는 custom hook
├─pages // 페이지 단위로 구분된 뷰 모음
├─router
├─services // 도메인, 서버 및 레지스터에 관련된 코드
│  ├─AuthAPI 
│  └─HOSTAPI 
└─store
```

## 컴포넌츠에 대한 간단한 설명

### Header, HostTab
- 해당 컴포넌츠는 전역으로 관리하는 특정한 value가 있음
- Header는 로그인 즉, 토큰에 대한 정보가 담겨져 있음 (최상위)
- 로그인이 안되어있으면 [로그인 / 회원가입] 로그인이 되어있으면 [로그아웃 / 내정보]에 대한 컴포넌츠가 출력
- HostTab은 로그인 이후 Host에 대한 정보가 담겨져 있음 (차상위)

### Main Components
- host에 저장된 숙소데이터를 불러와 출력
- /acommodation/info/id를 response함

### SignUp Components
- email, password, name, birth, phone value값을 입력 및 저장함
- Mysql DB상에 guest라는 테이블에 저장이 됨
- 해당 클라이언트에서는 /signup 으로 response함

### Login Components
- email, password value값을 입력
- 해당 클라이언트에서는 /login 으로 response함
- 로그인과 동시에 DB에서 보내준 accesstoken을 받아와 localhost상에 key, value가 저장
- LogoutComponents를 사용하면 localhost상에 token이 제거

### HostRoomTab Components
- DB에 저장된 value를 받아와서 출력
- 간략하게 해당 이미지, 제목, 설명을 카드 형태로 출력 / Maincomponents와 동일

### HostRegister Components
- 로그인이 된 상태에서 호스트로 전환 가능

### HostUpdate Components
- 로그인이 된 상태에서 나의 프로필 수정
- mysql DB에 저장된 나의 프로필 수정

### RoomRegister Components
- 호스트가 된 상태에서 숙소를 등록
- /accomodation에 대해 간단한 숙소 정보 등록
- /accomodation/info에 대해 상세숙소 정보 등록
- 해당 숙소 등록을 진행하면 HostRoomTab, Main components에 출력
---
## 사용한 프레임워크 및 라이브러리

### React-Query
- 전역상태를 보다 원활하게 사용하고자 사용
- 서버 데이터와 클라이언트에서 사용하는 state를 분리하기 위함

### Styled-components
- module을 이용하고자 했지만 해당 컴포넌츠 폴더가 많아져서 구조가 오히려 복잡해졌음
- 마크업이 있는 해당 파일에 적용하고 원하는 input, button, form 등 반복적으로 일어나는 구조에 대해 모듈화를 하여 작업이 용이해짐
---
## 문제점 및 해결방안

### Axios instance에 대해 response, request에 대한 명확한 이해점이 부족
- axios instance하는 과정에서 서버가 실행이 안됨 이 문제점은 서버를 맡고 계신 backend분과 협업을 통해 진행을 해봐야겠습니다. (Error 404)
  - API연동은 확인 되었으나 8000과의 링크가 제대로 되있지 않았음을 확인했음
  - 이 후 mysqlDB와 연동을 진행하여 로그인, 회원가입 등 API가 연동에 성공함
  - 로그인시에는 단순하게 request가 아닌 해당 토큰을 받아야 하기때문에 response도 적절하게 사용해야함
  - 나머지의 경우 DB에서 요청 받아와야해서 response를 사용함 단 host전환, 숙소등록을 진행하기 위해서는 request도 사용을 함
### Router에 구조
- Router가 링크가 연결이 끊켜서 연결이 되지 않았습니다.
  - Route path를 해당 파일의 명과 동일 시 하니깐 Router 링크가 다시 연결이 됨
  - 또한, Route 간의 net 구성을 바꿔주어 SPA구조로 만들었음
###

## 해결하지 못한 문제점

### 버튼을 클릭시 해당 페이지로 이동
- 해당 페이지로 이동시 심한 렉이 발생

### API연동
- Branch 단위로 작업을 진행하면 API가 연동이 되었지만 PR이 후 Merge가 되면 연동이 어려웠음
- Skeleton
