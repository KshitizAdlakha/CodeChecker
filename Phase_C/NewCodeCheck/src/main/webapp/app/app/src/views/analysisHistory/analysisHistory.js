'use strict';

angular.module('codeChecker')

    .controller('AnalysisHistoryCtrl', function(sessionService) {
        var vm=this;
        function init() {
            vm.assignments=[];
            vm.logout=function () {
                sessionService.logout();
            }
        }
        init();
        getAllAssignments();

        function getAllAssignments() {
            vm.assignments=assignmentService.getAllAssignments();
        }
    });