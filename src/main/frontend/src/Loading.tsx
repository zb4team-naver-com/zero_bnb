import styled, { keyframes } from "styled-components"

export default function Loading() {
  return (
    <>
      <S.Div>
        <S.Img src={"https://static-storychat.pstatic.net/2020/4/15/5/1934655_f7bf2k08ih200.jpg?type=rsc5"} alt="로딩이미지" />
        <S.Text>loading.....</S.Text>
        <div>
          <S.Span id="first"></S.Span>
          <S.Span id="second"></S.Span>
          <S.Span id="third"></S.Span>
        </div>
      </S.Div>
    </>
  )
}

const S: any = {}

S.Div = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
`
S.Img = styled.img`
  width: 10rem;
  height: 10rem;
  border-radius: 50%;
  margin-bottom: 1rem;
`
S.Text = styled.div`
  font-size: 1.4rem;
  text-align: center;
  margin-bottom: 1rem;
`

const firstCricle = keyframes`
  35% {
    transform: scale(0.8);
    background: gray;
  }

  70% {
    transform: scale(1);
    background: white;
  }
`
const secondCircle = keyframes`
  70% {
    transform: scale(0.8);
    background: gray;
  }

  100% {
    transform: scale(1);
    background: white;
  }
`
const thirdCircle = keyframes`
  100% {
    transform: scale(0.8);
    background: gray;
  }
`
S.Span = styled.span`
  display: inline-block;
  width: 0.8rem;
  height: 0.8rem;
  margin-right: 0.5rem;
  border-radius: 50%;
  border: 1px solid gray;
  animation: ${(props) => {
   switch(props.id) {
    case "first":
      return firstCricle
    case "second":
      return secondCircle
    case "third":
      return thirdCircle
   }}} 1.8s infinite ease-in-out ;
`