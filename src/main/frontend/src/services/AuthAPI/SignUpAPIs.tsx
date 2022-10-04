import axios from "axios"

interface UserAuthSignUpInput {
	email: string
	password: string
	name: string
	birth: string
	phone: string
}

const instance = axios.create({
	baseURL: "http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com",
})

const fetchSignUp = (props: UserAuthSignUpInput) => {
	return instance.post("./signup", props)
}

export { fetchSignUp }
