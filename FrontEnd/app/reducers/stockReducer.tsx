import {
  GET_ALL_STOCKS_INFO,
  GET_STOCK_BY_SYMBOL,
  GET_STOCK_BY_NAME,
  GET_STOCK_BY_SYMBOL_AND_DATETIME,
  CONCAT_NEW_STOCK_PRICE,
} from "../actions/stockActionsTypes";

export const stockReducer = (
  state: any,
  action: { type: string; payload: any }
) => {
  switch (action.type) {
    case GET_ALL_STOCKS_INFO: {
      // const stocks = getAllStockInfo()

      return {
        ...state,
        all_stock_details: action.payload,
      };
    }

    case GET_STOCK_BY_SYMBOL:
      return {
        ...state,
        stock: action.payload,
      };

    case GET_STOCK_BY_SYMBOL_AND_DATETIME:
      return {
        ...state,
        stock_daily_prices: action.payload,
      };

    case CONCAT_NEW_STOCK_PRICE:
      const newPrices = state.stock_daily_prices.concat(action.payload);
      return {
        ...state,
        stock_daily_prices: newPrices,
      };

    case GET_STOCK_BY_NAME:
      return {
        ...state,
        stock: action.payload,
      };
      
    default:
      return state;
  }
};
