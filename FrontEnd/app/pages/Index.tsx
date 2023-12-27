import { useState } from "react";
import config from "../config/config-stockTable.json";
import Banner from "../components/banner/Banner";
import {StockListTable, StockListTableGrid} from "../components/stock/index";
import {PostListTable} from "../components/post/index";
import { HeaderSearchBar } from "../components/searchBar";
import { Box } from "@mui/material";
const Index = () => {

    return(
        <Box sx={{ flexGrow: 1 , p:2, maxWidth:1500, marginLeft:"auto", marginRight:"auto", height: "100vh"}}>
        <Banner/>
        <HeaderSearchBar/>
        <StockListTableGrid></StockListTableGrid>
        {/* <StockListTable></StockListTable> */}
        {/* <PostListTable></PostListTable> */}
        </Box>

    )
}

export default Index