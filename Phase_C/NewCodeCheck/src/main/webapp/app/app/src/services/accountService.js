(function () {
    var app = angular.module('codeChecker');
    app.factory('accountService', accountService);

    function accountService ($resource, $http) {
        var service = {};
        service.register = function(account, success, failure) {
            var Account = $resource("/code-checker/rest/accounts");
            Account.save({}, account, success, failure);
        };
        service.getCurrentUser = function () {
           return $http.get("/code-checker/rest/accounts/getMeId");
        };
        service.getAccountById = function(accountId) {
            var Account = $resource("/code-checker/rest/accounts/:paramAccountId");
            return Account.get({paramAccountId:accountId}).$promise;
        };
        service.getAccountByUsername = function(username) {
            var Account = $resource("/code-checker/rest/accounts/check-username/:username");
            return Account.get({username:username}).$promise;
        };
        service.userExists = function(account, success, failure) {
            var Account = $resource("/code-checker/rest/accounts");
            var data = Account.get({name:account.name, password:account.password}, function() {
                    var accounts = data.accounts;
                    if(accounts.length !== 0) {
                        success(account);
                    } else {
                        failure();
                    }
                },
                failure);
        };
        return service;
    };
})();