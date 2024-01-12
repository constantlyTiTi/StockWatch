
import { ThemeContext } from '@emotion/react'
import Link from 'next/link'
import { Typography } from '@mui/material'
export default function Home() {


  return (
    <>
    <Typography variant="h5" component="div">
      This app is created to monitor the stock price in real market; 
      Currently, it only supports 8 stocks due to the limitation of third party api which is used to retrieve the real time data.
    </Typography>
    <Typography sx={{ mb: 1.5 }} color="text.secondary">
      FrontEnd is built with Next.js and Node.js, it listens to the websocket channel to get the latest changes.
    </Typography>
    <Typography sx={{ mb: 1.5 }} color="text.secondary">
      BackEnd is built with spring boot and JPA, integrated scheduler to retrieve data from third party API, and distribute data via Apache Kafka. 
      Data will be saved into database in kafka consumer for better performance, as well as sending data to websocket channel.
    </Typography>

    <Link href="/stock">
    <Typography variant="h5" component="div">
        Now click here and enjoy
      </Typography>
    </Link>
    
    </>

    
  )
}
