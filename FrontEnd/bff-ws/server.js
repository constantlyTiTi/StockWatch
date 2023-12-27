const express = require('express')
const cors = require('cors');
// const hbs = require('hbs')
// const routers = require('./routers/routers')
// const httpConfig = require('./config/http-config.json')
const http = require('http')
const WebSocket = require('ws')
const app = express()
const WS_PORT = 3002

app.use(cors({
    origin:['http://localhost:3000'],
    credentials:true, 
    methods:['GET','POST','OPTION','DELETE','PUT'],
    optionSuccessStatus:200,
    allowedHeaders: ['Content-Type', 'Authorization']
}));

const server = http.createServer(app)

// app.use('/', routers)

server.listen(WS_PORT, () => {
    console.log(`app is running on PORT ${WS_PORT}`)
  })
  
  const wss = new WebSocket.Server({server});
  
  wss.on('connection', function (ws) {
      console.log('new connection')
  })

app.get('/', function (req, res) {
    const wsc = new WebSocket('http://localhost:3002')
})


module.exports = app