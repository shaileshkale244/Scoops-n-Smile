
import axios from 'axios';
var baseUrl = "http://localhost:3002/api/products"
class ProductService {
    constructor() {
        this.prodarr = []
    }
    getAllProducts() {
        return axios.get(baseUrl);
    }
    getById(pid) {
        return axios.get(baseUrl + "/" + pid);
    }

    addProduct(product) {
        //add product at the end of the array
        //var headers={"content-type":"application/json",Atherization:"barer+<token>"}

        var myheader = { "content-type": "application/json" }
        console.log(baseUrl + "/" + product.pid);
        return axios.post(baseUrl + "/" + product.pid, product, { headers: myheader })
        // return axios.post(baseUrl)

    }
    updateproduct(product) {
        var myheader = { "content-type": "application/json" }

        return axios.put(baseUrl + "/" + product.pid, product, { headers: myheader })

    }
    deleteProduct(pid) {
        return axios.delete(baseUrl + "/" + pid)
    }
}

export default new ProductService();