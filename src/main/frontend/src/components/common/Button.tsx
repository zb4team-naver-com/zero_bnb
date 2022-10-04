import styled from "styled-components"

interface Props {
	theme?: "line"
	type: "button" | "submit" | "reset" | undefined
	style?: {}
	onClick?: () => void
	text: string
}

export default function Button(props: Props) {
	const { theme, type, style, onClick, text } = props
	return (
		<>
			{theme ? (
				<S.LineButton type={type} style={style} onClick={onClick}>
					{text}
				</S.LineButton>
			) : (
				<S.Button type={type} style={style} onClick={onClick}>
					{text}
				</S.Button>
			)}
		</>
	)
}

interface Click {
	onClick?: () => void
}

export const CloseButton = ({ onClick }: Click) => {
	return (
		<S.CloseButton
			role="button"
			aria-label="닫기"
			onClick={onClick}
		></S.CloseButton>
	)
}

const S: any = {}

S.Button = styled.button`
	width: 340px;
	height: 50px;
	background: var(--main-color1);
	border: 1px solid var(--main-color1);
	border-radius: 8px;
	font-size: 17px;
	font-weight: 700;
	color: #fff;
	cursor: pointer;
	&:hover {
		border: 3px solid var(--main-color1-1);
		box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
	}
`
S.LineButton = styled.button`
	width: 10rem;
	height: 4.8rem;
	background: var(--color-white);
	border: 1px solid var(--color-gray1);
	border-radius: 0.8rem;
	font-size: 1.4rem;
	font-weight: 600;
	color: var(--color-gray3);
	&:hover,
	&:focus {
		border: none;
		background: var(--main-color1);
		color: var(--color-white);
	}
`

S.CloseButton = styled.div`
	width: 0.3rem;
	height: 2rem;
	background: var(--color-gray0);
	border-radius: 1rem;
	transform: rotate(45deg);
	cursor: pointer;
	::before {
		content: "";
		display: block;
		width: 0.3rem;
		height: 2rem;
		background: var(--color-gray0);
		border-radius: 1rem;
		transform: rotate(-90deg);
	}
`
