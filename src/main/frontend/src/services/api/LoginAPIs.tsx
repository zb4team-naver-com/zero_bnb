import axios, { AxiosRequestConfig, AxiosRequestHeaders } from "axios"
import storage from "./api"

interface UserAuthLoginInput {
	email: string
	password: string
}

const EXPIRE_TIEM = 3600 * 1000

const instance = axios.create({
	baseURL: "http://localhost:8000",
	timeout: 6000,
	withCredentials: false,
	headers: {
		"Content-Type": "application/json",
	},
})

// instance.interceptors.request.use((config) => {
// 	const AccessToken = storage.get({ key: "Accesstoken" })
// 	const RefreshToken = storage.get({ key: "Accesstoken" })
// 	if (!!AccessToken) {
// 		storage.remove({ key: "Accesstoken" })
// 	}
// })

instance.interceptors.response.use(
	(response) => {
		const { token } = response.data
		storage.set({
			key: "Accesstoken",
			value: `Bearer ${token}`,
		})
		return { ...response, headers: { Authorization: token } }
	},
	(error) => {
		return Promise.reject(error)
	}
)

const fetchLogin = (props: UserAuthLoginInput) => {
	return instance.post("./login", props)
}

export { fetchLogin }
