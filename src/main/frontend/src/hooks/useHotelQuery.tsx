import React from "react"
import { AxiosError } from "axios"
import { useMutation } from "@tanstack/react-query"
import { fetchHotelList } from "../services/AuthAPI/HotelAPIs"

const useHotelQuery = () => {
	const list = useMutation(fetchHotelList, {
		onSuccess: () => {
			console.log("리스트 불러오기")
		},
		onError: (error) => {
			if (error instanceof AxiosError) alert(error.response?.data.details)
		},
	}).mutate
	return { list }
}

export default useHotelQuery
