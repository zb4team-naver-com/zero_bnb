import { useState, ChangeEvent } from "react"

const useInput = (initialValue: string) => {
	const [value, setValue] = useState(initialValue)

	const onChange = (e: ChangeEvent<HTMLInputElement>) => {
		setValue(e.target.value)
	}

	const reset = () => setValue("")

	return { value, setValue, onChange, reset }
}

export default useInput
