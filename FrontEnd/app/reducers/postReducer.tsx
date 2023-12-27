import { GET_ALL_STOCKS_INFO } from "../actions/stockActionsTypes";

export const postReducer = (state: any, action: { type: string; payload: any; }) => {
    switch(action.type){

        case GET_ALL_STOCKS_INFO:{
            // const stocks = getAllStockInfo()

            return {
                ...state,
                all_post_comments: action.payload
            }
        }
        default:
            return state

    }
}