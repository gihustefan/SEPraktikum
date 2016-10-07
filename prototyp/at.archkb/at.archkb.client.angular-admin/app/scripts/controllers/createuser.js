'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:CreateuserCtrl
 * @description
 * # CreateuserCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('CreateuserCtrl', function ($scope, $rootScope, $location, userservice) {
    $scope.email;
    $scope.username;
    $scope.create = function () {
      $scope.buttonDisabled = true;
      if ($scope.createuser.email.$error.required || $scope.createuser.email.$error.email || $scope.createuser.username.$error.required) {
        $scope.buttonDisabled = false;
        return;
      }
      var user = new Object();
      user.username = $scope.username;
      user.email = $scope.email;
      userservice.create(user).then(function (response) {
        if (!(response && response.data)) {
          $scope.buttonDisabled = false;
          $rootScope.showToast("Couldn't create user");
          return;
        }
        if (!(response.data.id && response.data.password)) {
          $scope.buttonDisabled = false;
          $rootScope.showToast("Couldn't create user");
          return;
        }
        $scope.password = response.data.password;
        $scope.id = response.data.id;
        $("#userPasswordModal").modal();
      }, function (error){
        $rootScope.showToast("Couldn't create user");
        $scope.buttonDisabled = false;
      });
    }

    $("#userPasswordModal").on('hidden.bs.modal', function (e) {
      if ($scope.id) {
        window.location = "#/userdetail/" + $scope.id;
        //$location.path("#/userdetail/" + $scope.id);
      }
    })

  });
