'use strict';

// Declare app level module which depends on views, and components
angular.module('codeChecker', [
    'ngRoute',
    'ngFileUpload',
    'codeChecker.login',
    'codeChecker.signUp',
    'codeChecker.analysisHistory',
    'codeChecker.analysisResults',
    'codeChecker.sideBySide',
    'codeChecker.uploadSubmission',
    'codeChecker.help'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $routeProvider.otherwise({redirectTo: '/'});
}]);
