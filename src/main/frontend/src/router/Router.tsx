import React from 'react'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from '../pages/Header'

const Router = () => {
  return(
  <BrowserRouter>
    <Routes>
     <Route element={<Header/>}></Route>
    </Routes>
  </BrowserRouter>
)
} 

export default Router
