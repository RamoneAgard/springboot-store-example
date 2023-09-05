
export function appDataFetch(method, endpoint, callback, data) {
    const xhttp = new XMLHttpRequest();
    const url = `http://localhost:8080/api${endpoint}`; 
    xhttp.open(method, url)
    const responseType = "json";
    xhttp.responseType = responseType;
    xhttp.setRequestHeader("Content-Type", "application/json")
    let jsonData;
    if(data){
        jsonData = JSON.stringify(data);
    }

   
    xhttp.onload = function () {
        if(xhttp.status >= 200 && xhttp.status < 300){
            console.log(xhttp.status)
            callback(xhttp.response, xhttp.status);
        }
        else {
            callback({'message': "An error occured with this request"}, 400);
        }
    }
    xhttp.onerror = function (e) {
        console.log(e)
        callback({'message': "An error occured with this request"}, 400);
    }

    xhttp.send(jsonData);
}