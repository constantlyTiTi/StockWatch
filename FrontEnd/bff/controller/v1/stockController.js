
const stockService = require('../../services/v1/stockService.js')

exports.getAllStockDetails = async (req,res)=> {
    console.log("stockController")
    try{
        res.send(await stockService.getAllStockDetailsService())
        
    }
    catch(error){
        return "error"
    }

    
}
