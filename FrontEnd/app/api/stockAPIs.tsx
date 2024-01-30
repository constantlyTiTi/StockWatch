import { StockInfo } from "@/app/Interfaces/table/StockInfoInterface";
import axios from "axios";
import {GET_ALL_STOCK,GET_COMPANY_NEWS_BY_SYMBOL,GET_STOCK_BY_SYMBOL, GET_STOCKS_CURRENT_PRICE} from './stockURLs'
import httpConfig from '../config/config-http.json'
import { StockCompanyNews, StockCompanyNewsInterface } from "../Interfaces/table/StockCompanyNewsInterface";

export const getAllStockInfo = (country:string) =>{
  
  const response = 
    axios.get(GET_ALL_STOCK + country,{
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

export const getStockBySymbol = (symbol:string) =>{
  
  const response = 
     axios.get(GET_STOCK_BY_SYMBOL+symbol,{
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

export const getCompanyNewsBySymbol = (symbol: string, from:Date, to:Date) =>{
  console.log(`${GET_COMPANY_NEWS_BY_SYMBOL}?symbol=${symbol}&from=${from.toLocaleDateString()}&to=${to.toLocaleDateString()}`)
  const response = 
     axios.get(`${GET_COMPANY_NEWS_BY_SYMBOL}?symbol=${symbol}&from=${from.toLocaleDateString()}&to=${to.toLocaleDateString()}`,{
      headers:httpConfig.headers
    })
    .then((res) => {
      const news = res.data.map((r:StockCompanyNews) => new StockCompanyNews(r))
      return news

    })
    .catch((error) => {
      console.log(error)
      return error
    })

    return response
}


