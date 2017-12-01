(function () {
    var app = angular.module('codeChecker');

    app.controller('LoginCtrl', LoginCtrl);

    function LoginCtrl(accountService, $location, sessionService) {
        var vm = this;

        function init() {
            vm.loginLoader = 0;
            vm.login = login;
            vm.showPassword = false;
            vm.toggleShowPassword=toggleShowPassword;
        }

        init();

        function login(account) {
            accountService.userExists(account, function (account) {
                sessionService
                    .login(account)
                    .success(function (data) {
                        localStorage.setItem("session", {});
                        $location.url('/upload-submission');
                    })
                    .error(function () {
                        vm.loginLoader = 0;
                        vm.error = "Invalid credentials";
                    });
            }, function () {
                vm.loginLoader = 0;
                vm.error = "The email/password combination is incorrect"
            });
        }

        function toggleShowPassword() {
            vm.showPassword = !vm.showPassword;
        }
    };
})();