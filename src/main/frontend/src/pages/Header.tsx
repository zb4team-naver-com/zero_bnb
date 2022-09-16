import React from "react"
import { Outlet, Link } from "react-router-dom"
import HeaderComponents from "../components/layout/HeaderComponents"

export default function Header() {
	return (
		<>
			<div>
				<HeaderComponents />
				<Link to="./LoginPage">로그인</Link>{" "}
				<Link to="./SignupPage">회원가입</Link>
			</div>
			<Outlet />
		</>
	)
}
