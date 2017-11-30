'use strict';

angular.module('codeChecker')

    .controller('UploadSubmissionCtrl', function(account, sessionService, assignmentService, Upload) {
        var vm=this;
        function init() {
            vm.currentUser=account.data;
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

        function createAssignment(assignment) {
            assignmentService
                .createAssignment(vm.currentUser.rid, assignment)
                .then(function () {
                    console.log("Success");
                }, function () {
                    console.log("Error");
                });
        }

        function uploadFiles(assignment) {
            assignment.owner=vm.currentUser;

            Upload.upload({
                url: 'upload',
                fields: {'username': 'zouroto'}, // additional data to send
                file: file
            }).progress(function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
            }).success(function (data, status, headers, config) {
                console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
            });
            createAssignment(assignment);
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
            }
            // files.forEach(function (file) {
            //     var completeFileName= file.name;
            //     var fileExt=completeFileName.substring(completeFileName.lastIndexOf(".")+1);
            //     if(fileExt!=='java'){
            //         vm.error="One or more files you have selected does not have the .java extension";
            //         return;
            //     } else {
            //         console.log("Hello")
            //     }
            // });
        };
    });