export interface StockInfoInterface{
    id: string ,
    name:string ,
    symbol:string,
    variation_percentage: string ,
    variation_number: number ,
    latest_price: number ,
    related:string ,
    rank:number ,
    history: string ,
}

export interface StockPriceInterface{
    datetime:string,
    price:number
}

// export interface StockDailyInterface{
//     name: string,
//     symbol:string,
//     date:string,
//     prices:StockPriceInterface[]
// }

export class StockInfo implements  StockInfoInterface{

    id!: string;
    name!: string;
    symbol!:string;
    variation_percentage!: string;
    variation_number!: number;
    latest_price!: number;
    related!: string;
    rank!: number;
    history!: string;

    constructor(data: Partial<StockInfo>|undefined){
        if(data !== undefined){
            Object.assign(this, data);
        }
        

    }
    
}

export interface StockDailyPropsInterface {
    name:string,
    symbol: string,
    datetime:string
}