import { useContext, useState } from "react";
import Button from "react-bootstrap/Button"
import Modal from "react-bootstrap/Modal"
import Container from "react-bootstrap/Container"
import { CartContext } from "./cartContext";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Badge from "react-bootstrap/Badge";
import { CartProduct } from "../products/cartView";
import { apiOrderSubmit } from "./dataFetch";

export function Cart() {
    const [show, setShow] = useState(false);
    const cart = useContext(CartContext);
    const totalItems = cart.items.reduce(((total, item) => total += item.quantity), 0)

    function toggleCartShow() {
        setShow(!show)
    }

    function handleClose() {
        setShow(false)
    }

    function handleOrderSubmit(response, status) {
        if (status === 201) {
            console.log(response.redirect_url)
            window.location.href = response.redirect_url
        }
        else {
            alert("error submitting order")
        }
        console.log(response)
    }

    function submitCartOrder() {
        const data = {
            orderDetails: cart.items
        }
        apiOrderSubmit(data, handleOrderSubmit)
    }

    return <Container>
        <Button variant="primary" onClick={toggleCartShow}>
            Cart <Badge bg="secondary">{totalItems}</Badge>
        </Button>
        {show ? <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Your Shopping Cart</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Container>
                    {cart.items.map((currItem, index) => {
                        return <CartProduct item={currItem} onAdd={cart.addToCart} onRemove={cart.removeFromCart} key={index} />
                    })}
                </Container>
            </Modal.Body>
            <Modal.Footer>
                <Container>
                    <Row>
                        <Col>
                            Subtotal: ${cart.getTotalCost().toFixed(2)} USD
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Button onClick={submitCartOrder}>Checkout</Button>
                        </Col>
                        <Col>
                            <Button onClick={handleClose}>Close</Button>
                        </Col>
                    </Row>
                </Container>
            </Modal.Footer>
        </Modal> : null}
    </Container>
}