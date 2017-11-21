'use strict';

angular.module('codeChecker')

    .controller('AnalysisResultsCtrl', function(sessionService) {
        var vm=this;
        function init() {
            vm.findResultsByAssignmentId=findResultsByAssignmentId;
            vm.loginLoader=0;
            vm.logout=function () {
                sessionService.logout();
            }
        }
        init();
        
        function findResultsByAssignmentId() {

        }
    });