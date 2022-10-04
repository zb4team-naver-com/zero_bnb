import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"

import styled from "styled-components"
import hostQuery from "../../services/HostAPI/host_Query"
import { ManagePopup } from "../common/Popup"
import HostProfile from "../host/HostProfile"

export default function HostMenuTab() {
	const nav = useNavigate()
	const [openPopup, setOpenPopup] = useState(false)

	const { hostModify } = hostQuery()
	const mutation = hostModify(4, { active: false })

	const close = () => {
		setOpenPopup(false)
	}
	const noneHostMode = () => {
		setTimeout(() => {
			mutation.mutate()
			nav("/MainContainerPage")
		}, 1000)
	}

	const popup = (
		<ManagePopup
			text={"계정을 비활성화 하시겠습까?"}
			close={close}
			onClick={noneHostMode}
		/>
	)

	return (
		<>
			{openPopup ? popup : null}
			<S.Div>
				<HostProfile />
				<S.Ul>
					<S.Li>
						계정관리
						<U.Ul>
							<U.Li>
								<Link to="/Host/HostUpdatePage">프로필 수정</Link>
							</U.Li>
							<U.Li onClick={() => setOpenPopup(true)}>계정 비활성화</U.Li>
						</U.Ul>
					</S.Li>
					<S.Li>
						숙소관리
						<U.Ul>
							<U.Li>
								<Link to="/Host/HostMainPage">숙소 보기</Link>
							</U.Li>
							<U.Li>
								<Link to="/Host/RoomRegisterPage">숙소 등록</Link>
							</U.Li>
							<U.Li>숙소 수정</U.Li>
						</U.Ul>
					</S.Li>
				</S.Ul>
			</S.Div>
		</>
	)
}

//style

const S: any = {}

S.Div = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: start;
	align-items: center;
	width: 28rem;
	height: 75rem;
	margin-top: 2rem;
	margin-right: 2rem;
	padding-top: 6rem;
	border: 1px solid var(--color-gray0);
	border-radius: 2rem;
`
S.Ul = styled.ul`
	margin-top: 8rem;
`
S.Li = styled.li`
	width: 20rem;
	height: 8rem;
	margin-bottom: 11rem;
	padding-left: 0.5rem;
	font-size: 1.6rem;
	font-weight: 700;
	color: var(--color-gray3);
`

const U: any = {}

U.Ul = styled.ul`
	margin-top: 3.8rem;
`
U.Li = styled.li`
	display: block;
	width: 20rem;
	height: 4rem;
	margin-bottom: 2rem;
	padding: 1rem 0;
	padding-left: 1rem;
	font-size: 1.4rem;
	color: var(--color-gray2);
	&:hover,
	&:focus {
		background: var(--main-color2-0);
		border-radius: 1rem;
		color: var(--color-white);
		cursor: pointer;
	}
`
