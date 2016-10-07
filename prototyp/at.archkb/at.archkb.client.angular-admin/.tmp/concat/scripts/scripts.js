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
  .config(["$routeProvider", function ($routeProvider) {
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
  }]);

//app.config(function($httpProvider) {
	//$httpProvider.defaults.headers.common.Accept = '*/*';
//	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
//});

var serverAddress = "http://localhost:8080/";
  
  /**
 * control login token
 */
app.run(["$rootScope", "$cookies", "$location", function ($rootScope, $cookies, $location) {
    $rootScope.$on("$locationChangeStart", function (event, next, current) {        
        if (!$rootScope.authenticated && $location.path() != "/login" ) {
            $location.path("/login");
            //$("#loginErrorModal").modal();
        }
    });
}]);

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('MainCtrl', function () {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('LoginCtrl', ["$rootScope", "$scope", "$location", "$cookies", "loginservice", function ($rootScope, $scope, $location, $cookies, loginservice) {

    $scope.username;
    $scope.password;

    $rootScope.execlogin = function () {
      loginservice.login($scope.username, $scope.password).then(function () {
        updateAuthStatus();
      }, function () {
        // TODO: Error handling on login
        $("#loginErrorModal").modal();
      });
    };

    $rootScope.execlogout = function () {
      loginservice.logout().then(function () {
        //updateAuthStatus();
      }, function () {
        // TODO: Error handling logout
      });
      updateAuthStatus();
    };

    var updateAuthStatus = function () {
      var authenticated = false;
      if (loginservice.principal && loginservice.authorities) {
        var i = 0;
        while (!false && i < loginservice.authorities.length) {
          if (loginservice.authorities[i].authority == "ROLE_ADMIN") {
            authenticated = true;
          }
          i++;
        }
      }
      $rootScope.authenticated = authenticated;
      $rootScope.principal = loginservice.principal;
      $rootScope.$broadcast('authenticationChanged');
      if ($rootScope.authenticated) {
        //$location.path("/createapattribute");
        $location.path("/#");
      } else {
        $location.path("/login");
        $("#loginErrorModal").modal();
      }
    };

    // Try to login right away - maybe still logged in (Cookie set)
    //$scope.username = 'admin@archkb.at';
    //$scope.password = 'password';
    //$rootScope.execlogin();
  }]);

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:AlluserCtrl
 * @description
 * # AlluserCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('AlluserCtrl', ["$scope", "$rootScope", "$location", "userservice", function ($scope, $rootScope, $location, userservice) {
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
  }]);

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:HeaderCtrl
 * @description
 * # HeaderCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('HeaderCtrl', ["$scope", "$rootScope", "$mdToast", function ($scope, $rootScope, $mdToast) {
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
  }]);

'use strict';

/**
 * @ngdoc service
 * @name architectureKbadminApp.loginservice
 * @description
 * # loginservice
 * Factory in the architectureKbadminApp.
 */
angular.module('architectureKbadminApp')
  .factory('loginservice', ["$http", "$q", function ($http, $q) {
    // Service logic
    // ...

    var service = {};

    service.login = function (username, password) {
      $http.defaults.headers.common['Authorization'] = null;

      var url = serverAddress+'api/authentications/user';
      var headers = username && password ? {
        'Authorization': "Basic " + btoa(username + ":" + password)
      } : {};


      // Promise (resolved on successful authentication, reject on unsuccessful authentication or error)
      var deferred = $q.defer();
      $http.get(url, { headers: headers }).success(function (user) {
        if (user.authenticated) {
          service.authenticated = true;
          service.principal = user.principal;
          service.authorities = user.authorities;
          $http.defaults.headers.common['Authorization'] = "Basic " + btoa(username + ":" + password);
          deferred.resolve();
        } else {
          clearAuth();
          deferred.reject();
        }
      }).error(function () {
        clearAuth();
        deferred.reject();
      });

      return deferred.promise;
    };

    service.logout = function () {
      $http.defaults.headers.common['Authorization'] = null;
      clearAuth();
      
      var url = serverAddress+'api/logout';

      var deferred = $q.defer();

      // Make a logout rest call to the server and set authenticated to false
      $http.post(url, {}).success(function () {
        
        deferred.resolve();
      }).error(function () {
        deferred.reject();
      });

      return deferred.promise;
    };


    service.isPrincipalOwnerOfArchProfile = function (archProfile) {
      return service.principal !== null && archProfile.ownerName === service.principal.username;
    };

    var clearAuth = function () {
      service.authenticated = false;
      service.principal = null;
      service.authorities = null;
    };

    clearAuth();

    return service;
  }]);

'use strict';

/**
 * @ngdoc service
 * @name architectureKbadminApp.userservice
 * @description
 * # userservice
 * Factory in the architectureKbadminApp.
 */
angular.module('architectureKbadminApp')
  .factory('userservice', ["$http", function ($http) {
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

  }]);

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:UserdetailCtrl
 * @description
 * # UserdetailCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('UserdetailCtrl', ["$scope", "$rootScope", "$routeParams", "userservice", function ($scope, $rootScope, $routeParams, userservice) {
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

  }]);

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:CreateuserCtrl
 * @description
 * # CreateuserCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('CreateuserCtrl', ["$scope", "$rootScope", "$location", "userservice", function ($scope, $rootScope, $location, userservice) {
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

  }]);

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:AllapattributeCtrl
 * @description
 * # AllapattributeCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('AllapattributeCtrl', ["$scope", "$rootScope", "$location", "apattributeservice", function ($scope, $rootScope, $location, apattributeservice) {

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

  }]);

'use strict';

/**
 * @ngdoc service
 * @name architectureKbadminApp.apattributeservice
 * @description
 * # apattributeservice
 * Factory in the architectureKbadminApp.
 */
angular.module('architectureKbadminApp')
  .factory('apattributeservice', ["$http", function ($http) {
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
  }]);

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:ApattributedetailCtrl
 * @description
 * # ApattributedetailCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('ApattributedetailCtrl', ["$scope", "$rootScope", "$routeParams", "$location", "apattributeservice", function ($scope, $rootScope, $routeParams, $location, apattributeservice) {

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

  }]);

'use strict';

/**
 * @ngdoc function
 * @name architectureKbadminApp.controller:CreateapattributeCtrl
 * @description
 * # CreateapattributeCtrl
 * Controller of the architectureKbadminApp
 */
angular.module('architectureKbadminApp')
  .controller('CreateapattributeCtrl', ["$scope", "$rootScope", "$location", "apattributeservice", function ($scope, $rootScope, $location, apattributeservice) {
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
    
  }]);

angular.module('architectureKbadminApp').run(['$templateCache', function($templateCache) {
  'use strict';

  $templateCache.put('views/allapattribute.html',
    "<div class=\"container\"> <h1 class=\"page-header\">Existing ArchProfile Attributes</h1> <ul class=\"nav nav-tabs\"> <li ng-repeat=\"type in allTypes\" ng-class=\"active(type)\"><a href=\"\" ng-click=\"select(type)\">{{type}}</a></li> </ul>  <table class=\"table table-hover\"> <thead> <tr> <th>ID</th> <th>Name</th> <th>Core Added</th> <th>View</th> </tr> </thead> <tbody> <tr ng-repeat=\"apat in apats\"> <td style=\"vertical-align:middle\">{{apat.id}}</td> <td style=\"vertical-align:middle\">{{apat.name}}</td> <td style=\"vertical-align:middle\"><div ng-show=\"apat.coreAdded\">{{apat.coreAdded | date:'mediumDate'}}</div></td> <td><a ng-click=\"navigate(apat.id)\" class=\"btn btn-warning\"><span class=\"glyphicon glyphicon-share\"></span> Edit</a></td> </tr> </tbody> </table> </div>"
  );


  $templateCache.put('views/alluser.html',
    "<div class=\"container\"> <h1 class=\"page-header\">Existing User</h1> <table class=\"table table-hover\"> <thead> <tr> <th>ID</th> <th>Username</th> <th>E-Mail</th> <th>Firstname</th> <th>Lastname</th> <th>View</th> </tr> </thead> <tbody> <tr ng-repeat=\"user in users\"> <td style=\"vertical-align:middle\">{{user.id}}</td> <td style=\"vertical-align:middle\">{{user.username}}</td> <td style=\"vertical-align:middle\">{{user.email}}</td> <td style=\"vertical-align:middle\">{{user.firstName}}</td> <td style=\"vertical-align:middle\">{{user.lastName}}</td> <td><a ng-click=\"navigate(user.id)\" class=\"btn btn-warning\"><span class=\"glyphicon glyphicon-share\"></span> Edit</a></td> </tr> </tbody> </table> </div>"
  );


  $templateCache.put('views/apattributedetail.html',
    "<div class=\"container\"> <h1 class=\"page-header\">Core Data</h1> <form class=\"form-horizontal\" role=\"form\"> <div class=\"panel panel-default\"> <div class=\"panel-heading\">General Information</div> <div class=\"panel-body\"> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"name\">Name:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"apat.name\" id=\"name\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"definition\">Definition:</label> <div class=\"col-sm-9\"> <textarea type=\"text\" class=\"form-control\" ng-model=\"apat.definition\" rows=\"4\" id=\"definition\" ng-readonly=\"!edit\"></textarea> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"type\">Type:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"apat.type\" id=\"type\" ng-readonly=\"true\"> </div> </div> <div class=\"form-group\" ng-show=\"apat.creatorEmail&&apat.creatorId\"> <label class=\"control-label col-sm-3\">Creator:</label> <div class=\"col-sm-9\"> <button ng-click=\"showCreator()\" type=\"button\" class=\"btn btn-default\">{{apat.creatorEmail}}</button> </div> </div> </div> </div> <div class=\"panel panel-default\"> <div class=\"panel-heading\">Activation</div> <div class=\"panel-body\"> <div class=\"form-inline\" ng-show=\"apat.creationDate>0\"> <label class=\"control-label col-sm-3\">Creationdate</label> <label class=\"control-label col-sm-9\"><p class=\"text-left\">{{apat.creationDate | date:'medium'}}</p></label> </div> <div class=\"form-inline\" ng-show=\"apat.coreAdded>0\"> <label class=\"control-label col-sm-3\">Core Added:</label> <label class=\"control-label col-sm-9\"><p class=\"text-left\">{{apat.coreAdded | date:'medium'}}</p></label> </div> <div class=\"form-inline\" ng-show=\"apat.lastModified>0\"> <label class=\"control-label col-sm-3\">Last Modified:</label> <label class=\"control-label col-sm-9\"><p class=\"text-left\">{{apat.lastModified | date:'medium'}}</p></label> </div> </div> </div> <div class=\"panel-body\"> <div class=\"form-group\"> <div class=\"form-inline pull-right\"> <a ng-click=\"edit=false\" class=\"btn btn-danger\" ng-show=\"edit\"><span class=\"glyphicon glyphicon-arrow-left\"></span> Back</a> <a ng-click=\"coreAdd()\" class=\"btn btn-success\" ng-show=\"edit&&(!apat.coreAdded)\"><span class=\"glyphicon glyphicon-floppy-saved\"></span> Add to Core</a> <a ng-click=\"coreRemove()\" class=\"btn btn-warning\" ng-show=\"edit&&apat.coreAdded\"><span class=\"glyphicon glyphicon-floppy-remove\"></span> Remove from Core</a> <a ng-click=\"update()\" class=\"btn btn-primary\" ng-show=\"edit\"><span class=\"glyphicon glyphicon-floppy-disk\"></span> Save</a> <a ng-click=\"edit=true\" class=\"btn btn-warning\" ng-show=\"!edit\"><span class=\"glyphicon glyphicon-edit\"></span> Edit</a> </div> </div> </div> </form> </div>"
  );


  $templateCache.put('views/createapattribute.html',
    "<div class=\"container\"> <h1 class=\"page-header\">Create Core Data</h1> <form class=\"form-horizontal\" role=\"form\" name=\"createapat\"> <div class=\"panel panel-default\"> <div class=\"panel-heading\">New Core Data</div> <div class=\"panel-body\"> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"name\">Name:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"apat.name\" id=\"name\" name=\"name\" placeholder=\"Enter Name\" required> <span class=\"label label-danger\" ng-show=\"createapat.name.$error.required\"> Required!</span> <span class=\"label label-danger\" ng-show=\"(!createapat.name.$error.required)&&apat.name.length<5\"> Minimum Size 5!</span> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"definition\">Definition:</label> <div class=\"col-sm-9\"> <textarea type=\"text\" class=\"form-control\" ng-model=\"apat.definition\" rows=\"4\" id=\"definition\" name=\"definition\" placeholder=\"Enter Definition\" required></textarea> <span class=\"label label-danger\" ng-show=\"createapat.definition.$error.required\"> Required!</span> <span class=\"label label-danger\" ng-show=\"(!createapat.definition.$error.required)&&apat.definition.length<5\"> Minimum Size 5!</span> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"type\">Type:</label> <div class=\"col-sm-9\"> <select class=\"form-control\" id=\"type\" ng-model=\"apat.type\"> <option value=\"\">---Please select---</option> <!-- not selected / blank option --> <option ng-repeat=\"type in allTypes\" value=\"{{type}}\">{{type}}</option> </select> </div> </div> <div class=\"form-group\"> <div class=\"col-sm-3\"></div> <div class=\"col-sm-9\"> <div class=\"checkbox\"> <label><input ng-model=\"isCore\" type=\"checkbox\" value=\"\">is Core Attribute</label> </div> </div> </div> </div> </div> <div class=\"panel-body\"> <div class=\"form-group\"> <div class=\"form-inline pull-right\"> <a ng-click=\"create()\" ng-disabled=\"buttonDisabled\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-floppy-disk\"></span> Create</a> </div> </div> </div> </form> </div>"
  );


  $templateCache.put('views/createuser.html',
    "<div class=\"container\"> <h1 class=\"page-header\">Create User</h1> <form class=\"form-horizontal\" role=\"form\" name=\"createuser\"> <div class=\"panel panel-default\"> <div class=\"panel-heading\">New User</div> <div class=\"panel-body\"> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"email\">Email: </label> <div class=\"col-sm-9\"> <input type=\"email\" class=\"form-control\" ng-model=\"email\" id=\"email\" name=\"email\" placeholder=\"Enter E-Mail\" required> <span class=\"label label-danger\" ng-show=\"createuser.email.$error.required\"> Required!</span> <span class=\"label label-danger\" ng-show=\"createuser.email.$error.email\"> Not valid email!</span> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"username\">Username:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"username\" id=\"username\" name=\"username\" placeholder=\"Enter Username\" required> <span class=\"label label-danger\" ng-show=\"createuser.username.$error.required\"> Required!</span> </div> </div> </div> </div> <div class=\"panel-body\"> <div class=\"form-group\"> <div class=\"form-inline pull-right\"> <a ng-click=\"create()\" ng-disabled=\"buttonDisabled\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-floppy-disk\"></span> Create</a> </div> </div> </div> </form> </div> <!-- Modal --> <div class=\"modal fade\" id=\"userPasswordModal\" role=\"dialog\"> <div class=\"modal-dialog modal-sm\"> <div class=\"modal-content\"> <div class=\"modal-header\"> <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button> <h4 class=\"modal-title\">User Password</h4> </div> <div class=\"modal-body\"> <p>The created Password for the User is:</p> <input class=\"form-control\" ng-readonly=\"true\" ng-model=\"password\"> </div> <div class=\"modal-footer\"> <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">OK</button> </div> </div> </div> </div>"
  );


  $templateCache.put('views/footer.html',
    "<p><span class=\"glyphicon glyphicon-heart\"></span> from the Yeoman team</p>"
  );


  $templateCache.put('views/header.html',
    "<div ng-controller=\"HeaderCtrl\"> <div class=\"navbar navbar-default\" role=\"navigation\"> <div class=\"container-fluid\"> <div class=\"navbar-header\"> <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#js-navbar-collapse\"> <span class=\"sr-only\">Toggle navigation</span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> </button> <a class=\"navbar-brand\" href=\"#/\">ArchKB Admin Page</a> </div> <div ng-show=\"showmenue()\"> <div class=\"collapse navbar-collapse\" id=\"navbararchkb\"> <ul class=\"nav navbar-nav\"> <li><a href=\"#/\">Dashboard</a></li> <li class=\"dropdown\"> <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">User Administration<span class=\"caret\"></span></a> <ul class=\"dropdown-menu\"> <li><a href=\"#/alluser\">Existing User</a></li> <li><a href=\"#/createuser\">Create New User</a></li> <li role=\"separator\" class=\"divider\"></li> <li><a href=\"#/\">Separated link</a></li> </ul> </li> <li class=\"dropdown\"> <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Core Data<span class=\"caret\"></span></a> <ul class=\"dropdown-menu\"> <li><a href=\"#/allapattribute\">Existing Core Data</a></li> <li><a href=\"#/createapattribute\">Create New Core Data</a></li> </ul> </li> </ul> <ul class=\"nav navbar-nav navbar-right\"> <li class=\"dropdown\"> <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\"><span class=\"glyphicon glyphicon-user\"></span> <span class=\"caret\"></span></a> <ul class=\"dropdown-menu\"> <li><a href=\"#/\">Profile</a></li> <li><a href=\"#/\">Settings</a></li> <li role=\"separator\" class=\"divider\"></li> <li><a href=\"#/\" ng-click=\"execlogout()\">Logout</a></li> </ul> </li> </ul> <form class=\"navbar-form navbar-right\" role=\"search\"> <div class=\"form-group\"> <input type=\"text\" class=\"form-control\" placeholder=\"Search\"> </div> <button type=\"submit\" class=\"btn btn-default\">Submit</button> </form> </div> </div> <!-- /.navbar-collapse --> </div> </div> </div>"
  );


  $templateCache.put('views/login.html',
    "<div class=\"row\"> <div class=\"col-md-4 col-md-offset-4\"> <div class=\"login-panel panel panel-default\"> <div class=\"panel-heading\"> <h3 class=\"panel-title\">Please Sign In</h3> </div> <div class=\"panel-body\"> <form role=\"form\"> <fieldset> <div class=\"form-group\"> <input class=\"form-control\" placeholder=\"E-mail\" name=\"email\" type=\"email\" autofocus ng-model=\"username\"> </div> <div class=\"form-group\"> <input class=\"form-control\" placeholder=\"Password\" name=\"password\" type=\"password\" value=\"\" ng-model=\"password\"> </div> <!-- Change this to a button or input when using this as a form --> <button ng-click=\"execlogin()\" class=\"btn btn-lg btn-success btn-block\">Login</button> </fieldset> </form> </div> </div> </div> </div> <!-- Modal --> <div class=\"modal fade\" id=\"loginErrorModal\" role=\"dialog\"> <div class=\"modal-dialog modal-sm\"> <div class=\"modal-content\"> <div class=\"modal-header\"> <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button> <h4 class=\"modal-title\">Login error</h4> </div> <div class=\"modal-body\"> <p>Please check your user data</p> </div> <div class=\"modal-footer\"> <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button> </div> </div> </div> </div>"
  );


  $templateCache.put('views/main.html',
    "<div class=\"container\"> <div class=\"jumbotron\"> <h1>'Allo, 'Allo!</h1> <p class=\"lead\"> <img src=\"images/yeoman.png\" alt=\"I'm Yeoman\"><br> Always a pleasure scaffolding your apps. </p> <p><a class=\"btn btn-lg btn-success\" ng-href=\"#/\">Splendid!<span class=\"glyphicon glyphicon-ok\"></span></a></p> </div> <div class=\"row marketing\"> <h4>HTML5 Boilerplate</h4> <p> HTML5 Boilerplate is a professional front-end template for building fast, robust, and adaptable web apps or sites. </p> <h4>Angular</h4> <p> AngularJS is a toolset for building the framework most suited to your application development. </p> <h4>Karma</h4> <p>Spectacular Test Runner for JavaScript.</p> </div> </div>"
  );


  $templateCache.put('views/userdetail.html',
    "<div class=\"container\"> <h1 class=\"page-header\">User Profile</h1> <form class=\"form-horizontal\" role=\"form\"> <div class=\"panel panel-default\"> <div class=\"panel-heading\">General Information</div> <div class=\"panel-body\"> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"username\">Username: </label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.originalUsername\" id=\"username\" ng-readonly=\"true\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"email\">E-Mail:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.email\" id=\"email\" ng-readonly=\"true\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"firstName\">First Name:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.firstName\" id=\"firstName\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"lastName\">Last Name:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.lastName\" id=\"lastName\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"about\">About:</label> <div class=\"col-sm-9\"> <textarea type=\"text\" class=\"form-control\" ng-model=\"user.about\" rows=\"4\" id=\"about\" ng-readonly=\"!edit\"></textarea> </div> </div> </div> </div> <div class=\"panel panel-default\"> <div class=\"panel-heading\">Address</div> <div class=\"panel-body\"> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"address\">Address:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.address\" id=\"address\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"city\">City:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.city.name\" id=\"city\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"zipcode\">ZIP Code:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.city.zipCode\" id=\"zipcode\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"country\">Country:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.city.country.name\" id=\"country\" ng-readonly=\"!edit\"> </div> </div> </div> </div> <div class=\"panel panel-default\"> <div class=\"panel-heading\">Company</div> <div class=\"panel-body\"> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"address\">Company:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.company.name\" id=\"address\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"city\">Address:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.company.address\" id=\"city\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"city\">City:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.company.city.name\" id=\"city\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"zipcode\">ZIP Code:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.company.city.zipCode\" id=\"zipcode\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"country\">Country:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.company.city.country.name\" id=\"country\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"zipcode\">Position:</label> <div class=\"col-sm-9\"> <input type=\"text\" class=\"form-control\" ng-model=\"user.position.name\" id=\"zipcode\" ng-readonly=\"!edit\"> </div> </div> <div class=\"form-group\"> <label class=\"control-label col-sm-3\" for=\"country\">Position Description:</label> <div class=\"col-sm-9\"> <textarea type=\"text\" class=\"form-control\" rows=\"3\" ng-model=\"user.position.description\" id=\"country\" ng-readonly=\"!edit\"></textarea> </div> </div> </div> </div> <div class=\"panel panel-default\"> <div class=\"panel-heading\">Activation</div> <div class=\"panel-body\"> <div class=\"form-inline\" ng-show=\"user.dateLocked>0\"> <label class=\"control-label col-sm-3\">Deactivated at:</label> <label class=\"control-label col-sm-9\"><p class=\"text-left\">{{user.dateLocked | date:'medium'}}</p></label> </div> <div class=\"form-inline\" ng-show=\"user.dateActivated>0\"> <label class=\"control-label col-sm-3\">Activated at:</label> <label class=\"control-label col-sm-9\"><p class=\"text-left\">{{user.dateActivated | date:'medium'}}</p></label> </div> </div> </div> <div class=\"panel-body\"> <div class=\"form-group\"> <div class=\"form-inline pull-right\"> <a ng-click=\"edit=false\" class=\"btn btn-danger\" ng-show=\"edit\"><span class=\"glyphicon glyphicon-arrow-left\"></span> Back</a> <a ng-click=\"resetpassword()\" class=\"btn btn-warning\" ng-show=\"edit\" ng-disabled=\"resetpwdisabled\"><span class=\"glyphicon glyphicon-floppy-remove\"></span> Reset Password</a> <a ng-click=\"activate()\" class=\"btn btn-success\" ng-show=\"edit&&user.dateLocked>user.dateActivated\"><span class=\"glyphicon glyphicon-floppy-disk\"></span> Activate</a> <a ng-click=\"deactivate()\" class=\"btn btn-warning\" ng-show=\"edit&&(!user.dateLocked||user.dateLocked<user.dateActivated)\"><span class=\"glyphicon glyphicon-floppy-remove\"></span> Deactivate</a> <a ng-click=\"update()\" class=\"btn btn-primary\" ng-show=\"edit\"><span class=\"glyphicon glyphicon-floppy-disk\"></span> Save</a> <a ng-click=\"edit=true\" class=\"btn btn-warning\" ng-show=\"!edit\"><span class=\"glyphicon glyphicon-edit\"></span> Edit</a> </div> </div> </div> </form> </div> <!-- Modal --> <div class=\"modal fade\" id=\"userPasswordModal\" role=\"dialog\"> <div class=\"modal-dialog modal-sm\"> <div class=\"modal-content\"> <div class=\"modal-header\"> <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button> <h4 class=\"modal-title\">User Password</h4> </div> <div class=\"modal-body\"> <p>The created Password for the User is:</p> <input class=\"form-control\" ng-readonly=\"true\" ng-model=\"password\"> </div> <div class=\"modal-footer\"> <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">OK</button> </div> </div> </div> </div>"
  );

}]);
