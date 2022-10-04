import React from "react"
import { AxiosError } from "axios"
import { useMutation } from "@tanstack/react-query"
import { useNavigate } from "react-router-dom"
import { fetchLogin } from "../services/AuthAPI/LoginAPIs"
import { fetchSignUp } from "../services/AuthAPI/SignUpAPIs"

const useAuthQuery = () => {
	const navigate = useNavigate()

	const login = useMutation(fetchLogin, {
		onSuccess: () => {
			navigate("../MainContainerPage")
			console.log("성공")
		},
		onError: (error) => {
			if (error instanceof AxiosError) alert(error.response?.data.details)
		},
	}).mutate

	const signup = useMutation(fetchSignUp, {
		onSuccess: () => {
			navigate("../LoginPage")
		},
		onError: (error) => {
			if (error instanceof AxiosError) alert(error.response?.data.details)
		},
	}).mutate

	return { login, signup }
}

export default useAuthQuery
