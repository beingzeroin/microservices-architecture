const express = require('express')
const app = express()
const morgan = require('morgan')
const config = require('./backend/config/globalconfig')
const dbconnect = require('./backend/db/mongoconnect');

const port = config.web_port

dbconnect.connect(true);

app.use(morgan('dev'))
app.get('/', (req, res) => res.send( 'Welcome to '+config.service_name+' '+config.service_version));


app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`))