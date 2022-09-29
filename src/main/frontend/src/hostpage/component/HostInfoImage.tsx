import React, { useRef, useState } from "react"
import styled from "styled-components"

import ico from "../../assets/jjangkoo.png"
import getInfo from "../api/host_axios";



export default function Host_infoImage() {
  const [ profileImg, setProfileImg ] = useState(ico)
  const{ data } = getInfo('saymymin@kakao.com')

  const refInput = useRef<HTMLInputElement>(null)


  const hostInfo = {
    businessContact: data.businessContact,
    companyRegistrationNumber: data.companyRegistrationNumber,
    profileImage: data.profileImage,
    email: 'saymymin@kakao.com'
  }
  
  const clickHandler = () => {
    refInput.current?.click()
  }

  const changeHanler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newImg = e.target.files![0]
    const url = URL.createObjectURL(newImg)
    setProfileImg(url)
  }

  const submitHandler = (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault()
    setTimeout(() => {
      // const newData = { ...hostInfo, profileImage: profileImg }
      // const mutaion = hostModify('saymymin@kakao.com', data.hostId!, newData)
      // mutaion.mutate(newData as any) 
    }, 500)
    }
  

  return (
    <>
      <S.Form onSubmit={submitHandler}>
        <S.Img src={profileImg} alt='호스트 이미지' onClick={clickHandler}></S.Img>
        <S.Input type='file' name='profileImg' onChange={changeHanler} ref={refInput} />
      </S.Form>
    </>
  )
}

const S: any = {}

S.Form = styled.form`
  display: flex;
  justify-content: center;
  align-items: end;
  position: relative;
`

S.Img = styled.img`
  width: 13rem;
  height: 13rem;
  background: var(--color-gray1);
  border-radius: 50%;
  border: 2px solid var(--color-gray2);
  cursor: pointer;
  &:hover {
    transform: scale(1.08);
    transition: all 0.3s ease;
  }
`
S.Input = styled.input`
  position: absolute;
  width: 1;
  height: 1;
  margin: -1;
  overflow: hidden;
  clip-path: polygon(0 0, 0 0, 0 0);
`

