import { TableColCustProps } from "@/app/Interfaces/table/TableColProps";
import TableCell from "@mui/material/TableCell";

const StockTableCol: React.FC<TableColCustProps> = (props) => {
    const content = props.content;
    const symbol = props.symbol;
  
    return (
      <TableCell id={`col-${symbol}`} >
        {content}
      </TableCell>
    );
};

export default StockTableCol;