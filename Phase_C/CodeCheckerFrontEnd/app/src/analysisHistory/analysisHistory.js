'use strict';

angular.module('codeChecker.analysisHistory', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/analysis-history', {
            templateUrl: 'src/analysisHistory/analysisHistory.html',
            controller: 'AnalysisHistoryCtrl',
            controllerAs: "model"
        });
    }])

    .controller('AnalysisHistoryCtrl', function() {
        function init() {
            var vm=this;
            vm.assignments=[];
        }
        init();

    });