'use strict';

angular.module('codeChecker.signUp', ['ngRoute'])

  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/sign-up', {
        templateUrl: 'src/signUp/signUp.html',
        controller: 'SignUpCtrl',
        controllerAs: "model"

    });
  }])

  .controller('SignUpCtrl', function() {

  });