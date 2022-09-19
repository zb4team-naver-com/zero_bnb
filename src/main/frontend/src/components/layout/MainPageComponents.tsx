import React from "react"
import Image from "../../assets/logo.png"
import styled from "styled-components"

export default function MainPageComponents() {
	return (
		<>
			<a href="/">
				<S.IMG src={Image} />
			</a>
		</>
	)
}

const S: any = {}

S.IMG = styled.img`
	width: 10rem;
`
