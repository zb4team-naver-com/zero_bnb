import { useQuery } from "@tanstack/react-query"
import axios from "axios"

export interface Room {
  type?: string
  address?: string
  description?: string
  locationPosition?: {
    latitude?: number
    longitude?: number
  }
  name?: string
  notice?: string
  policy?: string
  eventInputs?: {
    description: string
  } []
  popularFacilityServiceInputs?: {
    popularFacilityServiceType: number
  } []
  accommodationImageInputs?: {
    url: string
  } [] 
}

export interface RoomData extends Room {
  hostId: number
}

const getRoomInfo = async(hostId: number) => {
  const data = await axios.get(`http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com/accommodation/search/${hostId}`)
  return data.data
}

export default function getRoom(hostId: number) {
  return useQuery(['@room'], () => getRoomInfo(hostId), {staleTime: Infinity})
}

export const putRoom = (accommodationId: number, data: any) => {
  return axios.put(`http://ec2-18-221-179-218.us-east-2.compute.amazonaws.com/accommodation/update/${accommodationId}`, data)
}

export const postRoom = (data: any) => {
  return axios.post(`http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com/accommodation/register`, data)
}

