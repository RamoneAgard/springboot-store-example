import logo from './logo.svg';
import './App.css';
import React, { useContext } from 'react';
import { useState, useEffect } from 'react';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import { ProductContext, ProductProvider } from './products/productContext';
import { CartProvider } from './cart';
import { NavbarComponent } from './base/NavComponent';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { OrderForm } from './order';
import { CartContext } from './cart/cartContext';
import { ProductList } from './products/components';



// function ProductItem(props){
//   //const [item, setItem] = useState(props.item);
//   const item = props.item;
//   const dex = props.dex;
//   const imgURL = `https://picsum.photos/250/200?random=${dex+1}`

//   const mapFilaments = (list) => {
//     return <Card.Subtitle className='text-muted'> {list.map((curItem, index) => {
//       return <p>{curItem}</p>
//     })} </Card.Subtitle>
//   }

//   return <Col xs={12} md={6} lg={4}>
//     <Card>
//       <Card.Img variant='top' src={imgURL}/>
//       <Card.Body>
//         <Card.Title>{item.productName}</Card.Title>
//         {mapFilaments(item.filaments)}
//         <Card.Text>{item.description}</Card.Text>
//       </Card.Body>
//       <Card.Footer> 
//         <div>USD ${item.price.toFixed(2)}</div>
//         <Button variant='success' onClick={() => props.onAdd(item)}>Buy Now!</Button>
//       </Card.Footer>
//     </Card>
//   </Col>

//   // return (
//   //   <div className="card col-xs-12 col-md-6 col-lg-4">
//   //     <img className="card-img-top" src={imgURL} alt="Card image cap"/>
//   //     <div className="card-body">
//   //       <h5 className="card-title">{item.productName}</h5>
//   //       <p className="card-text">{item.description}</p>
//   //       <a href="/" className="btn btn-success">USD ${item.price} Buy Now!</a>
//   //     </div>
//   //   </div>
//   // )
// }


function App() {
  return (
    <React.Fragment>
      <ProductProvider>
        <CartProvider>
          <BrowserRouter>
            <NavbarComponent/>
            <Routes>
              <Route path='/' element={<ProductList/>} />
              <Route path='/about' element={<OrderForm/>} />
            </Routes>
          </BrowserRouter>
        </CartProvider>
      </ProductProvider>
    </React.Fragment>
  );
}

export default App;
