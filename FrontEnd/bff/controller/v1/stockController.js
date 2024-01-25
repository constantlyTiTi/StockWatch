
const stockService = require('../../services/v1/stockService.js')

exports.getAllStockDetails = async (req,res)=> {
    try{
        res.send(await stockService.getAllStockDetailsService(req.query.country))
        
    }
    catch(error){
        return "error"
    }
}

exports.getHistoryPricesBySymbol = async (req,res)=> {
    try{
        res.send(await stockService.getHistoryPricesBySymbol(req.symbol))
        
    }
    catch(error){
        return "error"
    }
}

exports.getStocksWithCurrentPrice = async (req,res)=> {
    try{
        res.send(await stockService.getStocksWithCurrentPriceService())
        
    }
    catch(error){
        return "error"
    }
}
