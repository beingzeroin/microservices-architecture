var UserModel = require('../db/models/userModel');
var bcryptLib = require('./bcryptLib')
// const users = [
//     {
//         username: 'beingzero',
//         password: 'beingzeroadmin',
//         email: 'beingzeroin@gmail.com'
//     }, 
//     {
//         username: 'admin',
//         password: 'admin',
//         email: 'admin@beingzero.in'
//     }
// ];

module.exports.isValidUser = function(inputUser, callback){
    UserModel.findOne({username: inputUser.username}, function(err, user){
        console.log("USER: " + JSON.stringify(user));
        if(!user || err)
            return callback({'error': 'User is not registered'}, null);
        bcryptLib.comparePassword(inputUser.password, user.password, function(err, result){
            callback(err, result);
        })
    })
    //const user = users.find(u => { return u.username === inputUser.username && u.password === inputUser.password });
    //return user;
}

module.exports.createUser = function(inputUser, callback){

    var user = new UserModel(inputUser);
    user.save(function(err){
        if(err)
            console.log(JSON.stringify(err));
        callback(err, user);
    });
}