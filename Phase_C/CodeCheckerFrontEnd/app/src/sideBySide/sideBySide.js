'use strict';

angular.module('codeChecker.sideBySide', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/side-by-side', {
            templateUrl: 'src/sideBySide/sideBySide.html',
            controller: 'SideBySideCtrl',
            controllerAs: "model"
        });
    }])

    .controller('SideBySideCtrl', function() {

    });