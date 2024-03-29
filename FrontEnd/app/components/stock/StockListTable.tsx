import {
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  styled,
  tableCellClasses,
} from "@mui/material";
import { memo, useEffect, useState, useCallback } from "react";
import config from "../../config/config-stockTable.json";
import { STOCK_HOME_PAGE } from "@/app/const/TableConst";
import StockListTableRow from "./StockListTableRow";
import { StockInfo } from "@/app/Interfaces/table/StockInfoInterface";
import { StockListTableRowProps } from "@/app/Interfaces/table/StockListTableRowProps";
import { getStocksWithPrices } from "@/app/api/stockAPIs";
import * as StompJs from "@stomp/stompjs";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.success.main,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StockListTable = () => {
  const [tableRows, setTableRows] = useState<StockListTableRowProps[]>([]);
  // const [_, dispatch] = useReducer(stockReducer, { stocks_with_prices: [] });
  const headers = config.display.find(
    (p) => p.page === STOCK_HOME_PAGE
  )?.display_sections;

  const fetchData = useCallback(() => {
    getStocksWithPrices()
      .then((res) => {
        const stockRows = res.map((s: StockInfo) => ({
          stock_info: s,
          page: "home",
          spacing: 2,
        }));
        setTableRows(stockRows);
      })
      .catch((error) => console.log(error));
  }, []);

  useEffect(() => {
    if (!tableRows || tableRows.length == 0) {
      fetchData();

      const stompClient = new StompJs.Client({
        brokerURL: "ws://localhost:8080/ws"
      });

      stompClient.onConnect = (frame) => {
        console.log("Connected: " + frame);
        stompClient.subscribe("/bff/prices", (data) => {
          const dataConvert = JSON.parse(data.body);
          const stockRows = dataConvert.map((d: {StockDto:any,StockConcurrentPriceDtos:StockInfo[]}) => (new StockInfo({
            ...d.StockDto,
            ...d.StockConcurrentPriceDtos[0]
          }))).map((s: StockInfo) => ({
            stock_info: s,
            page: "home",
            spacing: 2,
          }));  

          setTableRows(stockRows);
        });
      };

      stompClient.activate()
    }
  }, [fetchData, STOCK_HOME_PAGE]);

  return (
    <TableContainer component={Paper}>
      <Table sx={{ width: "100%" }}>
        <TableHead>
          <TableRow>
            {headers!! && headers.length > 0
              ? headers.map((header) => (
                  <StyledTableCell>{header}</StyledTableCell>
                ))
              : null}
          </TableRow>
        </TableHead>
        <TableBody>
          {tableRows!! && tableRows.length > 0
            ? tableRows.map((value) => (
                <StockListTableRow
                  key={`${value.stock_info.symbol}`}
                  page={value.page}
                  stock_info={value.stock_info}
                ></StockListTableRow>
              ))
            : null}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default memo(StockListTable);
