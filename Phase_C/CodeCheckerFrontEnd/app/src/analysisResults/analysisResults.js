'use strict';

angular.module('codeChecker.analysisResults', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/analysis-result', {
            templateUrl: 'src/analysisResults/analysisResults.html',
            controller: 'AnalysisResultsCtrl',
            controllerAs: "model"
        });
    }])

    .controller('AnalysisResultsCtrl', function() {
        var vm=this;
        function init() {
            vm.findResultsByAssignmentId=findResultsByAssignmentId;
            vm.loginLoader=0;
        }
        init();
        
        function findResultsByAssignmentId() {

        }
    });