import {
  Paper,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { memo, useEffect, useState, useCallback } from "react";
import config from "../../config/config-stockTable.json";
import { STOCK_ALL_PAGE, STOCK_HOME_PAGE } from "@/app/const/TableConst";
import StockListTableRow from "./StockListTableRow";
import { StockInfo } from "@/app/Interfaces/table/StockInfoInterface";
import { StockListTableRowProps } from "@/app/Interfaces/table/StockListTableRowProps";
import { useReducer } from 'react';
import { stockReducer } from "@/app/reducers/stockReducer";
import { GET_ALL_STOCKS_INFO } from "@/app/actions/stockActionsTypes";
import { getAllStockInfo } from "@/app/api/stockAPIs";

const StockListTable = () => {
  const [tableRows, setTableRows] = useState<StockListTableRowProps[]>([]);
  const [state, dispatch] = useReducer(stockReducer, {all_stock_details:[]});
  const headers = config.display.find(
    (p) => p.page === STOCK_ALL_PAGE
  )?.display_sections;

  
  const fetchData = useCallback(() => {
    async () => {
      getAllStockInfo()
        .then((res) => {
          const stockRows = res.map((s: StockInfo) => ({
            stock_info: s,
            page: "all_stock"
          }));
          dispatch({
            type: GET_ALL_STOCKS_INFO,
            payload: stockRows,
          });
          setTableRows(state.all_stock_details);
        })
        .catch((error) => console.log(error));
    };
  }, []);

  useEffect(() => {

    const intervalId = setInterval(() => {
      fetchData();
    }, 1000 * 1);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <TableContainer component={Paper}>
      <TableHead>
        <TableRow>
          {headers!! && headers.length > 0
            ? headers.map((header) => <TableCell>{header}</TableCell>)
            : null}
        </TableRow>
      </TableHead>
      <TableBody>
        {tableRows!! && tableRows.length > 0
          ? tableRows.map((value) => (
              <StockListTableRow
                page={value.page}
                stock_info={value.stock_info}
              ></StockListTableRow>
            ))
          : null}
      </TableBody>
    </TableContainer>
  );
};

export default memo(StockListTable);
