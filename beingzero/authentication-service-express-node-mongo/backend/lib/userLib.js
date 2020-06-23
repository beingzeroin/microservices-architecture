const users = [
    {
        username: 'beingzero',
        password: 'beingzeroadmin',
        email: 'beingzeroin@gmail.com'
    }, 
    {
        username: 'admin',
        password: 'admin',
        email: 'admin@beingzero.in'
    }
];

module.exports.isValidUser = function(inputUser){
    const user = users.find(u => { return u.username === inputUser.username && u.password === inputUser.password });
    return user;
}