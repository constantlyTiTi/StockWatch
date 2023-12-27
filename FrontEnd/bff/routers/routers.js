const stockController = require('../controller/v1/stockController.js')
const express = require("express");
const router = express.Router();

router.get("/all_stocks", stockController.getAllStockDetails)

module.exports = router;