import axios from "axios"
import { useState } from "react"

import styled from "styled-components"
import plus from "../img/plus.svg"

export default function ImageUpload() {
  const [ file, setFile ] = useState<any>("")

  const changeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFile( e.target.files![0] )
      
      }
    
  
  const submitHandler = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault()
    const formData = new FormData()
    formData.append('file', file )
    console.log(formData.get('file'))
    const res = await axios({
      method: 'post',
      url: "http://localhost:8000/images",
      data: formData,
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })
    console.log(res.data)

  }

  return (
    <S.Form action="/images" method="POST" encType="multipart/form-data">
      <label htmlFor="images"></label>
      <S.Input type="file" 
        name="images" 
        id="images" 
        accept="image/*" 
        multiple onChange={changeHandler}/>
      <S.Submit type="submit" value='확인' />
      </S.Form>
  )
}

const S: any = {}

S.Form = styled.form`
  display: flex;
  align-items: end;
`
S.Input = styled.input`
  position: relative;
  &::-webkit-file-upload-button {
    width: 7rem;
    height: 6rem;
    margin-right: 2rem;
    border-radius: 2rem;
  }
  &::before {
    content: '+';
    display: block;
    position: absolute;
    bottom: 0;
    left: 0;
    width: 7rem;
    height: 6rem;
    padding: 1rem 0;
    border-radius: 2rem;
    border: 2px solid var(--main-color2-0);
    background: var(--color-white);
    font-size: 3.5rem;
    text-align: center;
    color: var(--main-color2-0);
    cursor: pointer;
  }

`

S.Submit = styled.input`
  display: block;
  margin-left: 5rem;
  border: none;
  background: none;
  font-size: 1.6rem;
  cursor: pointer;
  color: var(--main-color2);
  &:hover {
    width: 5rem;
    height: 3rem;
    border-radius: 2rem;
    font-weight: 700;
    color: var(--main-color2-2);
    box-shadow: 1px 1px 2px var(--color-gray0);
  }
`