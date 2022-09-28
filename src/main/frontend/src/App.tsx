import React, { useEffect } from "react"
import Router from "./router/Router"
import axios from "axios"

function App() {
	useEffect(() => {
		axios
			.get("/")
			.then((response) => console.log(response.status))
			.catch((error) => console.log(error))
	}, [])

	return <Router />
}

export default App
