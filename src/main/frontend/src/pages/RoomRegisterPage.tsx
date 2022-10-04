import { serviceTypes } from "../services/roomRegister"

import React, { useContext, useEffect, useState } from "react"
import { myContext } from "../store/RoomRegisterContext"
import {
	CheckBox,
	Input,
	Radio,
	TextArea,
} from "../components/host/Room_registerItem"
import ImageUpload from "../components/host/ImageUpload"
import styled from "styled-components"
import roomQuery from "../services/HostAPI/roomQuery"
import { useNavigate } from "react-router-dom"

export default function RoomRegisterPage() {
	let {
		type,
		address,
		locationPosition,
		description,
		name,
		notice,
		policy,
		eventInputs,
		popularFacilityServiceInputs,
		accommodationImageInputs,
		imgaes,
	} = useContext(myContext)

	const initvalue = {
		type,
		address,
		description,
		name,
		notice,
		policy,
		eventInputs,
	}

	const [basicInfo, setBasicInfo] = useState(initvalue)
	const [service, setService] = useState<string[] | null>([])

	const { roomRegister } = roomQuery()
	const mutation = roomRegister()

	const nav = useNavigate()

	const typeLists = ["펜션", "호텔", "모텔", "빌라", "게스트하우스"]
	const eventList = serviceTypes

	const handler = {
		basicChange: (
			e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
		) => {
			const value = e.target.name === "type" ? e.target.id : e.target.value
			setBasicInfo({ ...basicInfo, [e.target.name]: value })
		},
		eventChange: (e: React.ChangeEvent<HTMLInputElement>) => {
			setBasicInfo({
				...basicInfo,
				[e.target.name]: { description: e.target.value },
			})
		},
		serviceChange: (e: React.ChangeEvent<HTMLInputElement>) => {
			if (service?.includes(e.target.id)) {
				const newList = [...service].filter((item) => item !== e.target.id)
				setService(newList)
			} else {
				const newList = [...service!]
				newList.push(e.target.id)
				setService(newList)
			}
		},
	}

	const clickReset = () => {
		alert("취소 되었습니다")
		nav("HostMainPage")
	}

	const submitHandler = (e: React.ChangeEvent<HTMLFormElement>) => {
		e.preventDefault()
		const data = { ...basicInfo, popularFacilityServiceInputs: service }
		mutation.mutate(data)
		console.log(data)
	}

	return (
		<>
			<S.Layout>
				<S.Title>숙소 등록</S.Title>
				<div>
					<S.ImgDiv>
						<ImageUpload />
					</S.ImgDiv>
					<S.Form onSubmit={submitHandler}>
						<Radio
							text="숙소 타입을 선택하세요"
							name="type"
							lists={typeLists}
							onChange={handler.basicChange}
						/>
						<Input
							id="address"
							text="주소를 입력하세요"
							name="address"
							placeholder="주소 입력"
							onChange={handler.basicChange}
						/>
						<TextArea
							id="description"
							text="숙소에 대해 소개해 주세요"
							name="description"
							onChange={handler.basicChange}
						/>
						<Input
							id="name"
							text="숙박명을 입력하세요"
							name="name"
							placeholder="숙소명 입력"
							onChange={handler.basicChange}
						/>
						<TextArea
							id="notice"
							text="공지사항을 입력하세요"
							name="notice"
							onChange={handler.basicChange}
						/>
						<TextArea
							id="policy"
							text="지침사항을 입력하세요"
							name="policy"
							onChange={handler.basicChange}
						/>
						<Input
							id="events"
							text="이벤트를 입력하세요"
							name="eventInputs"
							placeholder="이벤트 입력"
							onChange={handler.eventChange}
						/>
						<CheckBox
							text="게스트에게 제공할 서비스를 선택하세요"
							name="popularFacilityServiceInputs"
							lists={eventList}
							onChange={handler.serviceChange}
						/>
						<S.ButtonArea>
							<S.Reset type="reset" onClick={clickReset}>
								취소
							</S.Reset>
							<S.Button type="submit" value="등록" />
						</S.ButtonArea>
					</S.Form>
				</div>
			</S.Layout>
		</>
	)
}

const S: any = {}

S.Layout = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 60rem;
	margin: 0 auto;
	margin-top: 10rem;
	margin-bottom: 10rem;
`
S.Title = styled.h2`
	margin-bottom: 8rem;
	font-size: 2.2rem;
	font-weight: 600;
	color: var(--main-color2-2);
`
S.ImgDiv = styled.div`
	display: flex;
	justify-content: center;
	width: 50rem;
	height: 5rem;
	padding-bottom: 3rem;
	border-bottom: 1px solid var(--color-gray0);
`
S.Form = styled.form`
	margin-left: 5rem;
	margin-top: 5rem;
`

S.Button = styled.input`
	width: 8rem;
	height: 3.5rem;
	background: var(--main-color1);
	border-radius: 2rem;
	border: 2.2px solid var(--main-color1-1);
	font-size: 1.5rem;
	font-weight: 700;
	color: var(--color-white);
	cursor: pointer;
	&:hover,
	&:focus {
		transform: scale(1.01);
		box-shadow: 1px 1px 2px var(--color-gray0);
	}
`
S.Reset = styled.button`
	width: 8rem;
	height: 3.5rem;
	margin-right: 1rem;
	background: white;
	border-radius: 2rem;
	border: 2.2px solid var(--main-color2-1);
	font-size: 1.5rem;
	font-weight: 700;
	color: var(--main-color2-2);
	cursor: pointer;
	&:hover,
	&:focus {
		color: var(--color-white);
		background: var(--main-color2-1);
		box-shadow: 1px 1px 2px var(--color-gray0);
	}
`

S.ButtonArea = styled.div`
	display: flex;
	align-items: center;
	margin-left: 8rem;
`
