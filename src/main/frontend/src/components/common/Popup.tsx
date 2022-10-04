import { useNavigate } from "react-router-dom"
import styled from "styled-components"
import Button, { CloseButton } from "./Button"
import ico from "../../assets/smile.png"

interface Popup {
	text: string
	subText?: string
	page?: string
	toPage?: string
	close?: () => void
}

export default function Popup(props: Popup) {
	const { text, subText, page, toPage, close } = props
	const nav = useNavigate()

	return (
		<S.Layout>
			<S.Div>
				<S.CloseDiv>
					<CloseButton onClick={close} />
				</S.CloseDiv>
				<S.TextDiv>
					<S.Text>{text}</S.Text>
					<S.SubText>{subText}</S.SubText>
				</S.TextDiv>

				<S.Img src={ico} alt="캐릭터아이콘" />

				<Button type="button" text={page!} onClick={() => nav(toPage!)} />
			</S.Div>
		</S.Layout>
	)
}

interface ManagePopup extends Popup {
	onClick?: () => void
}

export function ManagePopup(props: ManagePopup) {
	const { text, close, onClick } = props

	return (
		<S.Layout>
			<S.Div>
				<S.TextDiv>
					<S.Text>{text}</S.Text>
				</S.TextDiv>

				<S.Img src={ico} alt="캐릭터아이콘" />

				<S.Button type="button" onClick={close}>
					취소
				</S.Button>
				<S.Button type="button" onClick={onClick}>
					확인
				</S.Button>
			</S.Div>
		</S.Layout>
	)
}

//style

const S: any = {}

S.Layout = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100vh;
	background: rgba(0, 0, 0, 0.75);
	z-index: 9999;
	overflow: hidden;
`

S.Div = styled.div`
	position: relative;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	width: 48rem;
	height: 55rem;
	border-radius: 2.5rem;
	background: var(--color-white);
`
S.CloseDiv = styled.div`
	position: absolute;
	top: 3%;
	right: 5%;
	cursor: pointer;
`
S.TextDiv = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`

S.Text = styled.span`
	font-size: 2.2rem;
	font-weight: 700;
	margin-bottom: 2.2rem;
`
S.SubText = styled.span`
	width: 25rem;
	text-align: center;
	vertical-align: middle;
	line-height: 1.3;
	font-size: 1.7rem;
	font-weight: 500;
	white-space: normal;
`
S.Img = styled.img`
	width: 6rem;
	margin-top: 4rem;
	margin-bottom: 6rem;
`
S.Button = styled.button`
	width: 8rem;
	height: 3.8rem;
	margin-bottom: 1rem;
	background: var(--color-white);
	border: 1px solid var(--color-gray0);
	border-radius: 1rem;
	&:hover,
	&:focus {
		background: var(--main-color1);
		color: var(--color-white);
	}
`
