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
				<S.Span>회원 가입</S.Span>
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
						placeholder="비밀번호"
						{...inputPassword}
						isValid={isValidPassword}
						// invalidMessage="비밀 번호는 8자 이상"
						required
					/>
					<Input
						type="text"
						placeholder="이름"
						{...inputName}
						isValid={isValidName}
						// invalidMessage="이름"
						required
					/>
					<Input
						type="birth"
						placeholder="1999-01-01"
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
	margin: auto;
	width: 498px;
	height: 718px;
	border: 1px solid blue;
`
S.Span = styled.span`
	display: block;
	margin: auto;
	font-size: 2rem;
	text-align: center;
	padding-top: 30px;
	padding-bottom: 70px;
`

S.Button = styled.button`
	width: 25rem;
	margin-left: 3.3rem;
	background: #fc797f;
	border: 1px solid #fc797f;
	border-radius: 0.8rem;
	font-size: 1.2rem;
	font-weight: 700;
	color: #fff;
	cursor: pointer;
`
