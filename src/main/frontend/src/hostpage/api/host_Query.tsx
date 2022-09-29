import { useMutation, useQueryClient } from "@tanstack/react-query";
import { Host, postHost, putHost } from "./host_axios";


export default function hostQuery() {
  const queryClient = useQueryClient()

  const hostRegister = (fn:(value: any) => void, value: any) => {
    return useMutation(postHost, {
      onSuccess: () => {
        fn(value)
      },
      onError: (e: any) => {
        const { data } = e.response
        alert(data.message)
      }
    })
  }

  const hostModify = (id: number, data: Host) => {
    return useMutation(() => putHost(id, data), {
      onSuccess: () => {
        queryClient.invalidateQueries(["@host"])
        console.log('성공?')
      },
      onError: () => {
       console.log('실패?')
      }
    })
  }

  return { /*getData,*/ hostRegister, hostModify}
}
  
  

