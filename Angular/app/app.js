var app = angular.module('myfirstangularapp', ['ui.router'])
    .constant('AUTH_EVENTS', {
        loginSuccess: 'auth-login-success',
        loginFailed: 'auth-login-failed',
        logoutSuccess: 'auth-logout-success',
        sessionTimeout: 'auth-session-timeout',
        notAuthenticated: 'auth-not-authenticated',
        notAuthorized: 'auth-not-authorized'
    })
    .constant('USER_ROLES', {
        all: '*',
        admin: 'admin',
        editor: 'editor',
        guest: 'guest'
    }).
    config(function ($stateProvider, USER_ROLES) {
        $stateProvider.state('dashboard', {
            url: '/dashboard',
            templateUrl: 'dashboard/index.html',
            data: {
                authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
            }
        });
        $stateProvider.state('protected-route', {
            url: '/protected',
            resolve: {
                auth: function resolveAuthentication(AuthResolver) {
                    return AuthResolver.resolve();
                }
            }
        });
    }).
    config(function ($httpProvider) {
        $httpProvider.interceptors.push([
            '$injector',
            function ($injector) {
                return $injector.get('AuthInterceptor');
            }
        ]);
    })
    .run(function ($rootScope, AUTH_EVENTS, AuthService) {
        $rootScope.$on('$stateChangeStart', function (event, next) {
            var authorizedRoles = next.data.authorizedRoles;
            if (!AuthService.isAuthorized(authorizedRoles)) {
                event.preventDefault();
                if (AuthService.isAuthenticated()) {
                    // user is not allowed
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                } else {
                    // user is not logged in
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
                }
            }
        });
    });

/*config(function($routeProvider){
  $routeProvider.when('/view',
      {
        templateUrl:'templates/view.html',
        controller:'MyFirstController'

      })

  $routeProvider.otherwise({redirectTo: '/view'});
});*/