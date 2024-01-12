import { useEffect, useState } from "react";
import config from "../../config/config-stockTable.json";
import { TableRow, styled } from "@mui/material";
import StockTableCol from "./StockTableCol";
import { StockInfoInterface } from "@/app/Interfaces/table/StockInfoInterface";
import { StockListTableRowProps } from "@/app/Interfaces/table/StockListTableRowProps";

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

const StockListTableRow: React.FC<StockListTableRowProps> = (props) => {
  const displayConfig = config["display"];
  const page = props.page;
  const displaySections = displayConfig.find(
    (d) => d.page === page
  )?.display_sections;
  const stockInfo = props.stock_info

  return (
    <StyledTableRow>
      {displaySections?.map((value) => (
        <StockTableCol
          key = {`${stockInfo.symbol}-${value}`}
          symbol={stockInfo[value as keyof StockInfoInterface] as string}
          content={stockInfo[value as keyof StockInfoInterface] as string}
          colName={value}
        />
      ))}
    </StyledTableRow>
  );
};

export default StockListTableRow
