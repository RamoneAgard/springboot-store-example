import { createContext, useEffect, useState} from "react";
import { apiProductsList } from "./dataFetch";

export const ProductContext = createContext({
    productList: [],
    getProductById: () => {},
    getNextPage: () => {}
})

export function ProductProvider(props){
    const [products, setProducts] = useState([])
    const [productsDidLoad, setProductsDidLoad] = useState(false)
    const [pageNumber, setPageNumber] = useState(1)
    const [totalPages, setTotalPages] = useState(1)
    const [isLastPage, setIsLastPage] = useState(false)
    const pageSize = 20;

    useEffect(() => {
        if(!productsDidLoad){
            getProductsPage()
            console.log("in fetch")
        }
    })

    function getProductsPage(){
        apiProductsList(handleProductDataFetch, pageNumber, pageSize)
    }

    function getNextPage(){
        if(!isLastPage){
            apiProductsList(handleProductDataFetch, pageNumber, pageSize)
        }
    }

    function handleProductDataFetch(response, status) {
        if(status === 200){ 
            setProducts(response.content)
            setPageNumber(response.number + 1)
            setTotalPages(response.totalPages)
            setIsLastPage(response.last)
            setProductsDidLoad(true)
          }
          else{
            alert("There was an error getting this data")
          }
    }
    
    function getProductById(id){
        return products.find( (p) => p.id === id)
    }

    const contextData = {
        productList: products,
        getProductById: getProductById,
        getNextPage: getNextPage
    };

    return <ProductContext.Provider value={contextData}>
        {props.children}
    </ProductContext.Provider>
    
}