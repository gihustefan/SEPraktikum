/**
 * Created by Rainer on 25.05.2016.
 */
angular.module('archkb').run(function($rootScope, $timeout, $translate, SecurityService, MESSAGE_TYPE) {

    var updateAuthStatus = function () {
        $rootScope.authenticated = SecurityService.authenticated;
        $rootScope.principal = SecurityService.principal;
        $rootScope.$broadcast('authenticationChanged');
        $rootScope.global.showLoad = false;
    };

    $rootScope.global = {};

    $rootScope.global.showLoad = false;

    // Info about a single, current Message to display (e.g. current error occured)
    $rootScope.global.message = {
        show: false,
        description: '',
        type: MESSAGE_TYPE.INFO
    };

    $rootScope.global.login = function (username, password) {

        $rootScope.global.showLoad = true;

        SecurityService.login(username, password).then(updateAuthStatus, function () {
            // Setting the current Message Properties accordingly to display the message
            $rootScope.global.message.show = true;
            $rootScope.global.showLoad = false;
            $translate('LOGIN_ERROR').then(function(translation) {
                $rootScope.global.message.description = translation;
            });
            $rootScope.global.message.type = MESSAGE_TYPE.ERROR;

            updateAuthStatus();
        });
    };

    $rootScope.global.message.close = function () {
        $rootScope.global.message.show = false;
    };

    $rootScope.global.logout = function () {
        SecurityService.logout().then(updateAuthStatus, updateAuthStatus);
    };


    // Do not show the global message all the time -> hide it automatically after 4 seconds.
    $rootScope.$watch('global.message.show', function() {
        if($rootScope.global.message.show) {
            $timeout(function() {
                $rootScope.global.message.show = false;
            }, 4000);
        }
    });
});