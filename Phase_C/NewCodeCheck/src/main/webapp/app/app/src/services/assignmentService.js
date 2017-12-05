angular
    .module('codeChecker')
    .factory('assignmentService', function ($http, $resource){
        var service = {};
        service.createAssignment = function(userId, assignment) {
            assignment.title=userId+"_"+assignment.title;
            return $http.post("/code-checker/rest/accounts/"+userId+"/assignments", assignment)
        };

        service.getAllAssignments = function() {
            var Assignment = $resource("/code-checker/rest/assignments");
            return Assignment.get().$promise.then(function(data) {
                return data.assignments;
            });
        };

        service.createAssignmentSubmissions = function (assignmentId, assignmentSubmission) {
            return $http.post("/code-checker/rest/assignments/"+assignmentId+"/assignment-submissions", assignmentSubmission)
        };

        service.compareAssignments = function (id1, id2) {
            return $http.get("/code-checker/rest/assignment-submissions/compare?assignmentId="+id1+"&otherAssignmentId="+id2);
        };

        service.checkIfAssignmentAlreadyExists = function (assignment, accountId) {
            return $http.get("/code-checker/rest/assignments/name/"+accountId+"_"+assignment.title);
        };

        service.getAssignmentsForAccount = function(accountId) {
            var deferred = $q.defer();

            var Account = $resource("/code-checker/rest/accounts/:paramAccountId");
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