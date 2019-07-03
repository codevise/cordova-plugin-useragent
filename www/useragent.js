var noop = function() { };

var userAgent = {
    get: function (success, fail) {
        if (success) {
           cordova.exec(success, fail || noop, "UserAgent", "get", []);
         } else {
           return false;
        }
    },
    set: function (userAgentString || '', success, fail) {
        cordova.exec(success || noop, fail || noop, "UserAgent", "set", [userAgentString]);
    },
    reset: function (success, fail) {
        cordova.exec(success || noop, fail || noop, "UserAgent", "reset", []);
    }
};
module.exports = userAgent;