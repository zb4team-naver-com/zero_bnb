import axios from "axios"

interface HotelType {
	accommodationId: string
	name: string
	latitude: number
	longitude: number
	address: string
	description: string
	wishCount: string
	accommodationType: string
	accommodationImageList: string
}

const instance = axios.create({
	baseURL: "http://localhost:8000/accommodation/info",
})

const fetchHotelList = (props: HotelType) => {
	return instance.post("./accommodation/info", props)
}

export { fetchHotelList }
