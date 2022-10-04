import React from "react"
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Header from "../pages/Header"
import LoginPage from "../pages/LoginPage"
import LogoutPage from "../pages/LogoutPage"
import SignupPage from "../pages/SignupPage"
import MainPage from "../pages/MainContainerPage"
import RoomRegisterContext from "../store/RoomRegisterContext"
import HostMainPage from "../pages/HostMainPage"
import RoomRegisterPage from "../pages/RoomRegisterPage"
import HostUpdatePage from "../pages/HostUpdatePage"

const Router = () => {
	return (
		<BrowserRouter>
			{/* <RoomRegisterContext> */}
			<Routes>
				<Route path="/" element={<Header />}>
					<Route path="MainContainerPage" element={<MainPage />} />
					<Route path="LoginPage" element={<LoginPage />} />
					<Route path="LogoutPage" element={<LogoutPage />} />
					<Route path="SignupPage" element={<SignupPage />} />
					<Route path="HostMainPage" element={<HostMainPage />} />
					<Route path="HostUpdatePage" element={<HostUpdatePage />} />
					<Route path="RoomRegisterPage" element={<RoomRegisterPage />} />
				</Route>
			</Routes>
		</BrowserRouter>
	)
}

export default Router
