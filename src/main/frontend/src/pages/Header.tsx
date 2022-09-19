import React from "react"
import { Outlet, Link } from "react-router-dom"
import HeaderComponents from "../components/layout/HeaderComponents"
import styled from "styled-components"
import MainPage from "./MainPage"

export default function Header() {
	return (
		<>
			<S.HeaderContainer>
				<HeaderComponents />

				<MainPage />
				<S.ButtonDiv>
					<S.StyledLink to="./LoginPage">로그인</S.StyledLink>
					<S.StyledLink to="./SignupPage">회원가입</S.StyledLink>
				</S.ButtonDiv>
			</S.HeaderContainer>
			<Outlet />
		</>
	)
}

const S: any = {}

S.HeaderContainer = styled.div`
	max-width: 169rem;
	height: 8rem;
	margin: 0 auto;
	padding: 0 2rem;
	display: flex;
	justify-content: space-between;
	align-items: center;
`

S.ButtonDiv = styled.div`
	display: flex;
	justify-content: space-between;
	width: 18rem;
`

S.StyledLink = styled(Link)`
	text-align: center;
	width: 8.5rem;
	height: 4rem;
	padding: 1rem 0;
	background: none;
	border: none;
	border-radius: 2.5rem;
	font-size: 1.6rem;
	font-weight: 500;
	color: #a6adce;
	flex-shrink: 0;
`
