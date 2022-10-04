import { useState } from "react"
import styled from "styled-components"

import Popup from "../components/common/Popup"
import HostRegisterForm from "../components/host/HostRegisterForm"

import hostQuery from "../services/HostAPI/host_Query"
import { Register } from "../services/hostRegister"

export default function HostRegisterPage() {
	const { hostRegister } = hostQuery()

	const [register, setRegister] = useState<Register | {}>({})
	const [openPopup, setOpenPopup] = useState(false)

	const mutaion = hostRegister(setOpenPopup, true)

	const popUp = (
		<Popup
			text="등록이 완료되었어요"
			subText="호스트 페이지로 이동해 보세요"
			page="GO"
			toPage="/Host/HostMainPage"
		/>
	)

	const changeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
		setRegister({ ...register!, [e.target.name]: e.target.value })
	}

	const submitHandler = (e: React.ChangeEvent<HTMLFormElement>) => {
		e.preventDefault()
		const data = {
			businessContact: (register as Register).businessContact,
			companyRegistrationNumber: (register as Register)
				.companyRegistrationNumber,
			email: (register as Register).email,
			profileImage: "",
		}
		console.log(data)
		setTimeout(() => {
			mutaion.mutate(data)
		}, 500)
	}

	const props = {
		onChange: [changeHandler],
	}

	return (
		<S.MainDiv>
			<S.Div>
				<S.Title>호스트 등록</S.Title>
				<S.Form onSubmit={submitHandler}>
					<HostRegisterForm {...props} />
				</S.Form>
				{openPopup ? popUp : null}
			</S.Div>
		</S.MainDiv>
	)
}

//style

const S: any = {}

S.MainDiv = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin-top: 12rem;
`

S.Div = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 50rem;
	height: 55rem;
	padding-top: 5rem;
	padding-bottom: 4rem;
	background: var(--color-white);
	border: 1px solid var(--color-gray0);
	border-radius: 20px;
	box-shadow: 1px 1px 0 0 var(--color-gray0);
`
S.Title = styled.div`
	margin-bottom: 3rem;
	font-size: 20px;
	font-weight: 700;
	color: var(--color-gray3);
`

S.Img = styled.img`
	width: 13rem;
	height: 13rem;
	background: var(--color-gray1);
	border-radius: 50%;
	border: 2px solid var(--color-gray2);
	cursor: pointer;
	&:hover {
		transform: scale(1.08);
		transition: all 0.3s ease;
	}
`
S.Form = styled.form`
	display: flex;
	flex-direction: column;
	align-items: center;
`
