import React, { useState, useEffect, useRef, FormEvent } from "react"
import useAuthQuery from "../../hooks/useAuthQuery"
import useInput from "../../hooks/useInput"
import Input from "../common/Input"
import styled from "styled-components"

export default function LoginComponents() {
	const { login } = useAuthQuery()
	const isFirstRender = useRef(true)
	const [isValidEmail, setIsValidEmail] = useState(true)
	const [isValidPassword, setIsValidPassword] = useState(true)
	const inputEmail = useInput("")
	const inputPassword = useInput("")

	const handleLogin = (e: FormEvent<HTMLFormElement>) => {
		e.preventDefault()
		login({
			email: inputEmail.value,
			password: inputPassword.value,
		})
	}

	return (
		<>
			<S.Div>
				<S.Span>로그인</S.Span>
				<form name="login" onSubmit={handleLogin}>
					<Input
						type="email"
						{...inputEmail}
						isValid={isValidEmail}
						// invalidMessage="email을 입력해주세요"
						required
					/>
					<Input
						type="password"
						{...inputPassword}
						isValid={isValidPassword}
						// invalidMessage="비밀번호를 입력해주세요."
						required
					/>
					<S.Button
						type="submit"
						disabled={!inputEmail.value || !inputPassword.value}
					>
						로그인
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
	height: 518px;
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
