import styled from "styled-components"
import { Outlet } from "react-router-dom"
import HostMenuTab from "../components/layout/HostMenuTab"

export default function HostTab() {
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
