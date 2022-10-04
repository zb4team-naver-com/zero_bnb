import styled from "styled-components"
import { hostRegisterForm } from "../../services/hostRegister"
import Button from "../common/Button"

interface Props {
	onChange: ((e: React.ChangeEvent<any>) => void)[]
}

const dataForm = hostRegisterForm

export default function HostRegisterForm(props: Props) {
	const [changeHandler, imgHandler] = props.onChange
	return (
		<>
			<S.Div>
				{dataForm.map(({ name_, type, placeholder, required }) => {
					return (
						<S.Input
							key={name_}
							name={name_}
							placeholder={placeholder}
							type={type}
							required={required}
							onChange={name_ === "profileImage" ? imgHandler : changeHandler}
						/>
					)
				})}
			</S.Div>
			<Button type="submit" text="등록 하기" />
		</>
	)
}

const S: any = {}

S.Div = styled.div`
	margin-top: 3rem;
	margin-bottom: 4rem;
`
S.Input = styled.input`
	display: block;
	width: 340px;
	height: 45px;
	padding-left: 10px;
	margin-bottom: 1.2rem;
	border: none;
	border-bottom: 1px solid var(--color-gray0);
	font-size: 1.4rem;
	font-family: var(--font-family-noto);
`
