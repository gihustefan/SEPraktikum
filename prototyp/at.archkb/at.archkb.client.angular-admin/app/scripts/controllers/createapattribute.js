'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:CreateapattributeCtrl
 * @description
 * # CreateapattributeCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('CreateapattributeCtrl', function ($scope, $rootScope, $location, apattributeservice) {
    $scope.allTypes = ["QualityAttribute", "Architecturestyle", "Constraint", "Driver", "TradeoffItem", "DesignDecision"];
    
    $scope.apat=new Object();
    
    $scope.create = function () {
      $scope.buttonDisabled = true;
      var apat = $scope.apat;
      if (!(apat.name&&apat.name.length>=5&&apat.definition&&apat.definition.length>=5&&apat.type!=='')) {
        $scope.buttonDisabled = false;
        $rootScope.showToast("Couldn't create attribute");
        return;
      }
      if($scope.isCore){
        apat.coreAdded = new Date().getTime();
      }
      apattributeservice.create(apat).then(function (response) {
        if (!(response && response.data && response.data.id)) {
          $scope.buttonDisabled = false;
          $rootScope.showToast("Couldn't create attribute");
          return;
        }
        $location.path("/apattributedetail/"+response.data.id);
      }, function (error){
        $scope.buttonDisabled = false;
        $rootScope.showToast("Couldn't create attribute");
      });
    }
    
  });
