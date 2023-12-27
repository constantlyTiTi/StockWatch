import { StockInfoInterface } from "./StockInfoInterface"

export interface StockListTableRowProps {
    stock_info:StockInfoInterface
    page:string
}

export interface StockListTableRowGridProps {
    stock_info:StockInfoInterface
    page:string
    spacing:number|undefined
}
