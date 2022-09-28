import React from "react"
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Header from "../pages/Header"
import NotHeader from "../pages/NotHeader"
import LoginPage from "../pages/LoginPage"
import LogoutPage from "../pages/LogoutPage"
import SignupPage from "../pages/SignupPage"
import MainPage from "../pages/MainContainerPage"
import storage from "../services/api/api"

const Router = () => {
	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<Header />}>
					<Route path="MainContainerPage" element={<MainPage />} />
					<Route path="LoginPage" element={<LoginPage />} />
					<Route path="SignupPage" element={<SignupPage />} />
				</Route>
				<Route path="/" element={<NotHeader />}>
					<Route path="MainContainerPage" element={<MainPage />} />
					{/* <Route path="MainContainerPage/:hotelId" element={</>}/> */}
					<Route path="LogoutPage" element={<LogoutPage />} />
					<Route path="SignupPage" element={<SignupPage />} />
				</Route>
			</Routes>
		</BrowserRouter>
	)
}

export default Router
