const stockController = require('../controller/v1/stockController.js')
const express = require("express");
const router = express.Router();

router.get("/all_stocks", stockController.getAllStockDetails)
router.get("/all_stocks_price", stockController.getStocksWithCurrentPrice)
router.get("/history_prices?symbol=:symbol", stockController.getHistoryPricesBySymbol)
router.get("/symbols?country=:country", stockController.getHistoryPricesBySymbol)

module.exports = router;