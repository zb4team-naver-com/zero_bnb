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
## Main Components

- host에 저장된 숙소데이터를 불러와 출력
- /acommodation/info/id를 response함

## SignUp Components

- email, password, name, birth, phone value값을 입력 및 저장함
- Mysql DB상에 guest라는 테이블에 저장이 됨
- 해당 클라이언트에서는 /signup 으로 response함

## Login Components

- email, password value값을 입력
- 해당 클라이언트에서는 /login 으로 response함
- 로그인과 동시에 DB에서 보내준 accesstoken을 받아와 localhost상에 key, value가 저장
- LogoutComponents를 사용하면 localhost상에 token이 제거
---
## 사용한 프레임워크 및 라이브러리

### React-Query
- 전역상태를 보다 원활하게 사용하고자 사용
- 서버 데이터와 클라이언트에서 사용하는 state를 분리하기 위함
---
## 문제점

- axios instance하는 과정에서 서버가 실행이 안됨 이 문제점은 서버를 맡고 계신 backend분과 협업을 통해 진행을 해봐야겠습니다. (Error 404)
  - API연동은 확인 되었으나 8000과의 링크가 제대로 되있지 않았음을 확인했음
  - 이 후 mysqlDB서버와 연동을 진행하여 로그인, 회원가입 등 API가 연동에 성공함
