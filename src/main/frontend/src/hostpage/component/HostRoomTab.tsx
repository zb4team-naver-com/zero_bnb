import { useQueryClient } from "@tanstack/react-query"
import { useNavigate } from "react-router-dom"
import styled from "styled-components"
import { Room } from "../api/room_axios"
import Button from "../common/Button"
import { roomInitValues } from "../dataForm/roomRegister"

export default function HostRoomTab() {
  const query = useQueryClient()
  const getDatas = query.getQueryData(['@room']) 

  const renderValues = (getDatas? getDatas : roomInitValues) as Room[]
  const keys = [ 'a', 'b', 'c', 'd', 'e']
  //api 로직
  //정보받아와서 map으로 돌려야함
  return(
    <S.Layout>
      <S.H2>등록한 숙소</S.H2>
      <S.Ul>
        {renderValues.map((list, idx) => <RoomListLayout key={keys[idx]} renderValues={list}/>)}
      </S.Ul>
    </S.Layout>
  ) 
}

const RoomListLayout = (props: {renderValues: Room}) => {
  const { accommodationImageInputs, description, name } = props.renderValues
  const nav = useNavigate()
  return (
    <S.Li onClick={() => nav('/host/roommanage')}>
      <S.Div>
        <S.Img src={accommodationImageInputs![0].url} alt='숙소대표이미지' />
        <div>
          <S.Name>{name}</S.Name>
          <S.DesDiv>{description}</S.DesDiv>
          <S.Button type="button" onClick={() => nav("/host/roommange")}>자세히 보기</S.Button>
        </div>
      </S.Div>
    </S.Li>
  )
}
     
//style

const S: any = {}

S.Layout = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 4rem;
`
S.H2 = styled.h2`
  font-size: 2.2rem;
  font-weight: 500;
  color: var(--color-gray4);
`
S.Ul = styled.ul`
  display: flex;
  justify-content: center;
  align-items: center;
  align-content: center;
  flex-wrap: wrap;
  width: 90rem;
  margin-top: 3rem;
`
S.Li = styled.li`
  width: 60rem;
  height: 18rem;
  margin-right: 2rem;
  margin-left: 2rem;
  margin-bottom: 3rem;
  border:1px solid var(--color-gray0);
  border-radius: 2.5rem;
  padding: 2rem 3rem;
  &:hover,
  &:focus {
    box-shadow: 1px 1px 2px var(--color-gray0);
  }
`

S.Div = styled.div`
  display: flex;
  align-items: start;
`

S.Img = styled.img`
  display: block;
  width: 20rem;
  height: 14rem;
  margin-right: 3rem;
  border-radius: 1rem;
  background: var(--color-gray0);
`
S.Name = styled.div`
  font-size: 2rem;
  margin-bottom: 2rem;
`
S.DesDiv = styled.div`
  width: 30rem;
  height: 7rem;
  font-size: 1.6rem;
  overflow-x: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  &::-webkit-scrollbar {
    display: none;
  }
`
S.Button = styled.button`
  width: 7.7rem;
  height: 2.8rem;
  margin-left: 22rem;
  background: var(--color-white);
  border: none;
  font-size: 1.2rem;
  font-weight: 700;
  color: var(--main-color2-0);
  border-bottom: 1px solid var(--main-color2-0);
  &:hover,
  &:focus {
    color: var(--main-color2);
    border-bottom: 1px solid var(--main-color2);
  }
`


