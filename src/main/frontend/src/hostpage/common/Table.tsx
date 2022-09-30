import styled from "styled-components"


interface Props{
  headList: string[],
  desList: string[]
}


export default function Table(props: Props) {
  const { headList, desList } = props
  return (
    <div>
      {headList
        .map( (list, idx) => {
          return(
            <S.Div>
             <S.Span key={list}>{list}</S.Span>
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
  align-items: end;
  margin: 1.5rem 0;
`

S.Span = styled.span`
  display: inline-block;
  width: 14rem;
  height: 2.2rem;
  margin-right: 1rem;
  text-align: left;
  font-weight: border;
`
S.Content = styled.span`
  display: inline-block;
  height: 2.2rem;
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