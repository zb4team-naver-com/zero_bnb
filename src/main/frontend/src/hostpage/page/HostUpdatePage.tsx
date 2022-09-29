import styled from "styled-components";
import ico from "../img/plus.svg"

import { FixTab } from "../component/HostTab";
import Button from "../common/Button";
import { hostRegisterForm, initialValue, Name_ } from "../dataForm/hostRegister";
import { useReducer, useRef } from "react";

import getInfo from "../api/host_axios";
import { useNavigate } from "react-router-dom";
import hostQuery from "../api/host_Query";

const infoForm = hostRegisterForm

const buttonStyle = {
  width: 6 + 'rem',
  height: 4 + 'rem',
  marginTop: 3 + 'rem',
  marginRight: 1.5 + 'rem'
}

interface Action {
  type: string
  payload?: string
}

const reducer = (state: any, action: Action) => {
  switch(action.type) {
    case 'profileImage': 
      return { ...state, profileImage: action.payload}
    case 'businessContact':
      return { ...state, businessContact: action.payload}
    case 'companyRegistrationNumber':
      return { ...state, companyRegistrationNumber: action.payload}
    case 'email':
      return { ...state, email: action.payload}
    case 'reset':
      return {}
  }
} 

export default function HostUpdatePage() {
  const{ data } = getInfo('saymymin@kakao.com')
  console.log(data)
  const init = data? {
    profileImage: data.profileImage,
    name: '....',
    businessContact: data.businessContact,
    companyRegistrationNumber: data.companyRegistrationNumber,
    email: 'saymymin@kakao.com'
  } : initialValue

  
  const [ state, dispatch ] = useReducer(reducer, init)
  const refInput = useRef<HTMLInputElement>(null)
  const nav = useNavigate()

  const clickHandler = () => {
      refInput.current?.click()
  }

  const changeImgHanler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newImg = e.target.files![0]
    const url = URL.createObjectURL(newImg)
    dispatch({type: 'profileImage', payload: url})
  }

  const changeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch({type: e.target.name, payload: e.target.value})
  }

  const resetHandler = () => {
    dispatch({type: 'reset'})
    nav('/host')
  }

  const hostUpdate = hostQuery()
  const mutaion = hostUpdate.hostModify(1, state)

  const submitHandler = (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault()
    mutaion.mutate()
    }

  return (
    <>
      <FixTab/>
      <S.Form onSubmit={submitHandler}>
        <S.ImgDiv>
          <S.Ico src={ico} alt='이미지를 클릭해주세요' />
          <S.Img src={state.profileImage} alt='호스트 이미지' onClick={clickHandler}></S.Img>
          <S.ImgInput type='file' name='profileImg' onChange={changeImgHanler} ref={refInput} />
        </S.ImgDiv>
        <S.Div>
          <S.NameDiv>
            <span>호스트명</span>
            <S.Span>{init.name}</S.Span>
          </S.NameDiv>
          {infoForm
            .map(({label_, name_}) => {
              if(label_ !== '이미지' && label_ !== '호스트명')
              return (
                <S.List>
                  <S.Label htmlFor={label_}>{label_}</S.Label>
                  <S.Input
                    key={label_}
                    name={name_}
                    placeholder={state[(name_ as Name_)]}
                    onChange={changeHandler}>
                  </S.Input>
                </S.List>
              )
            })}
        </S.Div>
          <div>
            <Button type='reset' text='취소' theme="line" onClick={resetHandler} style={buttonStyle}/>
            <Button type='submit' text='확인' style={buttonStyle}/>
          </div>
      </S.Form>
              
    </>
  )
}

//style

const S: any = {}

S.Form = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 13rem;
  position: relative;
`
S.ImgDiv = styled.div`
  position: relative;
  width: 15rem;
  height: 15rem;
`

S.Ico = styled.img`
  position: absolute;
  top: 1.1rem;
  left: -1.2rem;
  width: 1.8rem;
  color: var(--main-color2);
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
S.ImgInput = styled.input`
  position: absolute;
  width: 1;
  height: 1;
  margin: -1;
  overflow: hidden;
  clip-path: polygon(0 0, 0 0, 0 0);
  `

S.Div = styled.div`
  margin-top: 5rem;
`

S.List = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 40rem
`

S.Label = styled.label`
  display: block;
  margin-right: 1rem;
  margin: 1.5rem 0;
  text-align: left;
`

S.Input = styled.input`
  width: 22rem;
  height: 3rem;
  padding-bottom: 0.5rem;
  border: none;
  border-bottom: 1px solid var(--color-gray0);
  outline: none;
  &::placeholer {
    color: var(--color-gray0);
  }
  &:focus {
    transform: scal(1.2);
    outline: var(--color-gray0)
  }
`
S.NameDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 40rem  
`
S.Span = styled.span`
  width: 22rem;
  height: 3rem;
  padding-bottom: 0.5rem;
  border: none;
  border-bottom: 1px solid var(--color-gray0);
  outline: none;
`
