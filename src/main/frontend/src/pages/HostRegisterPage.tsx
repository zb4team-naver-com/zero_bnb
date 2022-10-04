import { useState } from "react"
import styled from "styled-components"
import RoomRegisterForm from "../components/host/HostRegisterForm"
import ico from "../assets/jjangkoo.png"
import { Register } from "../services/hostRegister"
import Popup from "../components/common/Popup"
import hostQuery from "../services/HostAPI/host_Query"

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
			toPage="HostMainPage"
		/>
	)

	const changeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
		setRegister({ ...register!, [e.target.name]: e.target.value })
	}

	const imgHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
		const fileName = URL.createObjectURL(e.target.files![0])
		setRegister({ ...register!, [e.target.name]: fileName })
	}

	const submitHandler = (e: React.ChangeEvent<HTMLFormElement>) => {
		e.preventDefault()
		const data = {
			businessContact: (register as Register).businessContact,
			companyRegistrationNumber: (register as Register)
				.companyRegistrationNumber,
			email: (register as Register).email,
			profileImage: (register as Register).profileImage,
		}
		console.log(data)
		setTimeout(() => {
			mutaion.mutate(data)
		}, 500)
	}

	const props = {
		onChange: [changeHandler, imgHandler],
	}

	return (
		<S.MainDiv>
			<S.Div>
				<S.Title>호스트 등록</S.Title>
				<S.Img
					src={
						(register as Register).profileImage
							? (register as Register).profileImage
							: ico
					}
				/>
				<form onSubmit={submitHandler}>
					<RoomRegisterForm {...props} />
				</form>
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
	margin-top: 8rem;
`

S.Div = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 55rem;
	height: 65rem;
	padding-top: 4rem;
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
