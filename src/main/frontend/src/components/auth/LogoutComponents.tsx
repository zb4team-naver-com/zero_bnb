import React, { useEffect } from "react"
import { useNavigate } from "react-router-dom"
import LoginPage from "../../pages/LoginPage"
import storage from "../../services/api/api"

export default function LogoutComponents() {
	const navigate = useNavigate()

	useEffect(() => {
		storage.remove({ key: "token" })
		navigate("../../pages/LoginPage")
	})

	return (
		<>
			<LoginPage />
		</>
	)
}
