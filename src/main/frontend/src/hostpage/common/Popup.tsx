import { useNavigate } from 'react-router-dom'
import styled from "styled-components"
import Button, { CloseButton } from './Button'
import ico from "../../assets/jjangkoo.png"

interface PopupProps {
  text: string,
  subText: string,
  page: string,
  toPage: string,
  close?: () => void
}

export default function Popup(props: PopupProps) {
  const { text, subText, page, toPage, close } = props
  const nav = useNavigate()
  
  return(
    <S.Layout>
      <S.Div>
        <S.CloseDiv>
          <CloseButton onClick={close}/>
        </S.CloseDiv>
        <S.TextDiv>
          <S.Text>{text}</S.Text>
          <S.SubText>{subText}</S.SubText>
        </S.TextDiv>
        
          <S.Img src={ico} alt="캐릭터아이콘" />
        
        <Button type='button' text={page} onClick={() => nav(toPage)}/>
      </S.Div>
    </S.Layout>
  )
  }
  //style

  const S: any = {}

  S.Layout = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    width: 100%;
    height:100vh;
    background: rgba(0, 0, 0, 0.75);
    z-index: 9999;
  `

  S.Div = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 48rem;
    height: 55rem;
    border-radius: 2.5rem;
    background: var(--color-white);
  `
  S.CloseDiv = styled.div`
    position: absolute;
    top: 3%;
    right: 5%;
    cursor: pointer;
  ` 
  S.TextDiv = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
  `

  S.Text = styled.span`
    font-size: 2.2rem;
    font-weight: 700;
    margin-bottom: 2.2rem;
  `
  S.SubText = styled.span`
    width: 25rem;
    text-align: center;
    vertical-align: middle;
    line-height: 1.3;
    font-size: 1.7rem;
    font-weight: 500;
    white-space: normal;
  `
  S.Img = styled.img`
    width: 6rem;
    margin-top: 4rem;
    margin-bottom: 6rem;
  `
  
 