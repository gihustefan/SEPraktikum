'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:AlluserCtrl
 * @description
 * # AlluserCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('AlluserCtrl', function ($scope, $rootScope, $location, userservice) {
    $scope.users;
    userservice.getAll().then(function (response) {
      if(!(response&&response.data)){
        return;
      }
      $scope.users=response.data;
    });
    
    $scope.navigate = function(id){
				 $location.path("/userdetail/"+id);
			}
  });
