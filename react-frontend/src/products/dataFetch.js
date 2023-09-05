import { appDataFetch } from "../appFetch";

export function apiProductsList (callback, pageNumber, pageSize){
     appDataFetch(
          "GET", 
          `/product?pageNumber=${pageNumber}&pagesize=${pageSize}`, 
          callback
     )
}