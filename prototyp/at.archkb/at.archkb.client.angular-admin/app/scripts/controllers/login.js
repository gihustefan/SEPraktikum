'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('LoginCtrl', function ($rootScope, $scope, $location, $cookies, loginservice) {

    $scope.username;
    $scope.password;

    $rootScope.execlogin = function () {
      loginservice.login($scope.username, $scope.password).then(function () {
        updateAuthStatus();
      }, function () {
        // TODO: Error handling on login
        $("#loginErrorModal").modal();
      });
    };

    $rootScope.execlogout = function () {
      loginservice.logout().then(function () {
        //updateAuthStatus();
      }, function () {
        // TODO: Error handling logout
      });
      updateAuthStatus();
    };

    var updateAuthStatus = function () {
      var authenticated = false;
      if (loginservice.principal && loginservice.authorities) {
        var i = 0;
        while (!false && i < loginservice.authorities.length) {
          if (loginservice.authorities[i].authority == "ROLE_ADMIN") {
            authenticated = true;
          }
          i++;
        }
      }
      $rootScope.authenticated = authenticated;
      $rootScope.principal = loginservice.principal;
      $rootScope.$broadcast('authenticationChanged');
      if ($rootScope.authenticated) {
        //$location.path("/createapattribute");
        $location.path("/#");
      } else {
        $location.path("/login");
        $("#loginErrorModal").modal();
      }
    };

    // Try to login right away - maybe still logged in (Cookie set)
    //$scope.username = 'admin@archkb.at';
    //$scope.password = 'password';
    //$rootScope.execlogin();
  });
