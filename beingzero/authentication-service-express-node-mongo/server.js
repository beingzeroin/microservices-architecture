const express = require('express')
const app = express()
const morgan = require('morgan')
const config = require('./backend/config/globalconfig')
const dbconnect = require('./backend/db/mongoconnect');
const userLib = require('./backend/lib/userLib');
const jwtLib = require('./backend/lib/jwtLib');

const port = config.web_port

dbconnect.connect(true);

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
    userLib.createUser(req.body, function(err, user){
        if(user){
            const accessToken = jwtLib.createToken(user);
            res.json({
                username: user.username,
                token: accessToken,
                message: 'Registration Successful'
            });
        }
        else{
            res.json({error: err});
        }
    })
})
app.post('/api/v1/login', function(req, res){
    logRequest(req);
    var user = req.body;
    userLib.isValidUser(user, function(err, userDbObj){
        if(userDbObj){
            const accessToken = jwtLib.createToken(user);
            res.json({
                username: user.username,
                token: accessToken,
                message: 'Login Successful'
            });
        }
        else{
            res.json({message: 'Username or password incorrect'});
        }
    })
})


app.get('/api/v1/greeting/public', function(req, res){
    res.json({ greeting: "Welcome to API"});
})

app.get('/api/v1/greeting/protected', function(req, res){
    const authHeader = req.headers.authorization;
    console.log(authHeader);
    if (authHeader) {
        var authHeaderParts = authHeader.split(' ');
        if(authHeaderParts.length > 1){
            const token = authHeaderParts[1];
            jwtLib.verifyToken(token, (err, user) => {
                if (err) {
                    return res.sendStatus(403);
                }
                res.json({  message: "Welcome to Authenticated API" });
            });
        }
        else
            res.sendStatus(401);
    } else {
        res.sendStatus(401);
    }
})

app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`))