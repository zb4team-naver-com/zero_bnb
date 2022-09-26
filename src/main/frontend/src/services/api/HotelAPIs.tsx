import axios from "axios"
import storage from "./api"

interface HotelType {
	id: string
	title: string
	content: string
}

const instance = axios.create({
	baseURL: "http://localhost:8000",
})

instance.interceptors.response.use((res) => {
	return res.data
})
