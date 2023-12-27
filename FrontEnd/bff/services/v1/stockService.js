const mockData = require('../../mock-data/stock-mock-data.json')
const axios = require('axios');
const stockAPI = require('../../const/v1/stockAPIs.js')

exports.getAllStockDetailsService = async () => {

    // const res = await axios
    //     .get(stockAPI.ALL_STOCKS_DETAILS)
    //     .then(response => {
    //         console.log("api",response)
    //         return mockData
    //     })
    //     .catch(error => {
    //         console.log("bff-error",error)
    //         error = mockData
    //         return error
    //     });

    const res = mockData

    return res;

}
