var bcrypt    = require('bcryptjs');
var config = require('../config/globalconfig');

const SALT_WORK_FACTOR = config.password_hashing_salt_work_factor;


module.exports.hashPassword = function(password, callback){
    // generate a salt
    bcrypt.genSalt(SALT_WORK_FACTOR, function(err, salt) {
        // hash the password using our new salt
        bcrypt.hash(password, salt, callback);
    });
}

module.exports.comparePassword = function(userSuppliedPassword, hashedPassword, callback){
    bcrypt.compare(userSuppliedPassword, hashedPassword, function(err, isMatch) {
        if (err) return callback(err);
        console.log("MATCH: " + isMatch+"")
        callback(null, isMatch);
    });
}