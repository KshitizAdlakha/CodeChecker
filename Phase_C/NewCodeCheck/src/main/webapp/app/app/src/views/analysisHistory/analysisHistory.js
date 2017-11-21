'use strict';

angular.module('codeChecker')

    .controller('AnalysisHistoryCtrl', function(sessionService) {
        function init() {
            var vm=this;
            vm.assignments=[];
            vm.logout=function () {
                sessionService.logout();
            }
        }
        init();

    });