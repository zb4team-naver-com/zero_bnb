import { useMutation, useQueryClient } from "@tanstack/react-query";
import {  postRoom, putRoom, Room, RoomData } from "./room_axios";

export default function roomQuery() {
  const queryClient = useQueryClient()

  const roomRegister = () => {
    return useMutation(postRoom, {
      onSuccess: () => {
        queryClient.invalidateQueries(['@room'])
        alert('등록이 완료되었습니다.')
      },
      onError: (e: any) => {
        const { data } = e.response
        alert(data.message)
      }
    })
  }



  const roomModify = (accommodationId: number, data: RoomData) => {
    return useMutation(() => putRoom(accommodationId, data), {
      onSuccess: () => {
        queryClient.invalidateQueries(['@room'])
        console.log('성공?')
      },
      onError: () => {
       console.log('실패?')
      }
    })
  }

  return { roomRegister, roomModify }
}