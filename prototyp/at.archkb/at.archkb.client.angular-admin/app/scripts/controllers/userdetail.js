'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:UserdetailCtrl
 * @description
 * # UserdetailCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('UserdetailCtrl', function ($scope, $rootScope, $routeParams, userservice) {
    $scope.user;
    $scope.edit = false;

    $scope.userid = $routeParams.id;

    userservice.getById($scope.userid).then(function (response) {
      if (!(response && response.data)) {
        return;
      }
      handleIncomingUser(response.data);
    });

    $scope.deactivate = function () {
      userservice.deactivate($scope.userid).then(function (response) {
        if (!(response && response.data)) {
          $rootScope.showToast("Couldn't deactivate user");
          return;
        }
        $scope.user.dateLocked = response.data.dateLocked;
        $scope.user.dateActivated = response.data.dateActivated;
      }, function(error){
         $rootScope.showToast("Couldn't deactivate user");
      });
    };

    $scope.activate = function () {
      userservice.activate($scope.userid).then(function (response) {
        if (!(response && response.data)) {
          $rootScope.showToast("Couldn't activate user");
          return;
        }
        $scope.user.dateLocked = response.data.dateLocked;
        $scope.user.dateActivated = response.data.dateActivated;
      }, function(error){
        $rootScope.showToast("Couldn't activate user");
      });
    };

    $scope.update = function () {
      $scope.edit = false;
      userservice.update($scope.user).then(function (response) {
        if (!(response && response.data)) {
          $rootScope.showToast("Couldn't update user");
          return;
        }
        handleIncomingUser(response.data);
      }, function (error){
        $rootScope.showToast("Couldn't update user");
      });
    }

    $scope.resetpassword = function () {
      $scope.resetpwdisabled = true;
      userservice.resetpassword($scope.user.id).then(function (response) {
        if (!(response && response.data && response.data.password)) {
          $scope.resetpwdisabled = false;
          $rootScope.showToast("Couldn't reset password");
          return;
        }
        $scope.password=response.data.password;
        $("#userPasswordModal").modal();
        $scope.resetpwdisabled = false;
      }, function (error){
         $rootScope.showToast("Couldn't reset password");
         $scope.resetpwdisabled = false;
      });
    }

    var handleIncomingUser = function (user) {
      $scope.user = user;
      //User and Company same City
      /*if(typeof $scope.user.city == "number"){
        $scope.user.city = $scope.user.company.city;
      }
      if(typeof $scope.user.company.city == "number"){
        $scope.user.company.city = $scope.user.city;
      }*/
      console.log($scope.user);
    }

  });
