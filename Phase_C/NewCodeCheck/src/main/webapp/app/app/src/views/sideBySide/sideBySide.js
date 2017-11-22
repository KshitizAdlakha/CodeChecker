angular.module('codeChecker')
    .controller('SideBySideCtrl', function(sessionService, $http) {
        var vm = this;
        function init() {
            vm.logout=function () {
                sessionService.logout();
            };
            vm.sideBySideDiff=sideBySideDiff();
            vm.file1Name="AccountController";
            vm.file2Name="AssignmentController";
        }
        init();
        sideBySideDiff();

        function sideBySideDiff() {

        }
    });