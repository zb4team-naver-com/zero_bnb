import React, { useState, useEffect, useRef, FormEvent } from "react"
import useAuthQuery from "../../hooks/useAuthQuery"
import useInput from "../../hooks/useInput"
import Input from "../common/Input"
import styled from "styled-components"
import axios from "axios"
import storage from "../../services/AuthAPI/api"
import { useNavigate } from "react-router-dom"

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

	const isLogined = storage.get({ key: "token" })
	const navigate = useNavigate()

	if (!!isLogined) {
		useEffect(() => {
			alert("이미 로그인이 돼있습니다.")
			navigate("../MainContainerPage")
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
	margin: 60px auto;
	width: 498px;
	height: 518px;
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
	margin-top: 20px;
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
