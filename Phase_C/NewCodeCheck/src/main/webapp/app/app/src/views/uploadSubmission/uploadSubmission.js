'use strict';

angular.module('codeChecker')

    .controller('UploadSubmissionCtrl', function(sessionService) {
        var vm=this;
        function init() {
            vm.validateFileType=validateFileType;
            vm.loginLoader=0;
            vm.logout=function () {
                sessionService.logout();
            }
        }
        init();


        function validateFileType (files) {
            files.forEach(function (file) {
                var completeFileName= file.name;
                var fileExt=completeFileName.substring(completeFileName.lastIndexOf(".")+1);
                if(fileExt!=='java'){
                    vm.error="One or more files you have selected does not have the .java extension";
                    return;
                }
            });
        };
    });