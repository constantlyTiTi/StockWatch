// import { StockDailyPropsInterface, StockInfo, StockInfoInterface } from "@/app/Interfaces/table/StockInfoInterface";
// import { CONCAT_NEW_STOCK_PRICE, GET_STOCK_BY_SYMBOL, GET_STOCK_BY_SYMBOL_AND_DATETIME } from "@/app/actions/stockActionsTypes";
// import { getStockBySymbol } from "@/app/api/stockAPIs";
// import { STOCK_SYMBOL_CHANNEL } from "@/app/api/stockURLs";
// import { stockReducer } from "@/app/reducers/stockReducer";
// import { useCallback, useEffect, useReducer, useState } from "react";
// import useWebSocket from "react-use-websocket";
// import { Line } from "react-chartjs-2";

// const StockDailyPricesCanvas: React.FC<StockDailyPropsInterface> = (props) => {

//   const { lastMessage, getWebSocket } = useWebSocket(
//     STOCK_SYMBOL_CHANNEL + props.symbol
//   );
//   const [state, dispatch] = useReducer(stockReducer, 
//     { stock: {}, 
//     stock_daily_prices:[] as unknown as StockPriceInterface});

      
//   const [chartData, setChartData] = useState({
//     labels: state.stock_daily_prices.map((data: StockPriceInterface) => data.datetime), 
//     datasets: [
//       {
//         label: "prices",
//         data: state.stock_daily_prices.map((data:StockPriceInterface) => data.price),
//         backgroundColor: [
//           "rgba(75,192,192,1)",
//           "&quot;#ecf0f1",
//           "#50AF95",
//           "#f3ba2f",
//           "#2a71d0"
//         ],
//         borderColor: "black",
//         borderWidth: 2
//       }
//     ]
//   });
  
//   const fetchStockDescriptionData = useCallback(async (symbol: string) => {
//     getStockBySymbol(symbol).then((res) => {
//       dispatch({
//         type: GET_STOCK_BY_SYMBOL,
//         payload: res.data as StockInfo,
//       });
//     })
//     .catch((error)=>console.log(error));
//   }, []);

//   const fetchStockDailyPriceData = useCallback(async (symbol:string, datetime:string) => {
//     getStockPricesBySymbolAndDate(props.symbol, props.datetime).then((res) => {
//       dispatch({
//         type: GET_STOCK_BY_SYMBOL_AND_DATETIME,
//         payload: res.data as StockPriceInterface,
//       });
//     })
//     .catch((error)=>console.log(error));
//   }, []);

//   useEffect(()=>{
//     fetchStockDescriptionData(props.symbol)
//     fetchStockDailyPriceData(props.symbol,props.datetime)
//   },[fetchStockDescriptionData, fetchStockDailyPriceData])

//   useEffect(() => {
//     if(lastMessage !== null ){
//       const currentWebsocketUrl = getWebSocket()!.url;
//       console.log('received a message from ', currentWebsocketUrl);

//       dispatch({
//         type: CONCAT_NEW_STOCK_PRICE,
//         payload:lastMessage.data
//       })
      
//     }
//   }, [lastMessage]);

//   return (
//     <div className="chart-container">
//       <h2 style={{ textAlign: "center" }}>Line Chart</h2>
//       <Line
//         data={chartData}
//         options={{
//           plugins: {
//             title: {
//               display: false,
//             },
//             legend: {
//               display: false
//             }
//           }
//         }}
//       />
//     </div>
//   );
// };

// export default StockDailyPricesCanvas
