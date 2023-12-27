import styled from "@emotion/styled";
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import { TableColGridProps } from "@/app/Interfaces/table/TableColProps";


const StockTableColGrid: React.FC<TableColGridProps> = (props) => {
    const content = props.content;
    const id = props.id;

    const Item = styled(Paper)(({ theme }) => ({
        // backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
        // ...theme.typography.body2,
        // padding: theme.spacing(1),
        // textAlign: 'center',
        // color: theme.palette.text.secondary,
      }));
  
    return (
      <Grid id={`col-${id}`} item  xs={props.xs}>
        <Item>
        {content}
        </Item>
      </Grid>
    );
};

export default StockTableColGrid;