angular
    .module('codeChecker')
    .factory('assignmentService', function ($http, $resource){
        var service = {};
        service.createAssignment = function(userId, assignment) {
            var Assignment = $resource("/rest/accounts/:paramAccountId/assignments");
            return Assignment.save({paramAccountId: userId}, assignment).$promise;
        };

        service.getAllAssignments = function() {
            var Assignment = $resource("/rest/assignments");
            return Assignment.get().$promise.then(function(data) {
                return data.assignments;
            });
        };

        service.compareAssignments = function (id1, id2) {
            return $http.get("/rest/assignment-submissions/compare?assignmentId="+id1+"&otherAssignmentId="+id2);
        };

        service.getAssignmentsForAccount = function(accountId) {
            var deferred = $q.defer();

            var Account = $resource("/rest/accounts/:paramAccountId");
            Account.get({paramAccountId:accountId}, function(account) {
                var Assignment = account.resource('assignments');
                Assignment.get(null,
                    function(data) {
                        deferred.resolve(data.assignments);
                    },
                    function() {
                        deferred.reject();
                    }
                );
            });
            return deferred.promise;
        };

        return service;
    });