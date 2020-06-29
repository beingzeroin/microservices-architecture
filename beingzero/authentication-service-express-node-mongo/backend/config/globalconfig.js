const util = require('util');

module.exports = {
    web_port : process.env.PORT || 80,
    service_name : process.env.SERVICE_NAME || "Being Zero Authentication Service",
    service_version : process.env.SERVICE_VERSION || "0.1",
    mongodb_connection_string: process.env.MONGODB_CONNECTION_STRING || 'mongodb://localhost:27017/authsvc',
    jwt_token_generation_secret: 'simplesecret',
    password_hashing_salt_work_factor : 10
}