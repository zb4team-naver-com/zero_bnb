import React from "react"
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Header from "../pages/Header"
import LoginPage from "../pages/LoginPage"
import SignupPage from "../pages/SignupPage"

const Router = () => {
	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<Header />}>
					<Route path="LoginPage" element={<LoginPage />} />
					<Route path="SignupPage" element={<SignupPage />} />
				</Route>
			</Routes>
		</BrowserRouter>
	)
}

export default Router
