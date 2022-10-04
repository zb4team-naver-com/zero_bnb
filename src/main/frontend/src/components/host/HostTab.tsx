import styled from "styled-components"

interface Props {
	trueTab: () => void
	falseTab: () => void
}

export default function HostTap(props: Props) {
	const { trueTab, falseTab } = props
	return (
		<>
			<S.Ultab>
				<S.Li>
					<S.Button id="계정관리" onClick={trueTab}>
						계정 관리
					</S.Button>
				</S.Li>
				<S.Li>
					<S.Button id="숙소관리" onClick={falseTab}>
						숙소 관리
					</S.Button>
				</S.Li>
			</S.Ultab>
		</>
	)
}

export function FixTab() {
	return (
		<>
			<S.Ultab>
				<S.FixLi>계정 수정</S.FixLi>
				<S.FixLi>숙소 관리</S.FixLi>
			</S.Ultab>
		</>
	)
}

//style

const S: any = {}

S.Ultab = styled.ul`
	position: relative;
	display: flex;
	justify-content: space-around;
	align-items: center;
	width: 140rem;
	margin: 0 auto;
	margin-top: 8rem;
	&::after {
		content: "";
		position: absolute;
		bottom: 0;
		width: 100%;
		height: 0.1rem;
		background: var(--color-gray0);
	}
`

S.Li = styled.li`
	width: 80rem;
	height: 6rem;
	padding: 1.8rem 0;
	text-align: center;
	cursor: pointer;
	&:hover,
	&:active {
		background: rgb(241, 241, 241);
		border-top-left-radius: 2rem;
		border-top-right-radius: 2rem;
	}
`

S.Button = styled.button`
	width: 80%;
	height: 100%;
	background: none;
	border: none;
`
S.FixLi = styled.li`
	width: 80rem;
	height: 6rem;
	padding: 1.8rem 0;
	text-align: center;
	&:first-child {
		background: rgb(241, 241, 241);
		border-top-left-radius: 2rem;
		border-top-right-radius: 2rem;
	}
`
