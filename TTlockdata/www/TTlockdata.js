var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'TTlockdata', 'coolMethod', [arg0]);
};

exports.lockdata = function (arg0, success, error) {
    exec(success, error, 'ttlock', 'lockdata', [arg0]);
};
module.exports=ttlock;