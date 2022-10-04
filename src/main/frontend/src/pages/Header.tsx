import React from "react"
import { Outlet, Link } from "react-router-dom"
import HeaderComponents from "../components/layout/HeaderComponents"
import styled from "styled-components"
import GlobalStyle from "../assets/GlobalStyle"
import storage from "../services/api/api"
import Image from "../assets/logo.png"

export default function Header() {
	const isLogined = storage.get({ key: "token" }) ? true : false
	console.log(isLogined)
	return (
		<>
			<GlobalStyle />
			<S.HeaderContainer>
				<S.Content>
					<HeaderComponents />
					<Link to="./MainContainerPage">
						<S.IMG src={Image} />
					</Link>
					<S.ButtonDiv>
						{!isLogined ? (
							<S.StyledLink to="./LoginPage">로그인</S.StyledLink>
						) : (
							<S.StyledLink to="./LoginPage">로그아웃</S.StyledLink>
						)}
						<S.StyledLink to="./SignupPage">회원가입</S.StyledLink>
					</S.ButtonDiv>
				</S.Content>
			</S.HeaderContainer>
			<Outlet />
		</>
	)
}

const S: any = {}

S.HeaderContainer = styled.div`
	height: 8rem;
	background-color: var(--main-color2);
`

S.Content = styled.div`
	width: 145rem;
	height: 8rem;
	margin: 0 auto;
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

S.IMG = styled.img`
	width: 11.5rem;
`
