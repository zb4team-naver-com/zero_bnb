import React from "react"
import styled from "styled-components"

interface Radio {
  text: string
  name: string
  lists: string[]
  onChange: (e: React.ChangeEvent<any>) => void
}

export function Radio(props: Radio) {
  const { text, name, lists, onChange } = props

  return(
    <>
      <Common.Div>
        <Common.Legend>{text}</Common.Legend>
        <div>
          {lists.map((list) => {
            return (
              <R.Div>
                <R.Label htmlFor={list} key={`$*${list}`}>{list}</R.Label>
                <input type="radio" id={list} name={name} onChange={onChange}/>
              </R.Div>
            )
          })}
        </div>
      </Common.Div>
    </>
  )
}

interface Input {
  id: string
  text: string
  name: string
  placeholder: string
  onChange: (e: React.ChangeEvent<any>) => void
  lists?: string[]
}

export function Input(props: Input) {
  const { id, text, name, placeholder, onChange, lists } = props
  return (
    <>
      <Common.Div>
        <Common.Label htmlFor={id}>{text}</Common.Label>
        {lists?.map(list => {
          return <Text.Input 
                  key={list}
                  type="text" 
                  id={id} 
                  name={name} 
                  placeholder={placeholder} 
                  maxLength={255}
                  onChange={onChange} />
        })}
        <Text.Input type="text" id={id} name={name} placeholder={placeholder} maxLength={255} onChange={onChange} />
      </Common.Div>
    </>
  )
}

interface TextArea {
  id: string
  text: string
  name: string
  onChange: (e: React.ChangeEvent<any>) => void
}

export function TextArea(props: TextArea) {
  const { id, text, name, onChange } = props

  return (
    <>
      <Common.Div>
        <Common.Label htmlFor={id}>{text}</Common.Label>
        <Text.TextArea 
          id={id} 
          name={name} 
          onChange={onChange} />
      </Common.Div>
    </>
  )
}

interface CheckBox {
  text: string
  name: string
  lists: string[]
  onChange: (e: React.ChangeEvent<any>) => void
}

export function CheckBox(props: CheckBox) {
  const { text, name, lists, onChange } = props

  return (
    <>
      <Common.Div>
        <Common.Legend>{text}</Common.Legend>
        <Ch.Div>
          {lists.map((list) => {
            return (
              <Ch.SelectArea key={`%&${list}`}>
                <Ch.Label htmlFor={list}>{list}</Ch.Label>
                <input type="checkbox" id={list} name={name} onChange={onChange} />
              </Ch.SelectArea>
            )
          })}
        </Ch.Div>
      </Common.Div>
    </>
  )
}

const Common: any = {}

Common.Div = styled.div`
  flex-shrink: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 60rem;
  height: 32rem;
  margin-bottom: 4rem;
`
Common.Input = styled.input`
  width: 300rem;
`
Common.Legend = styled.legend`
  margin-bottom: 3rem;
  font-weight: 600;
  color: var(--main-color2)
`
Common.Label = styled.label`
  margin-bottom: 2.2rem;
  font-weight: 600;
  color: var(--main-color2)
`

const R: any = {}

R.Div = styled.span`
  display: inline-block;
  margin-right: 1.2rem;
`
R.Label = styled.label`
  font-size: 1.4rem;
`

const Text: any = {}

Text.Input = styled.input`
  width: 40rem;
  height: 3rem;
  margin-top: 1.2rem;
  padding-left: 1rem;
  border: 0;
  border-bottom: 1px solid var(--color-gray0);
`

Text.TextArea = styled.textarea`
  width: 40rem;
  height: 8rem;
  margin-top: 1.2rem;
  padding: 1rem;
  resize: none;
  border: 1px solid var(--color-gray0);
  border-radius: 1rem;
`

const Ch: any = {}

Ch.Div = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
`
Ch.SelectArea = styled.div`
  width: 12rem;
  margin-bottom: 1.3rem;
`
Ch.Label = styled.label`
  font-size: 1.4rem;
`