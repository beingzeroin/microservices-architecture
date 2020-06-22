const express = require('express')
const app = express()
const morgan = require('morgan')
const config = require('./backend/config/globalconfig')
const dbconnect = require('./backend/db/mongoconnect');

const port = config.web_port

//dbconnect.connect(true);

app.use(morgan('dev'))
app.use(express.json())

function logRequest(req){
    console.log("HEADERS: " + JSON.stringify(req.headers));
    console.log("PARAMS: " + JSON.stringify(req.params));
    console.log("QUERY: " + JSON.stringify(req.query));
    console.log("BODY: " + JSON.stringify(req.body));
}

app.get('/', function(req, res){
    logRequest(req);
    res.send( 'Welcome to '+config.service_name+' '+config.service_version);
})

app.post('/api/v1/register', function(req, res){
    logRequest(req);
    res.json({
        username: '',
        token: '',
        message: ''
    });
})
app.post('/api/v1/login', function(req, res){
    logRequest(req);
    res.json({
        username: '',
        token: '',
        message: ''
    });
})


app.get('/api/v1/greeting/public', function(req, res){
    res.json({ greeting: "Welcome to API"});
})

app.get('/api/v1/greeting/protected', function(req, res){
    res.json({  message: "Welcome to Authenticated API" });
})

app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`))