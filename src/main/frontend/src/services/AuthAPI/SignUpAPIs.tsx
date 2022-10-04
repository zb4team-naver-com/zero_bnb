import axios from "axios"

interface UserAuthSignUpInput {
	email: string
	password: string
	name: string
	birth: string
	phone: string
}

const instance = axios.create({
	baseURL: "http://localhost:8000",
})

const fetchSignUp = (props: UserAuthSignUpInput) => {
	return instance.post("./signup", props)
}

export { fetchSignUp }
