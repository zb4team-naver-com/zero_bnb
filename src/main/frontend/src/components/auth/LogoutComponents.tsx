import React, { useEffect } from "react"
import { useNavigate } from "react-router-dom"
import MainContainerPage from "../../pages/MainContainerPage"
import storage from "../../services/AuthAPI/api"

export default function LogoutComponents() {
	const navigate = useNavigate()

	useEffect(() => {
		storage.remove({ key: "token" })
		navigate("../MainContainerPage")
		window.location.reload()
	})

	return <></>
}
