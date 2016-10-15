var MyFirstController = function($scope, $http, userData, userGravatar, gitHubUserLookup) {
    $scope.ManyHellos = ['Hello', 'Hola', 'Bonjour', 'Guten Tag', 'Ciao', 'Namaste', 'Yiasou'];

    $scope.data = userData.user;

    $scope.getGravatar = function(email) {
        return userGravatar.getGravatar(email);
    };

    $scope.getGitHubUser = function(username) {
        console.log("username: " + username);
        gitHubUserLookup.lookupUser(username).then(onLookupComplete, onError);
    };

    var onLookupComplete = function(response) {
        $scope.user = response.data;
        $scope.status = response.status;

    };

    var onError = function(reason) {
        $scope.error = "Ooops, something went wrong..";
    };
};

var AuthenticationController = function($scope, $rootScope, AUTH_EVENTS, AuthService) {
    $scope.credentials = {
        username: '',
        password: ''
    };
    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function (user) {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $scope.setCurrentUser(user);
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
        });
    };
};

var ApplicationController = function($scope, USER_ROLES, AuthService) {
    $scope.currentUser = null;
    $scope.userRoles = USER_ROLES;
    $scope.isAuthorized = AuthService.isAuthorized;
    $scope.isLoginPage = false;
    $scope.setCurrentUser = function (user) {
        $scope.currentUser = user;
    };
};

app.controller("MyFirstController", MyFirstController);
app.controller("AuthenticationController", AuthenticationController);
app.controller("ApplicationController", ApplicationController);