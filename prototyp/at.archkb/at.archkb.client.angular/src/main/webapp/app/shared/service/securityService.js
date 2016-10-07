angular.module('archkb.service').factory('SecurityService', function($http, $q, URL) {
	
	var service = {};
	
	service.login = function(username, password) {
		
		var url = URL.api + '/' + URL.authentications.base + '/' + URL.authentications.methods.user;
		var headers = username && password ? {
			'Authorization' : "Basic " + btoa(username + ":" + password)
		} : {};
		
		
		// Promise (resolved on successful authentication, reject on unsuccessful authentication or error)
		var deferred = $q.defer();
		$http.get(url, {headers: headers}).success(function(user) {
			if (user.authenticated) {
				service.authenticated = true;
				service.principal = user.principal;
				service.authorities = user.authorities;
				deferred.resolve();
			} else {
				clearAuth();
				deferred.reject();
			}
		}).error(function() {
			clearAuth();
			deferred.reject();
		});
		
		return deferred.promise;
	};
	
	service.logout = function() {
		var url = '/' + URL.logout;
		
		var deferred = $q.defer();
		
		// Make a logout rest call to the server and set authenticated to false
		$http.post(url, {}).success(function() {
			clearAuth();
			deferred.resolve();
		}).error(function() {
			deferred.reject();
		});
		
		return deferred.promise;
	};
	
	
	service.isPrincipalOwnerOfArchProfile = function(archProfile) {
		return service.principal !== null && archProfile.ownerName === service.principal.originalUsername;
	};

	service.isPrincipalOwnerOfDecisionGuidanceModel = function(decisionGuidanceModel) {
		return service.principal !== null && decisionGuidanceModel.ownerName === service.principal.originalUsername;
	};

	service.isPrincipalAdministrator = function() {

        var isAdmin = false;
		angular.forEach(service.authorities, function(authorityWrapper) {
			if(authorityWrapper.authority === "ROLE_ADMIN") {
                isAdmin = true;
                return;
            }
		});
        return isAdmin;
	};
	
	var clearAuth = function() {
		service.authenticated = false;
		service.principal = null;
        service.authorities = [];
	};
	
	clearAuth();
	return service;
});