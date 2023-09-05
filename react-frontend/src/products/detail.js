import React, { useState } from 'react';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import Modal from 'react-bootstrap/Modal';
import Image from 'react-bootstrap/Image';
import Form from 'react-bootstrap/Form'



export function ProductItem(props){
    //const [item, setItem] = useState(props.item);
    const item = props.item;
    const dex = props.dex;
    const [showDetail, setShowDetail] = useState(false);
    const [showFilamentMessage, setShowFilamentMessage] = useState(false)
    //const imgURL = `https://picsum.photos/250/200?random=${dex+1}`
    const [filamentPick, setFilamentPick] = useState(null)
    const [quantity, setQuantity] = useState(1)
    var clearOptions = null;


    function setClearOptions(func) {
        clearOptions = func
    }

    function resetOptions() {
        clearOptions()
    }
    
    function resetToDefaults() {
        setFilamentPick(null)
        setShowFilamentMessage(false)
        setQuantity(1)
        resetOptions()
    }

    function handleShowDetail() {
        setFilamentPick(null)
        resetOptions()
        setShowFilamentMessage(false)
        setShowDetail(!showDetail)
        setQuantity(1)
    }

    function handleFilamentPick(color) {
        if(color !== null){
            setShowFilamentMessage(false)
        }
        setFilamentPick(color)
        console.log(color)
    }

    function handleAddToCart() {
        if(filamentPick === null){
            setShowFilamentMessage(true)
        } else {
            console.log(filamentPick)
            props.onAdd(item, filamentPick)
            resetToDefaults()
        }
    }

    
  
    return <React.Fragment>
    <Col xs={12} md={6} lg={4} className='py-3'>
      <Card >
        {/* <Container > */}
        <Card.Img variant='top' src={item.imageUrl} onClick={handleShowDetail} fluid/>
        <Card.Body>
          <Card.Title onClick={handleShowDetail}>{item.productName}</Card.Title>
          {showFilamentMessage && !showDetail? <small className='text-danger fst-italic'>Please pick a filament and color</small> : null}
          <FilamaentOptions filaments={item.filaments} handleFilamentPick={handleFilamentPick} setResetFunction={setClearOptions}/>
          {/* <Card.Text>{item.description}</Card.Text> */}
        </Card.Body>
        {/* </Container> */}
        <Card.Footer> 
            <Row className='align-middle'>
                <p className='col my-auto fw-semibold'> USD ${item.price.toFixed(2)} </p>
                <Button className='col' variant='success' onClick={handleAddToCart}>Add to Cart</Button>
            </Row>
        </Card.Footer>
      </Card>
    </Col>
    {showDetail ? 
        <ProductDetail item={item} show={showDetail} showFilamentMessage={showFilamentMessage} handleClose={handleShowDetail} handleAddToCart={handleAddToCart} handleFilamentPick={handleFilamentPick}/> 
        : null} 
    </React.Fragment>
  }

  export function ProductDetail(props){
    const item = props.item;
    const show = props.show;
    const handleAddToCart = props.handleAddToCart
    const showFilamentMessage = props.showFilamentMessage
    const handleClose = props.handleClose

    return <Modal size="lg" fullscreen="md-down" show={show} onHide={handleClose}>
        <Modal.Header closeButton>
            <Modal.Title>{item.productName}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <Container>
                <Row xs={1} md={2}>
                    <Col className='p-auto'>
                        <Image src={item.imageUrl} fluid/>
                    </Col>
                    <Col>
                        <Row>
                            <Col xl={3}> Description:</Col>
                            <Col xl={9}> {item.description}</Col>
                        </Row>
                        <Row>
                            <Col xl={3}> Price:</Col>
                            <Col xl={9}> USD ${item.price.toFixed(2)}</Col>
                        </Row>
                    </Col>
                </Row>
                {/* <Row>
                    <Col xl={3}> Description:</Col>
                    <Col xl={9}> {item.description}</Col>
                </Row>
                <br/>
                <Row>
                    <Col xl={3}> Price:</Col>
                    <Col xl={9}> USD ${item.price.toFixed(2)}</Col>
                </Row> */}
            </Container>
        </Modal.Body>
        <Modal.Footer>
            <Container>
            <Row>
                <Col>
                    {showFilamentMessage && show? <small className='text-danger font-italic'>Please pick a filament and color</small> : null}
                    <FilamaentOptions filaments={item.filaments} handleFilamentPick={props.handleFilamentPick} setResetFunction={()=>{}}/>
                </Col>
                <Col className='d-flex justify-content-center'>
                    <Button variant='success' onClick={handleAddToCart}>Add to Cart</Button>
                </Col>
            </Row>
            </Container>
        </Modal.Footer>
    </Modal>
  }

  export function FilamaentOptions(props){
    const filaments = props.filaments
    const [colorList, setColorList] = useState(null);
    const [showColors, setShowColors] = useState(false)
    const selectRef = React.createRef()
    const setFilamentOption = props.handleFilamentPick
    props.setResetFunction(resetOptionSelect)

    function resetOptionSelect() {
        setColorList(null)
        setShowColors(false)
        selectRef.current.selectedIndex = null
    }
    

    function showColorOptions(event) {
        let dex = event.target.value
        if(dex === "default"){
            setShowColors(false)
        }
        else {
            console.log(event.target.value);
            setColorList(filaments[event.target.value].colors)
            console.log(filaments[event.target.value].colors)
            setShowColors(true)
        }
    }

    function pickColorOption(event) {
        console.log("in color pick: ", event)
        let dex = event.target.value
        if(dex !== "default"){
            setFilamentOption(colorList[dex])
        } else {
            setFilamentOption(null)
        }
    }

    return <Row xs={1} className='d-flex flex-row flex-wrap'>
        <Col>
            <Form.Select ref={selectRef} onChange={(event)=>{showColorOptions(event)}}>
                <option value="default" selected hidden disabled>Select a Filament</option>
                {filaments.map((fil, index) => {
                    return <option key={index} value={index}>{fil.type}</option>
                })}
            </Form.Select>
        </Col>
        <Col>
            {showColors? 
            <Form.Select size="sm" onChange={(event)=>{pickColorOption(event)}}>
                <option value="default" selected hidden disabled>Select a Color</option>
                {colorList.map((color, index) => {
                    return <option key={index} value={index}>{color.name}</option>
                })}
            </Form.Select>
            : null}
        </Col>
    </Row>
  }