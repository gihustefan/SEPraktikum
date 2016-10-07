'use strict';

/**
 * @ngdoc service
 * @name architectureKbadminApp.userservice
 * @description
 * # userservice
 * Factory in the architectureKbadminApp.
 */
angular.module('architectureKbadminApp')
  .factory('userservice', function ($http) {
    // Service logic
    // ...

    var service = {};

    var baseUrl = serverAddress + 'api/admin/user/';

    // Getting all the Users
    service.getAll = function () {
      return $http.get(baseUrl);
    };

    service.getById = function (id) {
      return $http.get(baseUrl + id);
    };

    service.deactivate = function (id) {
      return $http.post(baseUrl + 'deactivate/' + id);
    };

    service.activate = function (id) {
      return $http.post(baseUrl + 'activate/' + id);
    };

    service.update = function (user) {
      return $http.post(baseUrl, user);
    };

    service.create = function (user) {
      return $http.post(baseUrl + "create/", user);
    };

    service.resetpassword = function (id) {
      return $http.post(baseUrl + "resetpw/"+ id);
    };

    return service;

  });
