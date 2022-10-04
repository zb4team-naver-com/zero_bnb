import { useMutation, useQueryClient } from "@tanstack/react-query"
import Popup from "../../components/common/Popup"
import { GetHost, postHost, putHost } from "./host_axios"

export default function hostQuery() {
	const queryClient = useQueryClient()

	const hostRegister = (fn: (value: any) => void, value: any) => {
		return useMutation(postHost, {
			onSuccess: () => {
				fn(value)
			},
			onError: (e: any) => {
				const { data } = e.response
				alert(data.message)
			},
		})
	}

	const hostModify = (id: number, data: GetHost) => {
		return useMutation(() => putHost(id, data), {
			onSuccess: () => {
				queryClient.invalidateQueries(["@host"])
				return (
					<Popup
						text={"수정이 완료 되었습니다"}
						page={"go hostpage"}
						toPage={"/host"}
					/>
				)
			},
			onError: (e: any) => {
				alert(e.message)
			},
		})
	}

	return { /*getData,*/ hostRegister, hostModify }
}
