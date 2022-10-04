import { useQuery } from "@tanstack/react-query"
import axios from "axios"

export interface Host {
	businessContact?: string
	companyRegistrationNumber?: string
	profileImage?: string | Promise<any>
	email?: string
}

export interface GetHost extends Host {
	hostId?: number
	active?: boolean
}

//Host
const getHost = async (email: string) => {
	const data = await axios.get(
		`http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com/host/${email}`
	)
	return data.data
}

export default function getInfo(email: string) {
	return useQuery(["@host"], () => getHost(email), {
		staleTime: Infinity,
		refetchOnWindowFocus: false,
		refetchOnMount: false,
		refetchOnReconnect: false,
		retry: 1,
		onError: () => {
			alert("호스트 정보를 가져오지 못했습니다")
		},
	})
}

export const putHost = (hostId: number, data: GetHost) => {
	return axios.put(
		`http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com/host/update/${hostId}`,
		data
	)
}

export const postHost = (data: Host) => {
	return axios.post(
		"http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com/host/register",
		data
	)
}

const userFetch = () => {
	return new Promise((resolve) => {
		setTimeout(() => {
			resolve({ name: "ungheeya", birthDay: "825" })
		}, 2000)
	})
}

export const getFetch = () => {
	return useQuery(["my"], userFetch)
}
