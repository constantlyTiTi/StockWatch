import { TableRowProps } from "@mui/material/TableRow/TableRow";

export interface TableProps {
    id: number | string,
    tableRows: TableRowProps[]
}