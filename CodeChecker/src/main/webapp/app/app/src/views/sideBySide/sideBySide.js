angular.module('codeChecker')
    .controller('SideBySideCtrl', function(sessionService, $http, assignmentService, $routeParams) {
        var vm = this;
        function init() {
            vm.logout=function () {
                sessionService.logout();
            };

            vm.sideBySideDiff=sideBySideDiff;
            vm.file1Name=$routeParams.submissionId1;
            vm.file2Name=$routeParams.submissionId2;
        }
        init();
        sideBySideDiff();

        function sideBySideDiff() {
            assignmentService
                .compareAssignments(vm.file1Name, vm.file2Name)
                .then(function (data) {
                    vm.similarityPercentage=data.data;
                    console.log(data.data);
                }, function () {
                    vm.error="Failed to retrieve Assignments"
                })
        }
    });