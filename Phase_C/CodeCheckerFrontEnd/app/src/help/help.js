'use strict';

angular.module('codeChecker.help', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
      $routeProvider.when('/help', {
          templateUrl: 'src/help/help.html',
          controller: 'HelpCtrl',
          controllerAs: "model"
      });
    }])

    .controller('HelpCtrl', function() {

    });