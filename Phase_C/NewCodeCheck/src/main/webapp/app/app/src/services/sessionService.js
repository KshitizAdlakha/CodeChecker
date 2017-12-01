(function() {
    angular
        .module('codeChecker')
        .service('sessionService', function ($http, $base64, $location) {
            var session = {};
            session.login = function(data) {
                return $http.post("/login", "username=" + data.name +
                    "&password=" + data.password, {
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                });
            };
            session.logout = function() {
                localStorage.removeItem("session");
            };
            session.isLoggedIn = function() {
                return localStorage.getItem("session") !== null;
            };
            session.unsetStorage = function () {
                localStorage.removeItem("session");
            };
            return session;
        });
})();