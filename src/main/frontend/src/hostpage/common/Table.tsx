import styled from "styled-components"


interface Props{
  headList: string[],
  desList: string[]
}


export default function Table(props: Props) {
  const { headList, desList } = props

  const keyList = [ "dfas", "shg", "kkh", "qwt", "itb", "qppy", "rios", "vmkl" ]
  return (
    <div>
      {headList
        .map( (list, idx) => {
          return(
            <S.Div key={keyList[idx]}>
             <S.Span>{list}</S.Span>
             <S.Content>{desList[idx]}</S.Content>
            </S.Div>
          )
        })
      }
    </div>
  ) 
}
              



const S: any = {}

S.Div = styled.div`
  display: flex;
  flex-direction: column;
  align-items: left;
  height: 4rem;
  margin: 1.2rem 0;
`

S.Span = styled.span`
  width: 11rem;
  margin-bottom: 0.8rem;
  text-align: left;
  font-size: 1.3rem;
  font-weight: 700;
`
S.Content = styled.span`
  display: inline-block;
  height: 2.2rem;
  font-size: 1.3rem;
  text-align: left;
`

S.Label = styled.label`
display: inline-block;
width: 14rem;
height: 2.2rem;
margin-right: 1rem;
text-align: left;
font-weight: border;
`