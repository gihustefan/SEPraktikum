'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:HeaderCtrl
 * @description
 * # HeaderCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('HeaderCtrl', function ($scope, $rootScope, $mdToast) {
    $rootScope.showmenue = function () {
      if ($rootScope.authenticated) {
        return true;
      }
      return false;
    }
    $rootScope.showToast = function (text) {
      $mdToast.show(
        $mdToast.simple()
          .textContent(text)
          .hideDelay(3000)
      );
    }
  });
