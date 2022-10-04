import styled from "styled-components"
import { hostRegisterForm } from "../dataForm/hostRegister"
import Button from "../common/Button"

interface Props {
  onChange: ((e: React.ChangeEvent<any>) => void)[]
}

const dataForm = hostRegisterForm

export default function HostRegisterForm(props: Props) {
  const  [ changeHandler ] = props.onChange
  return (
    <>
      <S.Div>
        {dataForm.map(({ name_, type, placeholder, required }) => {
          if(name_ !== "profileImage")
          return <S.Input 
                  key={name_}
                  name={name_}  
                  placeholder={placeholder}
                  type={type}
                  required={required}
                  onChange={changeHandler}
                  />})}
        </S.Div>
        <Button type='submit' text="등록" style={{width: 12 + 'rem'}}/>
      </>
  )
}

const S: any = {}

S.Div = styled.div`
  margin-top: 3rem;
  margin-bottom: 8rem;
`
S.Input = styled.input`
  display: block;
  width: 310px;
  height: 45px;
  padding-left: 10px;
  margin-bottom: 1.2rem;
  border: none;
  border-bottom: 1px solid var(--color-gray0);
  font-size: 1.4rem;
  font-family: var(--font-family-noto);
`