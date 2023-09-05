import './App.css';
import React, { useContext } from 'react';
import {ProductProvider } from './products/productContext';
import { CartProvider } from './cart';
import { NavbarComponent } from './base/NavComponent';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { OrderForm } from './order';
import { ProductList } from './products/components';
import { About } from './base/about';
import { ThankYou } from './base/goodCheckout';


function App() {
  return (
    <React.Fragment>
      <ProductProvider>
        <CartProvider>
          <BrowserRouter>
            <NavbarComponent/>
            <Routes>
              <Route path='/' element={<ProductList/>} />
              <Route path='/about' element={<About/>} />
              <Route path='/checkout-success' element={<ThankYou/>} />
              <Route path='/checkout-failure' element={<ProductList/>} />
            </Routes>
          </BrowserRouter>
        </CartProvider>
      </ProductProvider>
    </React.Fragment>
  );
}

export default App;
