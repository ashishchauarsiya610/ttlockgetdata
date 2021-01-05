var exec = require('cordova/exec');
var TTlockdata={};
TTlockdata.coolMethod = function (arg0, success, error) {
    exec(success, error, 'TTlockdata', 'coolMethod', [arg0]);
};
TTlockdata.init = function (arg0, success, error) {
    exec(success, error, 'TTlockdata', 'init', [arg0]);
};
TTlockdata.lockscan = function (arg0, success, error) {
    exec(success, error, 'TTlockdata', 'lockscan', [arg0]);
};
TTlockdata.lockdata = function (arg0, success, error) {
    exec(success, error, 'TTlockdata', 'lockdata', [arg0]);
};
module.exports=TTlockdata;