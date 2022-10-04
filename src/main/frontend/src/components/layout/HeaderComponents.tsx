import React from "react"
import { Link } from "react-router-dom"
import styled from "styled-components"
import storage from "../../services/AuthAPI/api"

export default function HeaderComponents() {
	const isLogined = storage.get({ key: "token" }) ? true : false
	return <></>
}
