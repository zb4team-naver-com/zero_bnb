
import styled from "styled-components";
import HostMenuTab from "../component/HostMenuTab";
import HostRoomTab from "../component/HostRoomTab";
import { Outlet } from "react-router-dom";

export default function HostPage() {
 return (
    <>
      <S.Div>
        <HostMenuTab />
        <Outlet />
      </S.Div>
    </>
  )
}
  


//style

const S: any = {}

S.Div = styled.div`
  display: flex;
  align-items: start;
  width: 120rem;
  margin: 0 auto;
  margin-top: 7rem;
`
