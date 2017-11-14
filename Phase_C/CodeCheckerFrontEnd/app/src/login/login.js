'use strict';

angular.module('codeChecker.login', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
      $routeProvider.when('/', {
          templateUrl: 'src/login/login.html',
          controller: 'LoginCtrl',
          controllerAs: "model"
      });
    }])

    .controller('LoginCtrl', function() {

    });