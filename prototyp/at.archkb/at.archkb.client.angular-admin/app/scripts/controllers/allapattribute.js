'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:AllapattributeCtrl
 * @description
 * # AllapattributeCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('AllapattributeCtrl', function ($scope, $rootScope, $location, apattributeservice) {

    $scope.allTypes = ["QualityAttribute", "Architecturestyle", "Constraint", "Driver", "TradeoffItem", "DesignDecision"];
    $scope.type = 'QualityAttribute';

    $scope.apats;

    $scope.active = function (type) {
      if (type == $scope.type) {
        return 'active';
      }
      return '';
    }

    $scope.select = function (type) {
      $scope.type = type;
      loadByType();
    }
    
    $scope.navigate = function(id){
      $location.path("/apattributedetail/"+id);
    }

    var loadByType = function () {
      $scope.apats = [];
      apattributeservice.getAll($scope.type).then(function (response) {
        $scope.apats = response.data;
        if (!(response && response.data)) {
          $rootScope.showToast("Couldn't load Core Data");
        }
      }, function (error) {
        $rootScope.showToast("Couldn't load Core Data");
      });
    }

    loadByType();

  });
