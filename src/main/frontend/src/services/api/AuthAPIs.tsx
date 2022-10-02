import React from "react"
import axios from "axios"
import storage from "./api"

interface UserAuthSignUpInput {
	email: string
	password: string
	name: string
	birth: string
	phone: string
}

interface UserAuthLoginInput {
	email: string
	password: string
}

const instance = axios.create({
	baseURL: "http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com:8000",
})

instance.interceptors.response.use(
	(res) => {
		const { token } = res.data
		storage.set({ key: "token", value: token })
		return res
	},
	(error) => {
		return Promise.reject(error)
	}
)

const fetchLogin = (props: UserAuthLoginInput) => {
	return instance.post("./login", props)
}

const fetchSignUp = (props: UserAuthSignUpInput) => {
	return instance.post("./signup", props)
}

export { fetchLogin, fetchSignUp }
