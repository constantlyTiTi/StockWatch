
module.exports = Object.freeze({
    ALL_STOCKS_DETAILS : "http://localhost:8080/api/stock/symbols?country=",
    STOCKS_WITH_PRICES: "http://localhost:8080/api/stock/quotes",
    GET_STOCK_HISTORY_PRICES_BY_SYMBOL : "http://localhost:8080/api/stock/history_prices?symbol=",
    GET_CURRENT_PRICE_BY_SYMBOL : "http://localhost:8080/api/stock/quote?symbol=",
    GET_COMPANY_NEWS_BY_SYMBOL: "http://localhost:8080/api/stock/company_news"
});