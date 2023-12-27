import { Box, Grid } from "@mui/material";
import { memo, useCallback, useEffect, useState } from "react";
import config from "../../config/config-stockTable.json";
import { STOCK_HOME_PAGE } from "@/app/const/TableConst";
import StockListTableRow from "./StockListTableRow";
import { StockListTableRowGridProps } from "@/app/Interfaces/table/StockListTableRowProps";
import { StockInfo } from "@/app/Interfaces/table/StockInfoInterface";
import { GET_ALL_STOCKS_INFO } from "@/app/actions/stockActionsTypes";
import { useReducer } from "react";
import { stockReducer } from "@/app/reducers/stockReducer";
import { getAllStockInfo } from "@/app/api/stockAPIs";

const StockListTableGrid = () => {
  const [tableRows, setTableRows] = useState<StockListTableRowGridProps[]>([]);
  const [colGroupNum, setColGroupNum] = useState<number>(0);
  const stockTableRowsNumber = config.home_stock_table_rows;
  const [_, dispatch] = useReducer(stockReducer, { all_stock_details: [] });

  const fetchData = useCallback(() => {
      console.log(111111)
      getAllStockInfo()
        .then((res) => {
          const stockRows = res.map((s: StockInfo) => ({
            stock_info: s,
            page: "home",
            spacing: 2,
          }));
          dispatch({
            type: GET_ALL_STOCKS_INFO,
            payload: stockRows,
          });
          setTableRows(stockRows);
          console.log(stockRows)
        })
        .catch((error) => console.log(error));
  }, []);

  useEffect(() => {
    
    let colNum = config.display.find((p) => p.page === STOCK_HOME_PAGE)
      ?.display_sections.length;
    let groupNum = !!colNum ? Math.floor(6 / colNum) : 0;
    setColGroupNum(groupNum);
    fetchData();
    // const intervalId = setInterval(() => {
    //   fetchData();
    // }, 100000 * 1);

    // return () => clearInterval(intervalId);
  }, [fetchData,STOCK_HOME_PAGE]);

  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={2}>
        {Array(colGroupNum)
          .fill(0)
          .map((_, index) => (
            <Grid item xs={12 / colGroupNum} spacing={2}>
              {tableRows
                .slice(
                  index * stockTableRowsNumber,
                  (index + 1) * stockTableRowsNumber
                )
                .map((value) => (
                  <StockListTableRow
                    // page={value.page}
                    page="home"
                    stock_info={value.stock_info}
                  />
                ))}
            </Grid>
          ))}
      </Grid>
    </Box>
  );
};

export default StockListTableGrid;
