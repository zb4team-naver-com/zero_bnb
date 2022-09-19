import React from "react"
import axios from "axios"
import storage from "./api"

interface UserAuthInput {
	email: string
	password: string
}

const instance = axios.create({
	baseURL: "http://localhost:8000",
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

const fetchLogin = (props: UserAuthInput) => {
	return instance.post("login", props)
}

const fetchSignUp = (props: UserAuthInput) => {
	return instance.post("signup", props)
}

export { fetchLogin, fetchSignUp }
