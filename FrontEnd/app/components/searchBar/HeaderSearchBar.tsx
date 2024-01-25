import {
  AppBar,
  Box,
  Toolbar,
  Typography,
  alpha,
  ListItem,
  List,
  styled,
  InputBase,
  ListItemText,
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { getAllStockInfo } from "@/app/api/stockAPIs";
import { StockInfo } from "@/app/Interfaces/table/StockInfoInterface";

const Search = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.success.light, 0.15),
  "&:hover": {
    backgroundColor: alpha(theme.palette.success.light, 0.25),
  },
  marginLeft: 0,
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(1),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  width: "100%",
  "& .MuiInputBase-input": {
    padding: theme.spacing(1, 1, 1, 0),
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    [theme.breakpoints.up("sm")]: {
      width: "12ch",
      "&:focus": {
        width: "20ch",
      },
    },
  },
}));

const StyledList = styled(List)(({ theme }) => ({
  overflow: "auto",
  zIndex: 1,
  position: "absolute",
  border:"2px solid #ddd",
  "& .MuiListItemText-root":{
    color: 'black',
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
  },
  '& li.Mui-focused': {
    backgroundColor: '#4a8df6',
    color: 'white',
    cursor: 'pointer',
  },
  '& li:active': {
    backgroundColor: '#2977f5',
    color: 'white',
  },
}));

const StyledListItem = styled(ListItem)(({ theme }) => ({
  '& li.Mui-focused': {
    backgroundColor: '#4a8df6',
    color: 'white',
    cursor: 'pointer',
  },
  '& .MuiListItem-root:hover': {
    backgroundColor: '#2977f5',
    color: 'white',
  },
}));

const HeaderSearchBar = () => {
  const [symbol, setSymbol] = useState<string>("");
  const [allSymbols, setAllSymbols] = useState<string[]>([]);
  const [filteredSymbols, setFilteredSymbols] = useState<string[]>([]);
  const router = useRouter();

  useEffect(() => {
    getAllStockInfo("US").then((res) => {
      const symbols = res.map((r: StockInfo) => r.symbol);
      setAllSymbols(symbols);
    });
  }, []);

  const onChange = (e: any) => {
    const entryString = e.target.value;
    setSymbol(entryString.toUpperCase());
    console.log(allSymbols);
    if (!!allSymbols && allSymbols.length > 0) {
      const filted = allSymbols.filter((i) =>
        i.toUpperCase().startsWith(entryString.toUpperCase())
      );
      const filtedCount =
        filted.length > 5 ? filted.slice(0, 5).concat(["..."]) : filted

      setFilteredSymbols(filtedCount);
    }
  };

  const onKeyDown = (e: React.KeyboardEvent<HTMLInputElement> ) => {
    console.log(e.key)
    if(e.key === 'Enter' && !!symbol && allSymbols.includes(symbol)){
      router.push(`/stock/${symbol}`);
      setSymbol("")
      setFilteredSymbols([]);
    }
  };

  const listItemOnClick = (symbol:string) => {
    if(symbol !== '...')
    {
      router.push(`/stock/${symbol}`);
      setSymbol("")
      setFilteredSymbols([]);
    }
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar sx={{ bgcolor: "green" }}>
        <Toolbar>
          {/* <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="open drawer"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton> */}
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: "none", sm: "block" } }}
          >
            Stock Watch
          </Typography>
          <Search>
            <SearchIconWrapper>
              <SearchIcon />
            </SearchIconWrapper>
            <StyledInputBase
              placeholder="Search…"
              inputProps={{ "aria-label": "search" }}
              onChange={onChange}
              onKeyDown={onKeyDown}
              value={symbol}
            />

            {!!symbol?.trim() && filteredSymbols.length > 0 ? (
              <StyledList
                sx={{
                  width: "100%",
                  bgcolor: "background.paper",
                }}
              >
                {filteredSymbols.map((f) => (
                  <StyledListItem key={f} disableGutters onClick={(_:any) => listItemOnClick(f)}>
                    <ListItemText primary={f} />
                  </StyledListItem>
                ))}
              </StyledList>
            ) : null}
          </Search>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default HeaderSearchBar;
