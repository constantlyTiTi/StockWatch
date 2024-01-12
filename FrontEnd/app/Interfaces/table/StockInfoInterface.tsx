export interface StockInfoInterface{
    currency: string,
    symbol:string,
    display_symbol:String,
    description: String,
    figi: String,
    isin:String,
    mic:String,
    share_class_figi: string,
    type: String
    current_price?:number | undefined
    percent_change?:number | undefined
    history_prices?:StockDailyPropsInterface[]|undefined
}

export class StockInfo implements  StockInfoInterface{

    currency!: string;
    symbol!:string;
    display_symbol!:String
    description!: String;
    figi!: String;
    isin!:String
    mic!:String
    share_class_figi!: string;
    type!: String;
    current_price?:number | undefined
    percent_change?:number | undefined
    history_prices?: StockDailyPropsInterface[] | undefined;

    constructor(data: Partial<StockInfo>|undefined){
        if(data !== undefined){
            Object.assign(this, data);
        }
        

    }
    
}

export interface StockDailyPropsInterface {
    datetime:string,
    current_price:number,
    change:number,
    percent_change:number,
    highest_price:number,
    lowest_price:number,
    open_price:number,
    previous_closed_price:number,
}
