import React, { ChangeEvent } from "react"
import styled from "styled-components"

interface InputType {
	type?: string
	value: string
	onChange: (e: ChangeEvent<HTMLInputElement>) => void
	required?: boolean
	id?: string
	placeholder?: string
	labelName?: string
	textarea?: boolean
	isValid?: boolean
	invalidMessage?: string
	className?: string
	key?: string
	name?: string
}

const Input = (inputProps: InputType) => {
	const {
		id = inputProps.type,
		placeholder = inputProps.type,
		labelName = inputProps.placeholder || inputProps.type,
		isValid,
		invalidMessage,
		className,
		...props
	} = inputProps
	return (
		<S.Div className={className}>
			{inputProps.textarea ? (
				<S.textArea id={id} placeholder={placeholder} {...props} />
			) : (
				<S.input id={id} placeholder={placeholder} {...props} />
			)}
		</S.Div>
	)
}

// const HostInput = (hostProps: InputType) => {
// 	const {
// 		id = hostProps.type,
// 		key = hostProps.name,
// 		placeholder = hostProps.placeholder || hostProps.type,
// 		isValid,
// 		className,
// 		...props
// 	} = hostProps
// 	return (
// 		<S.Div className={className}>
// 			<S.input id={id} />
// 		</S.Div>
// 	)
// }

export default Input

const S: any = {}

S.Div = styled.div`
	padding-bottom: 2rem;
`

S.textArea = styled.textarea`
	display: block;
	width: 340px;
	height: 45px;
	padding-left: 10px;
	margin: auto;
	margin-bottom: 1.2rem;
	border: none;
	border-bottom: 1px solid #c9c9c9;
	font-size: 1.4rem;
	font-family: "Noto Sans KR", sans-serif;
`

S.input = styled.input`
	display: block;
	width: 340px;
	height: 45px;
	padding-left: 10px;
	margin: auto;
	margin-bottom: 1.2rem;
	border: none;
	border-bottom: 1px solid #c9c9c9;
	font-size: 1.4rem;
	font-family: "Noto Sans KR", sans-serif;
`
