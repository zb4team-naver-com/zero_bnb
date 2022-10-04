import styled from "styled-components";
import getInfo from "../api/host_axios";

import Table from "../common/Table"
import { initialValue } from "../dataForm/hostRegister";
import React, { useState } from "react";
import { imgUpload } from "../api/imgUpload_axios";
import hostQuery from "../api/host_Query";



export default function HostProfile() {
  const [ hover, setHover ] = useState(false)
  const [ openInfo, setOpenInfo ] = useState(false)
  const { hostModify } = hostQuery()
  const { data } = getInfo('saymymin@kakao.com') 
  //
  const message = <M.Div>클릭해서 이미지를 변경하세요</M.Div>
  
  const init = initialValue
  const renderValue = data ? {
    profileImage: data.profileImage,
    name: 'userName',
    businessContact: data.businessContact,
    companyRegistrationNumber: data.companyRegistrationNumber,
    email: 'saymymin@kakao.com' 
  } : init

  const [ imgsrc, setImgsrc ] = useState<File>()

  const props = {
    headList: ['호스트명', '연락처', '사업자 등록 번호', '이메일'],
    desList: Object.values(renderValue).slice(1)
  }

  const clickImg = (e: React.MouseEvent<HTMLImageElement>) => {
    const $input = (e.target as HTMLImageElement).nextSibling as HTMLInputElement
    $input!.click()
  } 

  const onMouse = () => {
    setHover(true)
    setTimeout(() => {setHover(false)}, 1200)
  }

  const clickOpne = () => {
    setOpenInfo(!openInfo)
  }

  const changeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setImgsrc(e.target.files![0])
  }

  const submitHandler = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault()
    const url = imgUpload(imgsrc!)
    const mutation = hostModify(data.hostId, {profileImage: url})
    mutation.mutate()
  }

  return (  
    <>
    <S.Div>
      <S.Form onSubmit={submitHandler}>
        {hover && message }
        <S.Img 
          src={imgsrc? URL.createObjectURL(imgsrc!) : renderValue.profileImage} 
          alt='호스트 이미지' 
          onClick={clickImg} 
          onMouseMove={onMouse}/>
        <S.Input type="file" name="images" id="images" accept="image/*" onChange={changeHandler}/>
        <S.ImgConfirm type="submit" value="확인"/>
      </S.Form>
      <S.Button type="button" onClick={clickOpne}>{openInfo ? "닫기" : "내 정보 보기"}</S.Button>
      {openInfo &&
        <S.Info>
          <S.Table>
            <Table {...props}/>
          </S.Table>
        </S.Info>
      }
      </S.Div>
    </>
  )
}

const S: any = {}

S.Div = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 25rem;
  margin: 0 auto;
`

S.Form = styled.form`
  position: relative;
  margin-left: 1rem;
`

S.Button = styled.button`
  width: 10rem;
  height: 3.8rem;
  margin-top: 2rem;
  margin-left: -3.5rem;
  background: var(--main-color1);
  border: 2px solid var(--main-color1-1);
  border-radius: 2rem;
  font-size: 1.3rem;
  color: var(--color-white);
  &:hover,
  &:focus {
    box-shadow: 1px 1px 2px var(--color-gray0);
  }
`

S.Info = styled.div`
  position: absolute;
  top: 15rem;
  left: 5rem;
  width: 30rem;
  margin-top: 2rem;
`

S.Table = styled.div`
  display: flex;
  flex-direction: column;
  align-items: left;
  justify-content: center;
  height: 25rem;
  padding: 3rem;
  background: #fffcf5;
  border: 1px solid #fcf4e1;
  border-radius: 2rem;
  box-shadow: -1px 1px 2px var(--color-gray0);
`

S.Img = styled.img`
  width: 10rem;
  height: 10rem;
  background: var(--color-gray0);
  border-radius: 50%;
  border: 1px solid var(--color-gray1);
  cursor: pointer;
`

S.Input = styled.input`
  position: absolute;
  width: 1;
  height: 1;
  margin: -1;
  overflow: hidden;
  clip-path: polygon(0 0, 0 0, 0 0);
`

S.ImgConfirm = styled.input`
  border: none;
  background: none;
  color: var(--main-color2);
  font-size: 1.4rem;
  cursor: pointer;
  &:hover {
    font-weight: 700;
  }
`

const M: any = {}

M.Div = styled.div`
  position: absolute;
  top: -6rem;
  right: -16rem;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 25rem;
  height: 5rem;
  background: var(--color-white);
  border-radius: 2rem;
  border: 1px solid var(--color-gray0);
  font-size: 1.5rem;
  &::before {
    content: "";
    display: block;
    width: 1.5rem;
    height: 1.5rem;
    position: absolute;
    bottom: -0.8rem;
    left: 2rem;
    background: var(--color-white);
    border-left: 1px solid var(--color-gray0);
    border-bottom: 1px solid var(--color-gray0);
    transform: rotate(-45deg);
  }
`
