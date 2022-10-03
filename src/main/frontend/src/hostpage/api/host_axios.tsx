import { useQuery } from "@tanstack/react-query"
import axios from "axios"

export interface Host {
  businessContact: string
  companyRegistrationNumber: string
  profileImage: string
  email?: string
}

export interface GetHost extends Host {
  hostId?: number
  active?: boolean
}

 
//Host
const getHost = async(email: string) => {
  const data = await axios.get(`/host/${email}`)
  return data.data
};

export default function getInfo(email: string) {
  return useQuery(['@host'], () => getHost(email), {staleTime: Infinity})
} 

export const putHost = (hostId: number, data: Host) => {
  return axios.put(`/host/update/${hostId}`, data)
};

export const postHost = (data: Host) => {
  return axios.post("/host/register", data)
};

