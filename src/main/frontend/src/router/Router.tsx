import React from "react"
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Header from "../pages/Header"
import LoginPage from "../pages/LoginPage"
import LogoutPage from "../pages/LogoutPage"
import SignupPage from "../pages/SignupPage"
import MainPage from "../pages/MainContainerPage"
import RoomRegisterContext from "../hostpage/store/RoomRegisterContext"
import HostMainPage from "../hostpage/page/HostMainPage"
import RoomRegisterPage from "../hostpage/page/RoomRegisterPage"
import HostUpdatePage from "../hostpage/page/HostUpdatePage"

const Router = () => {
	return (
		<BrowserRouter>
			<RoomRegisterContext>
				<Routes>
					<Route path="/" element={<Header />}>
						<Route path="MainContainerPage" element={<MainPage />} />
						<Route path="LoginPage" element={<LoginPage />} />
						<Route path="SignupPage" element={<SignupPage />} />
						<Route path="/host" element={<HostMainPage/>} />
						<Route path="/host/update" element={<HostUpdatePage/>} />
						<Route path="/host/roomRegister" element={<RoomRegisterPage/>} />
					</Route>
				</Routes>
			</RoomRegisterContext>
		</BrowserRouter>
	)
}

export default Router
