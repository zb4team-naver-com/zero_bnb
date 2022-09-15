## Zerobnb 
- 제로베이스 backend + frontend 프로젝트
- 사용 기술: React, TypeScript, @tanstack/react-query, react-router-dom, axios, styled-components

---

## client 실행하기
```
cd src/main/frontend
npm i -D react react-router-dom @tanstack/react-query axios styled-components
npm start
```

---

## SignUp Components
- email, password, name, birth, phone value값을 입력 및 저장함
- 우선 확인을 위하여 email, password값을 react-query를 이용하여 데이터를 보냄

---

## 문제점
- axios instance하는 과정에서 server가 실행이 안됨 이 문제점은 server를 맡고 계신 backend분들과 협업을 통해 진행을 해봐야겠습니다. (Error 404)
