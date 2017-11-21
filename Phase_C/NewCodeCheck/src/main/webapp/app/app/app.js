angular
    .module('codeChecker', ['ngRoute', 'ngFileUpload', 'ngResource', 'base64'])
    .config(function($locationProvider, $routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'src/views/login/login.html',
                controller: 'LoginCtrl',
                controllerAs: "model",
                resolve:{
                    currentUser: alreadyLoggedIn
                }
            })
            .when('/sign-up', {
                templateUrl: 'src/views/signUp/signUp.html',
                controller: 'SignUpCtrl',
                controllerAs: "model",
                resolve:{
                    currentUser: alreadyLoggedIn
                }
            })
            .when('/upload-submission', {
                templateUrl: 'src/views/uploadSubmission/uploadSubmission.html',
                controller: 'UploadSubmissionCtrl',
                controllerAs: "model",
                resolve:{
                    currentUser: notLoggedIn
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
                    currentUser: notLoggedIn
                }
            })
            .when('/analysis-result', {
                templateUrl: 'src/views/analysisResults/analysisResults.html',
                controller: 'AnalysisResultsCtrl',
                controllerAs: "model",
                resolve:{
                    currentUser: notLoggedIn
                }
            })
            .when('/analysis-history', {
                templateUrl: 'src/views/analysisHistory/analysisHistory.html',
                controller: 'AnalysisHistoryCtrl',
                controllerAs: "model",
                resolve:{
                    currentUser: notLoggedIn
                }
            })
            .otherwise({redirectTo: '/'});

        function alreadyLoggedIn(sessionService, $location) {
            if(sessionService.isLoggedIn()) {
                $location.url("/upload-submission")
            }
        }

        function notLoggedIn(sessionService, $location) {
            if(!sessionService.isLoggedIn()) {
                $location.url("/")
            }
        }
    });
