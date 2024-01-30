export interface StockCompanyNewsInterface{
    category: string,
    datetime:Date,
    headline:string,
    image: string,
    related: string,
    source:string,
    summary:string,
    url: string,
    origin_id: string
}

export class StockCompanyNews implements StockCompanyNewsInterface{
    category!: string;
    datetime!:Date;
    headline!:string;
    image!: string;
    related!: string;
    source!:string;
    summary!:string;
    url!: string;
    origin_id!: string;

    constructor(data: Partial<StockCompanyNews>|undefined){
        if(data !== undefined){
            Object.assign(this, data);
            this.datetime = new Date(data.datetime!!)
        }
        

    }
}