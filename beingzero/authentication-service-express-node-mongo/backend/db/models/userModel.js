var mongoose  = require('mongoose');
var config = require('../../config/globalconfig');
var bcryptLib = require('../../lib/bcryptLib');

var UserSchema = new mongoose.Schema({
  username: { type: String, required: true, unique: true},
  password: { type: String},
  email: { type: String, default: null, unique: true  }
});

/**
 * Before save a user document, Make sure:
 * to Hash user's password
 */
UserSchema.pre('save', function(next) {
  var user = this;

  // only hash the password if it has been modified (or is new)
  if (!user.isModified('password')) return next();

  bcryptLib.hashPassword(user.password, function(err, hash){
    if(err) return next(err);
    user.password = hash;
    next();
  })
});

var userModel = mongoose.model('user', UserSchema);

module.exports = userModel;