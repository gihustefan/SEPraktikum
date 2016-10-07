'use strict';

/**
 * @ngdoc service
 * @name architectureKbadminApp.loginservice
 * @description
 * # loginservice
 * Factory in the architectureKbadminApp.
 */
angular.module('architectureKbadminApp')
  .factory('loginservice', function ($http, $q) {
    // Service logic
    // ...

    var service = {};

    service.login = function (username, password) {
      $http.defaults.headers.common['Authorization'] = null;

      var url = serverAddress+'api/authentications/user';
      var headers = username && password ? {
        'Authorization': "Basic " + btoa(username + ":" + password)
      } : {};


      // Promise (resolved on successful authentication, reject on unsuccessful authentication or error)
      var deferred = $q.defer();
      $http.get(url, { headers: headers }).success(function (user) {
        if (user.authenticated) {
          service.authenticated = true;
          service.principal = user.principal;
          service.authorities = user.authorities;
          $http.defaults.headers.common['Authorization'] = "Basic " + btoa(username + ":" + password);
          deferred.resolve();
        } else {
          clearAuth();
          deferred.reject();
        }
      }).error(function () {
        clearAuth();
        deferred.reject();
      });

      return deferred.promise;
    };

    service.logout = function () {
      $http.defaults.headers.common['Authorization'] = null;
      clearAuth();
      
      var url = serverAddress+'api/logout';

      var deferred = $q.defer();

      // Make a logout rest call to the server and set authenticated to false
      $http.post(url, {}).success(function () {
        
        deferred.resolve();
      }).error(function () {
        deferred.reject();
      });

      return deferred.promise;
    };


    service.isPrincipalOwnerOfArchProfile = function (archProfile) {
      return service.principal !== null && archProfile.ownerName === service.principal.username;
    };

    var clearAuth = function () {
      service.authenticated = false;
      service.principal = null;
      service.authorities = null;
    };

    clearAuth();

    return service;
  });
