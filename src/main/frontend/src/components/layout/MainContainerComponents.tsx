import React from "react"
import Image from "../../assets/logo.png"
import styled from "styled-components"

export default function MainContainerComponents() {
	return (
		<>
			<S.Div>
				<img src={Image} />
			</S.Div>
			<S.Div>
				<img src={Image} />
			</S.Div>
			<S.Div>
				<img src={Image} />
			</S.Div>
			<S.Div>
				<img src={Image} />
			</S.Div>
			<S.Div>
				<img src={Image} />
			</S.Div>
			<S.Div>
				<img src={Image} />
			</S.Div>
			<S.Div>
				<img src={Image} />
			</S.Div>
			<S.Div>
				<img src={Image} />
			</S.Div>
		</>
	)
}

const S: any = {}

S.Div = styled.div`
	margin: auto;
	width: 925px;
	padding-top: 100px;
`
