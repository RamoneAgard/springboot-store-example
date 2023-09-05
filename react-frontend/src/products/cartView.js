import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Image from 'react-bootstrap/Image'
import Button from 'react-bootstrap/Button'
import Badge from "react-bootstrap/Badge";

export function CartProduct(props) {
    const cartItem = props.item
    const imgURL = 'https://picsum.photos/250/200?random=2'
    
    function handleIncreaseQuantity() {
        if(cartItem.quantity < 10){
            props.onAdd(cartItem.product, cartItem.color)
        }
    }

    function handleDecreaseQuantity() {
        if(cartItem.quantity > 1){
            props.onRemove(cartItem, false)
        }
        else{
            props.onRemove(cartItem, true)
        }
    }

    return <Row>
        <Col>
            <Row>
                <Col>
                    <Image src={imgURL}/>
                </Col>
                <Col>
                    <h3>{cartItem.product.productName}</h3>
                    <p>Plastic Type: {cartItem.color.filamentType} | Color: {cartItem.color.name} </p>
                </Col>
            </Row>
        </Col>
        <Col>
            <Row>
                <Button variant='primary' onClick={handleIncreaseQuantity}>+</Button>
                <Badge bg="secondary">{cartItem.quantity}</Badge>
                <Button variant='primary' onClick={handleDecreaseQuantity}>-</Button>
            </Row>
        </Col>
    </Row>
}