const jwt = require('jsonwebtoken');
const config = require('../../backend/config/globalconfig');

const authenticateJWT = (req, res, next) => {
    const authHeader = req.headers.authorization;

    if (authHeader) {
        const token = authHeader.split(' ')[1];

        jwt.verify(token, accessTokenSecret, (err, user) => {
            if (err) {
                return res.sendStatus(403);
            }

            req.user = user;
            next();
        });
    } else {
        res.sendStatus(401);
    }
};

module.exports.createToken = function(user){
    return jwt.sign({ username: user.username}, config.jwt_token_generation_secret);
}

module.exports.verifyToken = function(token, callback){
    jwt.verify(token, config.jwt_token_generation_secret, (err, user) => {
        console.log("ERR: "+err);
        console.log("USER: "+JSON.stringify(user));
        if (err) {
            return callback({}, null);
        }
        callback(null, user);
    });
}
