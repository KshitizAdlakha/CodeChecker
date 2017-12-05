(function() {
    angular.module('codeChecker')
        .controller('SignUpCtrl', function($location, sessionService, accountService) {
            var vm = this;
            function init() {
                vm.registerLoader = 0;
                vm.register = register;
                vm.showPassword = false;
                vm.toggleShowPassword=toggleShowPassword;
            }

            init();

            function register(account) {
                accountService.register(account,
                    function(data) {
                        sessionService
                            .login(account)
                            .then(function() {
                                localStorage.setItem("session", {});
                                $location.url('/upload-submission');
                            }, function () {
                                vm.registerLoader = 0;
                                vm.error="Registered successfully. Try logging in from the login page"
                            });
                        },
                    function(err) {
                        vm.registerLoader = 0;
                        vm.error="Failed to register. Try changing the username";
                    });
            }

            function toggleShowPassword() {
                vm.showPassword = !vm.showPassword;
            }
        });
})();