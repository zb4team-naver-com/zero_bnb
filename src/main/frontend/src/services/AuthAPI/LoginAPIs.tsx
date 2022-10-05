import axios from "axios"
import storage from "./api"

interface UserAuthLoginInput {
	email: string
	password: string
}

const instance = axios.create({
	baseURL: "http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com",
	timeout: 60000,
	headers: {
		"Content-Type": "application/json",
		Authorization: "accesstoken",
	},
})

instance.interceptors.request.use((config) => {
	const accesstoken = axios.defaults.headers.common.Authorization
	storage.set({ key: "token", value: `Bearer ${accesstoken}` })
	return config
})

instance.interceptors.response.use(
	(response) => {
		return response
	},
	async (error) => {
		const {
			config,
			response: { status },
		} = error
		if (status === 401) {
			if (error.response.data.message === "TokenExpired") {
				const request = config

				const { data } = await axios.post(
					`http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com/login`
				)
				axios.defaults.headers.common.Authorization = `Bearer ${data}`
				storage.set({
					key: "token",
					value: `${axios.defaults.headers.common.Authorization}`,
				})
				return axios(request)
			}
		}
		return Promise.reject(error)
	}
)

const fetchLogin = (props: UserAuthLoginInput) => {
	return instance.post("./login", props)
}

export { fetchLogin }
