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
			{/* <S.Label htmlFor={id}>{labelName}</S.Label> */}
			{inputProps.textarea ? (
				<S.textArea id={id} placeholder={placeholder} {...props} />
			) : (
				<S.input id={id} placeholder={placeholder} {...props} />
			)}
			{/* <S.inValidInputNotice isValid={isValid}>
				{invalidMessage}
			</S.inValidInputNotice> */}
		</S.Div>
	)
}

export default Input

const S: any = {}

// S.inValidInputNotice = styled.p<{ isValid: boolean }>`
// 	opacity: ${({ isValid }) => (isValid ? 0 : 1)};
// `

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
