'use strict';

angular.module('codeChecker')

    .controller('UploadSubmissionCtrl', function(account, sessionService, assignmentService, Upload, $location) {
        var vm=this;
        function init() {
            if(account){
                vm.currentUser=account.data;
            }
            vm.validateFileType=validateFileType;
            vm.loader=0;
            vm.logout=function () {
                sessionService.logout();
            };
            vm.uploadFiles=uploadFiles;
            vm.file1=null;
            vm.file2=null;
        }
        init();

        function uploadFiles(assignment, file1, file2) {
            vm.error=null;

            assignmentService
                .checkIfAssignmentAlreadyExists(assignment)
                .then(function () {
                    vm.error="Assignment of the same name already exists";
                    vm.loader=0;
                    },
                function () {
                assignmentService
                    .createAssignment(vm.currentUser.rid, assignment)
                    .then(function (data) {
                        var assignmentId = data.data.rid;
                        var assignment={};
                        assignment.title=data.data.title;
                        assignmentService
                            .createAssignmentSubmissions(assignmentId, assignment)
                            .then(function (response) {
                                var assignmentSubmission1Id=response.data.rid;
                                Upload.upload({
                                    url: 'rest/assignment-submissions/'+assignmentSubmission1Id+'' +
                                    '/upload?${_csrf.parameterName}=${_csrf.token}',
                                    fields: {'assignmentSubmissionId': assignmentSubmission1Id}, // additional data to send
                                    file: file1
                                }).success(function () {
                                    assignmentService
                                        .createAssignmentSubmissions(assignmentId, assignment)
                                        .then(function (response) {
                                            var assignmentSubmission2Id=response.data.rid;
                                            Upload.upload({
                                                url: 'rest/assignment-submissions/'+assignmentSubmission2Id+
                                                '/upload?${_csrf.parameterName}=${_csrf.token}',
                                                fields: {'assignmentSubmissionId': assignmentSubmission2Id}, // additional data to send
                                                file: file2
                                            }).progress(function (evt) {
                                                vm.progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                                            }).success(function () {
                                                vm.loader=0;
                                                $location.url("/side-by-side?submissionId1="+assignmentSubmission1Id+"&submissionId2="+
                                                    assignmentSubmission2Id);
                                            }).error(function () {
                                                vm.loader=0;
                                            });
                                        }, function (err) {
                                            vm.loader=0;
                                            vm.error="File 2 Upload failed";
                                            vm.error="Failed to create Assignment Submission 2";
                                            console.log(err)
                                        });
                                }).error(function () {
                                    vm.loader=0;
                                });
                            }, function (err) {
                                vm.loader=0;
                                vm.error="File 1 Upload failed";
                                console.log(err)
                            });
                    }, function (err) {
                        vm.error="Failed to create Assignment Submission 1";
                    });

                });
        }

        function validateFileType (file) {
            var completeFileName= file.name;
            var fileExt=completeFileName.substring(completeFileName.lastIndexOf(".")+1);
            if(fileExt!=='java'){
                vm.file1=null;
                vm.file2=null;
                vm.error="One or more files you have selected does not have the .java extension";
                vm.invalidFileType=true;
            } else {
                vm.loader=0;
            }
        };
    });