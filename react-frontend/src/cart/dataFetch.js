import { appDataFetch } from "../appFetch";

export function apiOrderSubmit(data, callback){
     appDataFetch(
          "POST", 
          "/order", 
          callback, 
          data
     )
}