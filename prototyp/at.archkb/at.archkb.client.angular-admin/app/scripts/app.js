'use strict';

/**
 * @ngdoc overview
 * @name architectureKbadminApp
 * @description
 * # architectureKbadminApp
 *
 * Main module of the application.
 */
var app = angular
  .module('architectureKbadminApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngMaterial'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      }).when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl',
        controllerAs: 'login'
      })
      .when('/alluser', {
        templateUrl: 'views/alluser.html',
        controller: 'AlluserCtrl',
        controllerAs: 'alluser'
      })
      .when('/userdetail/:id', {
        templateUrl: 'views/userdetail.html',
        controller: 'UserdetailCtrl',
        controllerAs: 'userdetail'
      })
      .when('/createuser', {
        templateUrl: 'views/createuser.html',
        controller: 'CreateuserCtrl',
        controllerAs: 'createuser'
      })
      .when('/allapattribute', {
        templateUrl: 'views/allapattribute.html',
        controller: 'AllapattributeCtrl',
        controllerAs: 'allapattribute'
      })
      .when('/apattributedetail/:id', {
        templateUrl: 'views/apattributedetail.html',
        controller: 'ApattributedetailCtrl',
        controllerAs: 'apattributedetail'
      })
      .when('/createapattribute', {
        templateUrl: 'views/createapattribute.html',
        controller: 'CreateapattributeCtrl',
        controllerAs: 'createapattribute'
      })
      .otherwise({
        redirectTo: '/'
      });
  });

//app.config(function($httpProvider) {
	//$httpProvider.defaults.headers.common.Accept = '*/*';
//	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
//});

var serverAddress = "https://akb.se.jku.at/";

  /**
 * control login token
 */
app.run(function ($rootScope, $cookies, $location) {
    $rootScope.$on("$locationChangeStart", function (event, next, current) {
        if (!$rootScope.authenticated && $location.path() != "/login" ) {
            $location.path("/login");
            //$("#loginErrorModal").modal();
        }
    });
});
