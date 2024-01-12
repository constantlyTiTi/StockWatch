const mockData = require('../../mock-data/stock-mock-data.json')
const axios = require('axios');
const stockAPI = require('../../const/v1/stockAPIs.js')

exports.getAllStockDetailsService = async () => {

    const res = await axios
        .get(stockAPI.ALL_STOCKS_DETAILS)
        .then(response => {
            console.log("api",response)
            return {all_stock_details:response.data}
        })
        .catch(error => {
            return error
        });

    return res;
}

exports.getStocksWithCurrentPriceService = async () => {

    const res = await axios
        .get(stockAPI.STOCKS_WITH_PRICES)
        .then(response => {
            console.log("api",response)
            return {stocks_with_prices:response.data}
        })
        .catch(error => {
            return error
        });

    return res;
}

exports.getHistoryPricesBySymbol = async (symbol) => {

    const res = await axios
        .get(stockAPI.GET_STOCK_HISTORY_PRICES_BY_SYMBOL)
        .then(response => {
            console.log("api",response)
            return response.data
        })
        .catch(error => {
            return error
        });

    return res;
}
