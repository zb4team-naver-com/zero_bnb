import axios from "axios"
import storage from "./api"

interface UserAuthLoginInput {
	email: string
	password: string
}

const instance = axios.create({
	baseURL: "http://localhost:8000",
	headers: {
		"Content-Type": "application/json",
	},
})

instance.interceptors.request.use((config) => {
	const accesstoken = config.data
	axios.defaults.headers.common["Authorization"] = `Bearer ${accesstoken.token}`
	console.log(accesstoken.token)
	return config
})

const fetchLogin = (props: UserAuthLoginInput) => {
	return instance.post("./login", props)
}

export { fetchLogin }
