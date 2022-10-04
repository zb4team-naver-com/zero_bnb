import styled from "styled-components"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { useQueryClient } from "@tanstack/react-query"

import { GetHost } from "../services/HostAPI/host_axios"
import { initialValue } from "../services/hostRegister"
import hostQuery from "../services/HostAPI/host_Query"

import Button from "../components/common/Button"

const buttonStyle = {
	width: 6 + "rem",
	height: 4 + "rem",
	marginTop: 3 + "rem",
	marginRight: 1.5 + "rem",
}

export default function HostUpdatePage() {
	const query = useQueryClient()
	const data = query.getQueryData(["@host"]) as GetHost

	const init = data
		? {
				name: "....",
				businessContact: data.businessContact,
				companyRegistrationNumber: data.companyRegistrationNumber,
				email: "saymymin@kakao.com",
		  }
		: initialValue

	const [inputValues, setInputValues] = useState({})
	const nav = useNavigate()

	const changeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputValues({ ...inputValues, [e.target.name]: e.target.value })
	}

	const resetHandler = () => {
		setInputValues({})
		nav("/host/main")
	}

	const hostUpdate = hostQuery()
	const mutaion = hostUpdate.hostModify(1, inputValues)

	const submitHandler = (e: React.ChangeEvent<HTMLFormElement>) => {
		e.preventDefault()
		const an = mutaion.mutate()
		console.log(an)
	}

	return (
		<S.Layout>
			<S.H2>{init.name} 프로필 수정</S.H2>
			<S.Form onSubmit={submitHandler}>
				<S.Div>
					<S.List>
						<S.Label htmlFor="contact">연락처</S.Label>
						<S.Input
							id="contact"
							name="businessContact"
							placeholder={init.businessContact}
							onChange={changeHandler}
						/>
					</S.List>
					<S.List>
						<S.Label htmlFor="businessnumber">사업자 등록 번호</S.Label>
						<S.Input
							id="businessnumber"
							name="companyRegistrationNumber"
							placeholder={init.companyRegistrationNumber}
							onChange={changeHandler}
						/>
					</S.List>
					<S.List>
						<S.Label htmlFor="email">이메일</S.Label>
						<S.Input
							id="email"
							name="email"
							placeholder={init.email}
							onChange={changeHandler}
						/>
					</S.List>
				</S.Div>
				<div>
					<Button
						type="reset"
						text="취소"
						theme="line"
						onClick={resetHandler}
						style={buttonStyle}
					/>
					<S.Button
						type="submit"
						disabled={Object.keys(inputValues).length > 0 ? false : true}
					>
						확인
					</S.Button>
				</div>
			</S.Form>
		</S.Layout>
	)
}

//style

const S: any = {}

S.Layout = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	margin: 0 auto;
	margin-top: 6rem;
`
S.H2 = styled.h2`
	font-size: 2.2rem;
`
S.Form = styled.form`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 50rem;
	margin-top: 2rem;
	position: relative;
	border-radius: 2rem;
`

S.Div = styled.div`
	margin-top: 5rem;
	margin-bottom: 3rem;
`

S.List = styled.div`
	display: flex;
	flex-direction: column;
	align-items: left;
	height: 10rem;
`

S.Label = styled.label`
	display: flex;
	align-items: center;
	width: 14rem;
	height: 4.5rem;
	margin-left: 1rem;
	text-align: left;
	font-size: 1.6rem;
`

S.Input = styled.input`
	width: 30rem;
	height: 4.5rem;
	padding-left: 1.5rem;
	border: 1px solid var(--color-gray0);
	border-radius: 1rem;
	&::placeholer {
		color: var(--color-gray0);
	}
`
S.NameDiv = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	height: 3rem;
	margin-bottom: 2rem;
	padding-bottom: 0.5rem;
`
S.Name = styled.span`
	width: 15rem;
	margin-right: 2.5rem;
	padding-right: 1rem;
	font-size: 1.6rem;
`
S.Span = styled.span`
	width: 25rem;
`
S.Button = styled.button`
	width: 6rem;
	height: 4rem;
	margintop: 3rem;
	marginright: 1.5rem;
	background: var(--main-color1);
	border: 1px solid var(--main-color1-1);
	border-radius: 1rem;
	&:hover {
		box-shadow: 1px 1px 1px var(--color-gray0);
	}
`
