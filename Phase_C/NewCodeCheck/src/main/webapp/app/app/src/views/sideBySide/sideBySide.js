angular.module('codeChecker')
    .controller('SideBySideCtrl', function(sessionService) {
        var vm = this;
        function init() {
            vm.logout=function () {
                sessionService.logout();
            }
        }
        init();
    });