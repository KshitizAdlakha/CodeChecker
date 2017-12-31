angular.module('codeChecker')
    .controller('HelpCtrl', function(sessionService) {
        var vm = this;
        function init() {
            vm.logout=function () {
                sessionService.logout();
            };
            $(document).ready(function(){
                $('.collapsible').collapsible();
            });
        }
        init();
    });