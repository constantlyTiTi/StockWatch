'use client'
import { useRouter } from "next/navigation";
import { Typography, Grid, Box, Button } from "@mui/material";

export default function Home() {
  const router = useRouter()

  const navigateToNextPage = () =>{
    router.push(`/stock`)
  }
  return (
    <Box m={2}>
      <Grid container spacing={3} mb={5} >
        <Grid item xs={12}sx={{ flexWrap: "wrap" }}>
          <Typography variant="h5" component="div">
            This app is created to monitor the stock price in real market;
            Currently, it only supports 8 stocks due to the limitation of third
            party api which is used to retrieve the real time data.
          </Typography>
        </Grid>
        <Grid item xs={12} sx={{ flexWrap: "wrap" }}>
          <Typography variant="h5" component="div">
            FrontEnd is built with Next.js and Node.js, it listens to the
            websocket channel to get the latest changes.
          </Typography>
        </Grid>
        <Grid item xs={12} sx={{ flexWrap: "wrap" }}>
          <Typography variant="h5" component="div">
            BackEnd is built with spring boot and JPA, integrated scheduler to
            retrieve data from third party API, and distribute data via Apache
            Kafka. Data will be saved into database in kafka consumer for better
            performance, as well as sending data to websocket channel.
          </Typography>
        </Grid>
      </Grid>
      <Button onClick={navigateToNextPage} variant="outlined">
        <Typography variant="h5" component="div">
          Now click here and enjoy
        </Typography>
      </Button>
    </Box>
  );
}
