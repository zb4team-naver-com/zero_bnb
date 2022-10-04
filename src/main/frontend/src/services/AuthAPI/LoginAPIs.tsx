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

instance.interceptors.response.use((response) => {
	const accesstoken = response.data
	storage.set({ key: "token", value: `Bearer ${accesstoken}` })
	return response
})

const fetchLogin = (props: UserAuthLoginInput) => {
	return instance.post("./login", props)
}

export { fetchLogin }
