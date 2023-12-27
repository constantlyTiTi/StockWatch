import { TableColCustProps } from "@/app/Interfaces/table/TableColProps";
import TableCell from "@mui/material/TableCell";

const StockTableCol: React.FC<TableColCustProps> = (props) => {
    const content = props.content;
    const id = props.id;
  
    return (
      <TableCell id={`col-${id}`} >
        {content}
      </TableCell>
    );
};

export default StockTableCol;