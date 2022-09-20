import React, {
	ChangeEvent,
	FormEvent,
	useState,
	useEffect,
	useRef,
} from "react"
import Input from "../common/Input"
import useInput from "../../hooks/useInput"
import useAuthQuery from "../../hooks/useAuthQuery"
import styled from "styled-components"

export default function SignupComponents() {
	const { signup } = useAuthQuery()
	const isFirstRender = useRef(true)

	const inputEmail = useInput("")
	const inputPassword = useInput("")
	const inputName = useInput("")
	const inputBirth = useInput("")
	const inputPhone = useInput("")

	const [isValidEmail, setIsValidEmail] = useState(true)
	const [isValidPassword, setIsValidPassword] = useState(true)
	const [isValidName, setIsValidName] = useState(true)
	const [isValidBirth, setIsValidBirth] = useState(true)
	const [isValidPhone, setIsValidPhone] = useState(true)

	const handleSignUp = async (e: FormEvent<HTMLFormElement>) => {
		e.preventDefault()
		signup({
			email: inputEmail.value,
			password: inputPassword.value,
			name: inputName.value,
			birth: inputBirth.value,
			phone: inputPhone.value,
		})
	}

	const checkValidEmail = () => {
		const email = inputEmail.value
		return /@/.test(email) && /\./.test(email)
	}

	const checkValidPassword = () => {
		const password = inputPassword.value
		return password.length >= 8
	}

	const checkValidName = () => {
		const name = inputName.value
		return /\s/.test(name)
	}

	const checkValidBirth = () => {
		const birth = inputBirth.value
		return /^\d{4}-\d{1,2}-\d{1,2}$/.test(birth)
	}

	const checkValidPhone = () => {
		const phone = inputPhone.value
		return /^\d{3}-\d{3,4}-\d{4}$/.test(phone)
	}

	useEffect(() => {
		if (isFirstRender.current) return
		setIsValidEmail(checkValidEmail())
	}, [inputEmail.value])

	useEffect(() => {
		if (isFirstRender.current) return
		setIsValidPassword(checkValidPassword())
	}, [inputPassword.value])

	useEffect(() => {
		if (isFirstRender.current) return
		setIsValidName(checkValidName())
	}, [inputName.value])

	useEffect(() => {
		if (isFirstRender.current) return
		setIsValidBirth(checkValidBirth())
	}, [inputBirth.value])

	useEffect(() => {
		if (isFirstRender.current) return
		setIsValidPhone(checkValidPhone())
	}, [inputPhone.value])

	return (
		<>
			<S.Div>
				<S.Span>회원가입</S.Span>
				<form name="signup" onSubmit={handleSignUp}>
					<Input
						type="email"
						{...inputEmail}
						isValid={isValidEmail}
						// invalidMessage="Email을 작성해 주세요."
						required
					/>
					<p></p>
					<Input
						type="password"
						placeholder="password"
						{...inputPassword}
						isValid={isValidPassword}
						// invalidMessage="비밀 번호는 8자 이상"
						required
					/>
					<Input
						type="text"
						placeholder="name"
						{...inputName}
						isValid={isValidName}
						// invalidMessage="이름"
						required
					/>
					<Input
						type="birth"
						placeholder="1999.01.01"
						{...inputBirth}
						isValid={isValidBirth}
						// invalidMessage="언제 태어 나심?"
						required
					/>
					<Input
						type="phone"
						placeholder="010-0000-0000"
						{...inputPhone}
						isValid={isValidPhone}
						// invalidMessage="폰 있어?"
						required
					/>
					<S.Button
						type="submit"
						disabled={
							!inputEmail.value ||
							!inputPassword.value ||
							!inputName.value ||
							!inputBirth.value ||
							!inputPhone.value
						}
					>
						회원가입
					</S.Button>
				</form>
			</S.Div>
		</>
	)
}

const S: any = {}

S.Div = styled.div`
	margin: 30px auto;
	width: 498px;
	height: 618px;
	background: var(--color-white);
	border: 1px solid var(--color-gray0);
	border-radius: 20px;
	box-shadow: 1px 1px 0 0 var(--color-gray0);
`
S.Span = styled.span`
	display: block;
	margin: auto;
	font-size: 20px;
	font-weight: 700;
	color: var(--color-gray3);
	text-align: center;
	padding-top: 50px;
	padding-bottom: 70px;
`

S.Button = styled.button`
	width: 340px;
	height: 50px;
	margin-left: 78px;
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
