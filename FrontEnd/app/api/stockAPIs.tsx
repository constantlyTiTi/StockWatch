import { StockInfo } from "@/app/Interfaces/table/StockInfoInterface";
import axios from "axios";
import {GET_ALL_STOCK,GET_STOCK_BY_SYMBOL, GET_STOCKS_CURRENT_PRICE} from './stockURLs'
import httpConfig from '../config/config-http.json'

export const getAllStockInfo = () =>{
  
  const response = 
    axios.get(GET_ALL_STOCK,{
      headers:httpConfig.headers
    })

    .then((res) => {
      const stocks = res.data.all_stock_details.map((d: StockInfo) => new StockInfo(d))
      return stocks

    })
    .catch((error) => {
      return [new StockInfo(undefined)]
    })

    return response

}

export const getStocksWithPrices = () =>{
  
  const response = 
    axios.get(GET_STOCKS_CURRENT_PRICE,{
      headers:httpConfig.headers
    })

    .then((res) => {
      const stocks = res.data.stocks_with_prices.map((d: {StockDto:any,StockConcurrentPriceDtos:StockInfo[]}) => (new StockInfo({
        ...d.StockDto,
        ...d.StockConcurrentPriceDtos[0]
      })))
      return stocks

    })
    .catch((error) => {
      console.log("fe-error",error)
      return [new StockInfo(undefined)]
    })

    return response

}

export const getStockBySymbol = async (symbol:string) =>{
  
  const response = 
    await axios.get(GET_STOCK_BY_SYMBOL+symbol,{
      headers:httpConfig.headers
    })

    .then((res) => {
      const stock = res.data as StockInfo
      return stock

    })
    .catch((error) => {
      return error
    })

    return response

}

// export const getStockPricesBySymbolAndDate = async (symbol:string, datetime:string) =>{
  
//   const response = 
//     await axios.get(GET_STOCK_PRICES_BY_SYMBOL_AND_DATE,{
//       headers:httpConfig.headers
//     })

//     .then((res) => {
//       const stock = res.data as StockPriceInterface
//       console.log("fe stock",symbol)
//       return stock

//     })
//     .catch((error) => {
//       console.log("fe-error",error)
//       return error
//     })

//     return response

// }


