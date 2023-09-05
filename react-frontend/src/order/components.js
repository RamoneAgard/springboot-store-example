import React, { useContext, useState } from "react";
import Modal from "react-bootstrap/Modal";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form"
import Button from "react-bootstrap/Button";
import { validateEmail, validateName } from "../utils/validation";
import { CartContext } from "../cart/cartContext";
import { apiOrderSubmit } from "./dataFetch";


export function OrderForm(props) {

  const [orderFormData, setOrderFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    isEmailError: false,
    isFirstNameError: false,
    isLastNameError: false,
    isFormValid: false,
  })

  const cart = useContext(CartContext)

  function handleInput(e){
    const value = e.target.value
    const name = e.target.name
    console.log("name:"+name+" value:"+value)
    let valid = false;
    switch(name){
      case "email":
        valid = validateEmail(value)
        setOrderFormData({
          ...orderFormData, 
          email: value, 
          isEmailError: !valid,
          isFormValid: (valid) && (!orderFormData.isFirstNameError) && (!orderFormData.isLastNameError)
        });
        //console.log(validateEmail(value))
        break;
      case "lastname":
        valid = validateName(value)
        setOrderFormData({
          ...orderFormData, 
          lastName: value, 
          isLastNameError: !valid,
          isFormValid: (valid) && (!orderFormData.isFirstNameError) && (!orderFormData.isEmailError)
        })
        //console.log(validateName(value))
        break;
      case "firstname":
        valid = validateName(value)
        setOrderFormData({
          ...orderFormData, 
          firstName: value, 
          isFirstNameError: !(validateName(value)),
          isFormValid: (valid) && (!orderFormData.isLastNameError) && (!orderFormData.isEmailError)
        })
        //console.log(validateName(value))
        break;
      default:
        break;
    }
    //validateForm()
  }

  function handleOrderSubmit(response, status){
    if(status === 201){
      console.log(response.redirect_url)
      window.location.href = response.redirect_url
    }
    else{
      alert("error submitting order")
    }
    console.log(response)
  }

  function submitForm(e){
    e.preventDefault()
    const orderData = {
      customer: {
        name: orderFormData.firstName + " " + orderFormData.lastName,
        email: orderFormData.email
      },
      orderDetails: cart.items
    }

    apiOrderSubmit(orderData, handleOrderSubmit)
  }



  return (
    <Form onSubmit={(event) => submitForm(event)}>
      <Form.Group controlId="formBasicText">
        <Form.Text muted hidden={!orderFormData.isFirstNameError || !orderFormData.isLastNameError}>Please Enter a Valid First and Last Name</Form.Text>
        <br/>
        <Form.Label>First Name:</Form.Label>
        <Form.Control onChange={(event) => handleInput(event)} isInvalid={orderFormData.isFirstNameError} type="text" name="firstname" maxLength={12} placeholder="John" required />
        <Form.Label>Last Name:</Form.Label>
        <Form.Control onChange={(event) => handleInput(event)} isInvalid={orderFormData.isLastNameError} type="text" name="lastname" maxLength={12} placeholder="Doe" required />
      </Form.Group>
      <Form.Group controlId="formBasicEmail">
        <Form.Text muted hidden={!orderFormData.isEmailError}>Please Enter a Valid Email</Form.Text>
        <br/>
        <Form.Label> Email Address: </Form.Label>
        <Form.Control onChange={(event) => handleInput(event)} isInvalid={orderFormData.isEmailError} type="email" name="email" placeholder="name@example.com" required />
        <Form.Text muted>We will never share your email!</Form.Text>
      </Form.Group>
      <Button variant="primary" type="sumbit" disabled={!orderFormData.isFormValid}>
        Go to Payment
      </Button>
    </Form>
  )
}