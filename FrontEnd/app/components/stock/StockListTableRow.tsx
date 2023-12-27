import { useEffect, useState } from "react";
import config from "../../config/config-stockTable.json";
import { TableRow } from "@mui/material";
import StockTableCol from "./StockTableCol";
import { STOCK_HOME_PAGE } from "@/app/const/TableConst";
import StockTableColGrid from "./StockTableColGrid";
import { StockInfoInterface } from "@/app/Interfaces/table/StockInfoInterface";
import { StockListTableRowProps } from "@/app/Interfaces/table/StockListTableRowProps";

const StockListTableRow: React.FC<StockListTableRowProps> = (props) => {
  const displayConfig = config["display"];
  const page = props.page;
  const displaySections = displayConfig.find(
    (d) => d.page === page
  )?.display_sections;
  const [stockInfo, setStockInfo] = useState<StockInfoInterface>({
    id: "",
    name: "",
    variation_percentage: "",
    variation_number: 0,
    latest_price: 0,
    related: "",
    rank: 0,
    history: "",
    symbol:""
  });

  useEffect(() => {
    setStockInfo(props.stock_info)
    // setStockInfo({ ...stockInfo, id: props.stock_info.id });
    // displaySections?.map(value =>
    //   setStockInfo({
    //     ...stockInfo,
    //     [value]: props.stock_info[value as keyof StockInfoInterface],
    //   })
    // );
  }, []);

  return (
    <TableRow>
      {displaySections?.map((value) => (
        <StockTableCol
          id={stockInfo[value as keyof StockInfoInterface] as string}
          content={stockInfo[value as keyof StockInfoInterface] as string}
          colName={value}
        ></StockTableCol>
      ))}
    </TableRow>
  );
};

export default StockListTableRow
