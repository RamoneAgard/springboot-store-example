import React, { useContext } from 'react';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import { ProductContext } from './productContext';
import { CartContext } from '../cart/cartContext';
import { ProductItem } from './detail';


export function ProductList(props) {
  
    const productInfo = useContext(ProductContext);
    const cartInfo = useContext(CartContext);
  
    function handleCartAdd(item, color){
      cartInfo.addToCart(item, color)
    }
  
    return (<Container className='p-3'>
      <Row>
        {productInfo.productList.map((curItem, index) => {
          return <ProductItem key={index} item={curItem} dex={index} onAdd={handleCartAdd}/>
        })}
      </Row>
    </Container>);
    // return (
    // <div className='container justify-content-center'>
    //   {products.map((curItem, index) => {
    //       return <ProductItem item={curItem} dex={index}/>
    //   })}
    //   {/* <ProductItem item={products[0]}/> */}
    // </div>
    // );
  
  }