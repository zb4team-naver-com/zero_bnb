import { createContext, ReactNode } from "react"

interface initvalue {
	type: string
	address: string
	description: string
	locationPosition:
		| {
				latitude: string
				longitude: string
		  }
		| {}
	name: string
	notice: string
	policy: string
	eventInputs: { description: string }[] | never
	popularFacilityServiceInputs: { popularFacilityServiceType: number }[] | never
	accommodationImageInputs: { url: string }[] | never
	imgaes: string[] | never
}

const initValues: initvalue = {
	type: "",
	address: "",
	description: "",
	locationPosition: {},
	name: "",
	notice: "",
	policy: "",
	eventInputs: [],
	popularFacilityServiceInputs: [],
	accommodationImageInputs: [],
	imgaes: [],
}

export const myContext = createContext(initValues)

export default function RoomRegisterContext({
	children,
}: {
	children: ReactNode
}) {
	return <myContext.Provider value={initValues}>{children}</myContext.Provider>
}
