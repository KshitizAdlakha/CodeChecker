'use strict';

angular.module('codeChecker.uploadSubmission', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/upload-submission', {
            templateUrl: 'src/uploadSubmission/uploadSubmission.html',
            controller: 'UploadSubmissionCtrl',
            controllerAs: "model"
        });
    }])

    .controller('UploadSubmissionCtrl', function() {
        var vm=this;
        function init() {
            vm.validateFileType=validateFileType;
            vm.loginLoader=0;
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