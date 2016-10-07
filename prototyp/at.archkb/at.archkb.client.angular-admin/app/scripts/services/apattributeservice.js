'use strict';

/**
 * @ngdoc service
 * @name architectureKbadminApp.apattributeservice
 * @description
 * # apattributeservice
 * Factory in the architectureKbadminApp.
 */
angular.module('architectureKbadminApp')
  .factory('apattributeservice', function ($http) {
    // Service logic
    // ...

    var service = {};

    var baseUrl = serverAddress + 'api/admin/apattribute/';

    service.getAll = function (type) {
      return $http.get(baseUrl + type);
    };

    service.getById = function (id) {
      return $http.get(baseUrl + "byid/" + id);
    }

    service.addToCore = function (id) {
      return $http.post(baseUrl + "addCore/" + id);
    };

    service.removeFromCore = function (id) {
      return $http.post(baseUrl + "removeCore/" + id);
    };

    service.update = function (apat) {
      return $http.post(baseUrl, apat);
    };

    service.create = function (apat) {
      return $http.post(baseUrl + "create/", apat);
    };

    return service;
  });
