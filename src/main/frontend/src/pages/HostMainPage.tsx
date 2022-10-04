import { useState } from "react"

import HostInfoTab from "../components/host/HostInfoTab"
import HostRoomTab from "../components/host/HostRoomTab"
import HostTap from "../components/host/HostTab"
import Popup from "../components/common/Popup"

export default function HostMainPage() {
	const [tabState, setTabState] = useState(true)

	const props = {
		trueTap: () => setTabState(true),
		falseTap: () => setTabState(false),
	}

	return (
		//  <>
		//    {data.active
		//      ?
		<>
			<HostTap trueTab={props.trueTap} falseTab={props.falseTap} />
			<div>{tabState ? <HostInfoTab /> : <HostRoomTab />}</div>
		</>
		//       : <Popup
		//         text="호스트가 아닙니다."
		//         subText="호스트가 되어보세요"
		//         page="호스트 틍록 하기"
		//         toPage="host/register"/>
		//     }
		// </>
	)
}

//style
