import React, { useState, useRef, useEffect, startTransition } from "react"
import Image from "../../assets/logo.png"
import styled from "styled-components"

export default function MainContainerComponents() {
	return (
		<>
			<S.Main>
				<S.Section>
					<S.DivBox>
						<S.ImgDiv>
							<img src="/" />
						</S.ImgDiv>
						<S.PDiv>
							<p>여기어때 혼자 어때 ~~</p>
						</S.PDiv>
					</S.DivBox>
				</S.Section>
			</S.Main>
		</>
	)
}

const S: any = {}

S.Main = styled.main`
	max-width: 100%;
	width: 100%;
	height: 100%;
	border: 1px solid red;
`

S.Section = styled.section`
	max-width: 99.9%;
	height: 1000px;
	margin: 1px;
	border: 1px solid blue;
	display: flex;
	float: left;
`

S.DivBox = styled.div`
	display: block;
	width: 336px;
	height: 420px;
	margin: 10px;
`

S.ImgDiv = styled.div`
	width: 336px;
	height: 320px;
	border: 1px solid black;
`
S.PDiv = styled.div`
	width: 336px;
	height: 100px;
	border: 1px solid pink;
`
