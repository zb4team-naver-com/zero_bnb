import { useNavigate } from "react-router-dom"
import styled from "styled-components"
import Button from "../common/Button"

export default function HostRoomTab() {
	const nav = useNavigate()
	//api 로직
	//정보받아와서 map으로 돌려야함
	return (
		<>
			<Button
				type="button"
				theme="line"
				text="숙소 등록"
				style={{
					marginLeft: 134 + "rem",
					marginTop: 3 + "rem",
					fontSize: 1.4 + "rem",
				}}
				onClick={() => nav("/host/roomRegister")}
			></Button>
			<S.Ul>
				<RoomListLayout />
				<RoomListLayout />
				<RoomListLayout />
				<RoomListLayout />
				<RoomListLayout />
				<RoomListLayout />
			</S.Ul>
		</>
	)
}

const RoomListLayout = () => {
	const nav = useNavigate()
	return (
		<S.Li onClick={() => nav("/host/roommanage")}>
			<S.DivButton role="button" araia-label="관리페이지로 가기">
				<S.Img src="/" alt="숙소대표이미지" className={styled.img} />
				<div>
					<S.DesDiv>{"숙소명가나다라마바사아자차카파타하하하"}</S.DesDiv>
				</div>
			</S.DivButton>
		</S.Li>
	)
}

//style

const S: any = {}

S.Ul = styled.ul`
	display: flex;
	justify-content: center;
	align-items: center;
	align-content: center;
	flex-wrap: wrap;
	width: 102rem;
	margin: 0 auto;
	margin-top: 3rem;
`
S.Li = styled.li`
	width: 30rem;
	height: 33rem;
	margin-right: 2rem;
	margin-left: 2rem;
	margin-bottom: 3rem;
	border: 1px solid var(--color-gray0);
	border-radius: 2.5rem;
	padding: 2rem;
	&:hover,
	&:focus {
		box-shadow: 1px 1px 2px var(--color-gray0);
	}
`

S.DivButton = styled.div`
	cursor: pointer;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
`

S.Img = styled.img`
	display: block;
	width: 25rem;
	height: 22rem;
	margin: 0 auto;
	border-radius: 2rem;
	background: var(--color-gray0);
	margin-bottom: 3rem;
`
S.DesDiv = styled.div`
	width: 22rem;
	font-size: 1.6rem;
	margin-left: 2rem;
	overflow-x: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	&::-webkit-scrollbar {
		display: none;
	}
`
