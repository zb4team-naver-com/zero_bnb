import { useQuery } from "@tanstack/react-query"
import axios from "axios"

export interface Room {
  type: string
  address: string
  description: string
  locationPosition: {
    latitude: number
    longitude: number
  }
  name: string
  notice: string
  policy: string
  eventInputs: {
    description: string
  } []
  popularFacilityServiceInputs: {
    popularFacilityServiceType: number
  } []
  accommodationImageInputs: {
    url: string
  } []
}

export interface RoomData extends Room {
  hostId: number
}

const getRoomInfo = async(hostId: number) => {
  const data = await axios.get(`/accommodation/search/${hostId}`)
  return data.data
}

export default function getRoom(hostId: number) {
  return useQuery(['@room'], () => getRoomInfo(hostId), {staleTime: Infinity})
}

export const putRoom = (accommodationId: number, data: any) => {
  return axios.put(`/accommodation/update/${accommodationId}`, data)
}

export const postRoom = (data: any) => {
  return axios.post(`/accommodation/register`, data)
}

export const postImg = (data:any) => {
  return axios({
    method: 'post',
    url: '/images',
    data: data,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  })
}