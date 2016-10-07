'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:ApattributedetailCtrl
 * @description
 * # ApattributedetailCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('ApattributedetailCtrl', function ($scope, $rootScope, $routeParams, $location, apattributeservice) {

    $scope.apat;
    $scope.edit = false;

    $scope.apatid = $routeParams.id;

    $scope.coreAdd = function () {
      if (!$scope.apat || $scope.apat.coreAdded > 0) {
        $rootScope.showToast("Couldn't add to Core");
        return;
      }
      apattributeservice.addToCore($scope.apatid).then(function (response) {
        if (!(response && response.data)) {
          $rootScope.showToast("Couldn't add to Core");
          return;
        }
        handleInputAPAT(response.data);
      }, function (error) {
        $rootScope.showToast("Couldn't add to Core");
      });
    }

    $scope.coreRemove = function () {
      if (!$scope.apat || $scope.apat.coreAdded <= 0) {
        $rootScope.showToast("Couldn't remove from Core");
        return;
      }
      apattributeservice.removeFromCore($scope.apatid).then(function (response) {
        if (!(response && response.data)) {
          $rootScope.showToast("Couldn't remove from Core");
          return;
        }
        handleInputAPAT(response.data);
      }, function (error) {
        $rootScope.showToast("Couldn't remove from Core");
      });
    }

    $scope.update = function () {
      if (!$scope.apat) {
        $rootScope.showToast("Couldn't update Core Data");
        return;
      }
      $scope.edit = false;
      apattributeservice.update($scope.apat).then(function (response) {
        if (!(response && response.data)) {
          $rootScope.showToast("Couldn't update Core Data");
          return;
        }
        handleInputAPAT(response.data);
      }, function (error) {
        $rootScope.showToast("Couldn't update Core Data");
      });
    }

    $scope.showCreator = function () {
      if ($scope.apat.creatorEmail && $scope.apat.creatorId) {
        $location.path("/userdetail/" + $scope.apat.creatorId);
      }
    }

    var getById = function () {
      if (!$scope.apatid) {
        $rootScope.showToast("Couldn't load Core Data");
        return;
      }
      apattributeservice.getById($scope.apatid).then(function (response) {
        if (!(response && response.data)) {
          $rootScope.showToast("Couldn't load Core Data");
          return;
        }
        handleInputAPAT(response.data);
      }, function (error) {
        $rootScope.showToast("Couldn't load Core Data");
      });
    }

    var handleInputAPAT = function (apat) {
      $scope.apat = apat;
      console.log($scope.apat);
    }

    getById();

  });
