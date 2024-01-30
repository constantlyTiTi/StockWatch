'use server'
import { StockCompanyNewsInterface } from "@/app/Interfaces/table/StockCompanyNewsInterface";
import { getCompanyNewsBySymbol } from "@/app/api/stockAPIs";
import {
  Card,
  CardContent,
  CardHeader,
  CardMedia,
  List,
  ListItem,
  Typography,
} from "@mui/material";
import { Suspense } from "react";
import Loading from "../../loading";

async function CompanyNewsList({symbol}: { symbol: string }) {
  const currentDate = new Date();
  const pastDate = new Date(
    new Date().setMonth(new Date().getMonth() - 5)
  );

  const result = await getCompanyNewsBySymbol(
    symbol,
    pastDate,
    currentDate
  )

  return (
    <List       sx={{
        width: '100%',
        bgcolor: 'background.paper',
        position: 'relative',
        overflow: 'auto',
        '& ul': { padding: 2 },
      }}
      subheader={<li />}>
      {!!result && result.length > 0 ? result.map((news: StockCompanyNewsInterface) => (
        <li key={`${news.origin_id}_listItem`}>
          <Card>
            <CardHeader
              title={news.headline}
              subheader={news.datetime.toLocaleDateString()}
            />
            {/* <CardMedia
              component="img"
              height="194"
              image={news.image}
              alt="news image"
            /> */}
            <CardContent>
              <Typography variant="body2" color="text.secondary">
                {news.summary}
              </Typography>
            </CardContent>
          </Card>
        </li>
      )) : null}
    </List>
  );
}

export default async function Page({ params:{symbol} }: { params: { symbol: string } }) {

  return (
 
      <CompanyNewsList symbol={symbol} />

  );
}
