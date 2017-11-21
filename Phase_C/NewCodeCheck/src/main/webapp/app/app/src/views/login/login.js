(function () {
    var app = angular.module('codeChecker');

    app.controller('LoginCtrl', LoginCtrl);

    function LoginCtrl(accountService, $location, sessionService) {
        var vm = this;

        function init() {
            vm.loginLoader = 0;
            vm.login = login;
        }

        init();

        function login(account) {
            accountService.userExists(account, function (account) {
                sessionService
                    .login(account)
                    .success(function () {
                        localStorage.setItem("session", {});
                        $location.url('/upload-submission');
                    })
                    .error(function () {
                        vm.loginLoader = 0;
                        vm.error = "Invalid credentials";
                    });
            }, function () {
                vm.loginLoader = 0;
                vm.error = "Account does not exist"
            });
        }
    };
})();