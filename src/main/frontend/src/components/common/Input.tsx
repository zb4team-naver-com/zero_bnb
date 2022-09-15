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
		<div className={className}>
			<label htmlFor={id}>{labelName}</label>
			{inputProps.textarea ? (
				<S.textArea id={id} placeholder={placeholder} {...props} />
			) : (
				<S.input id={id} placeholder={placeholder} {...props} />
			)}
			<S.inValidInputNotice isValid={isValid}>
				{invalidMessage}
			</S.inValidInputNotice>
		</div>
	)
}

export default Input

const S: any = {}

S.inValidInputNotice = styled.p<{ isValid: boolean }>`
	opacity: ${({ isValid }) => (isValid ? 0 : 1)};
`

S.textArea = styled.textarea`
	display: block;
`

S.input = styled.input`
	display: block;
`
