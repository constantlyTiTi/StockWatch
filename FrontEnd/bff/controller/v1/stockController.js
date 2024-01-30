
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
        res.send(await stockService.getHistoryPricesBySymbol(req.query.symbol))
        
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

exports.getCompanyNewsBySymbol = async (req, res) => {
    console.log("getCompanyController")
    console.log(req)
    try{
        res.send(await stockService.getCompanyNewsBySymbol(req.query.symbol, req.query.from, req.query.to))
        
    }
    catch(error){
        return "error"
    }
}
