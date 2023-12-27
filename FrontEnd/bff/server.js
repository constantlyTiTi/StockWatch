const express = require('express')
const cors = require('cors');
// const hbs = require('hbs')
const routers = require('./routers/routers')
const path = require('path')
const httpConfig = require('./config/http-config.json')
const app = express()
const PORT = 3001

app.use(cors({
    origin:['http://localhost:3000'],
    credentials:true, 
    methods:['GET','POST','OPTION','DELETE','PUT'],
    optionSuccessStatus:200,
    allowedHeaders: ['Content-Type', 'Authorization']
}));


app.use('/', routers)

app.listen(PORT, () => {
  console.log(`app is running on PORT ${PORT}`)
})

module.exports = app