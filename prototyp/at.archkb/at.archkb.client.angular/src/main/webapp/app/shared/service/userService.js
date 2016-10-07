angular.module('archkb.service').factory('UserService', function($http, URL) {
	
	var service = {};
	
	var baseUrl = URL.api + '/' + URL.users.base;
	
	// Getting all active User
	// TODO:  -> currently only fetching all users!
	service.getActiveUsers = function() {
		return $http.get(baseUrl);
	};


	service.getByUsername = function(username) {
		return $http.get(baseUrl + '/' + username);
	};

	/**
	 * Getting the ArchProfiles of the user
	 * @param page What page
	 * @param size How many items per page
	 * @param idUser For which user?
	 * @returns {HttpPromise}
     */
	service.getArchProfilesOfUser = function(page, size, idUser) {
		var config = {
			params: {
				page: page,
				size: size
			}
		};

		return $http.get(baseUrl + '/' + idUser + '/' + URL.users.relations.archprofiles, config);
	};

	/**
	 * Getting the ArchProfiles of the user
	 * @param page What page
	 * @param size How many items per page
	 * @param idUser For which user?
	 * @returns {HttpPromise}
	 */
	service.getDecisionGuidanceModelsOfUser = function(page, size, idUser) {
		var config = {
			params: {
				page: page,
				size: size
			}
		};

		return $http.get(baseUrl + '/' + idUser + '/' + URL.users.relations.decisionguidancemodels, config);
	};
	
	return service;
});