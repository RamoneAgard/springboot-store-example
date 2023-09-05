import { createContext, useState } from "react";

export const CartContext = createContext({
    items: [],
    addToCart: () => {},
    removeFromCart: () => {},
    getTotalCost: () => {},
    getItemQuantity: () => {}
})

export function CartProvider(props){
    const [cartItems, setCartItems] = useState([]);

    function itemsAreEqual(item1, item2){
        console.log("item1")
        console.log(item1)
        console.log("item2")
        console.log(item2)
        if(!item1 || !item2){
            return false 
        }
        return (item1.product.id === item2.product.id) && (item1.color.id === item2.color.id)
    }

    function getItemQuantity(currItem){
        console.log("getQuantity")
        console.log(currItem)
        const item = cartItems.find( (item) => itemsAreEqual(currItem, item))
        if(item !== undefined){
            return item.quantity !== undefined? item.quantity : 0
        }
        return 0
    }

    function addToCart(product, colorPick){
        console.log(colorPick)
        var newItem = {
            product: product,
            color: colorPick
        }
        const itemQuantity = getItemQuantity(newItem)
        if(itemQuantity === 0){
            setCartItems([...cartItems, 
                {   product: product,
                    color: colorPick,
                    quantity: 1
                }
            ])
        } else {
            setCartItems(
                cartItems.map( (item) => 
                    itemsAreEqual(newItem, item) ? 
                        {...item, quantity: itemQuantity + 1} : item
                )
            )
        }
    }

    function removeFromCart(removeItem, entireQuantity){
        const itemQuantity = getItemQuantity(removeItem)
        if(itemQuantity === 1 || entireQuantity){
            setCartItems(
                cartItems.filter( (item) => {
                    return item.product.id !== removeItem.product.id && item.color.id !== removeItem.color.id
                })
            )
        } else {
            setCartItems(
                cartItems.map( (item) => 
                    itemsAreEqual(removeItem, item) ? 
                        {...item, quantity: itemQuantity - 1} : item
                )
            )
        }
    }

    function getTotalCost(){

        return cartItems.reduce(
            (total, item) => total += (item.product.price * item.quantity), 
            0
        )
    }

    const contextData = {
        items: cartItems,
        addToCart: addToCart,
        removeFromCart: removeFromCart,
        getTotalCost: getTotalCost,
        getItemQuantity: getItemQuantity
    }

    return <CartContext.Provider value={contextData}>
        {props.children}
    </CartContext.Provider>

    
}