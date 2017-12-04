angular
    .module('codeChecker', ['ngRoute', 'ngFileUpload', 'ngResource', 'base64'])
    .config(function($locationProvider, $routeProvider, $httpProvider) {

        $httpProvider.defaults.xsrfCookieName = 'csrftoken';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRFToken';

        $routeProvider
            .when('/', {
                templateUrl: 'src/views/login/login.html',
                controller: 'LoginCtrl',
                controllerAs: "model",
                resolve:{
                    loginCheck: alreadyLoggedIn
                }
            })
            .when('/sign-up', {
                templateUrl: 'src/views/signUp/signUp.html',
                controller: 'SignUpCtrl',
                controllerAs: "model",
                resolve:{
                    loginCheck: alreadyLoggedIn
                }
            })
            .when('/upload-submission', {
                templateUrl: 'src/views/uploadSubmission/uploadSubmission.html',
                controller: 'UploadSubmissionCtrl',
                controllerAs: "model",
                resolve:{
                    loginCheck: notLoggedIn,
                    account: getCurrentUser
                }
            })
            .when('/help', {
                templateUrl: 'src/views/help/help.html',
                controller: 'HelpCtrl',
                controllerAs: "model"
            })
            .when('/side-by-side', {
                templateUrl: 'src/views/sideBySide/sideBySide.html',
                controller: 'SideBySideCtrl',
                controllerAs: "model",
                resolve:{
                    loginCheck: notLoggedIn,
                    account: getCurrentUser
                }
            })
            .when('/analysis-result', {
                templateUrl: 'src/views/analysisResults/analysisResults.html',
                controller: 'AnalysisResultsCtrl',
                controllerAs: "model",
                resolve:{
                    loginCheck: notLoggedIn,
                    account: getCurrentUser
                }
            })
            .when('/analysis-history', {
                templateUrl: 'src/views/analysisHistory/analysisHistory.html',
                controller: 'AnalysisHistoryCtrl',
                controllerAs: "model",
                resolve:{
                    loginCheck: notLoggedIn,
                    account: getCurrentUser
                }
            })
            .otherwise({redirectTo: '/'});

        function alreadyLoggedIn(sessionService, $location, accountService) {
            if(sessionService.isLoggedIn()) {
                $location.url("/upload-submission")
            }
        }

        function notLoggedIn(sessionService, $location, accountService) {
            if(!sessionService.isLoggedIn()) {
                $location.url("/")
            }
        }

        function getCurrentUser(accountService, sessionService, $location) {
            return accountService
                .getCurrentUser()
                .then(function (data) {
                    return data;
                }, function () {
                    sessionService.unsetStorage();
                    $location.url("/");
                })
        }
    });
