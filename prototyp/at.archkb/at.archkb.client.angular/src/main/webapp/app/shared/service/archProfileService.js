angular.module('archkb.service').factory('ArchProfileService', function($http, URL, Upload, MAX_UPLOAD_SIZE) {
	
	var service = {};

	var baseUrl = URL.api + '/' + URL.archProfile.base;
	
	// Endpoints for AP Relaitons
	service.relationUrls = URL.archProfile.relations;
	
	// Getting all the ArchProfiles
	service.getNewestArchProfiles = function(page, size) {
		
		var config = {
			params: {
				page: page,
				size: size
			}
		};
		
		return $http.get(baseUrl, config);
	};
	
	// Get single ArchProfile by id
	service.getById = function(id) {
		return $http.get(baseUrl + '/' + id);
	};
	
	// Creating a new ArchProfile
	service.createNewEmpty = function() {
		return $http.post(baseUrl);
	};
	
	// Deleting a single ArchProfile
	service.deleteById = function(id) {
		
		var config = {
				method: 'DELETE',
				url: baseUrl + '/' + id
		};
		
		return $http(config);
	};
	
	// Update only one Property of the ArchProfile
	service.updateProperties = function(idArchProfile, propertyName, newPropertyValue) {
		
		var data = {};
		data[propertyName] = newPropertyValue;
		
		return $http.patch(baseUrl + '/' + idArchProfile, data);
	};
	
	// Update the Properties of any ArchProfile Relation (e.g. Qualityattribute, ...)
	service.updateRelationProperties = function(idArchProfile, idRelation, endpoint, propertyName, newPropertyValue) {
		var data = {};
		data[propertyName] = newPropertyValue;
		return $http.patch(baseUrl + '/' + idArchProfile + '/' + endpoint + '/' + idRelation, data);
	};
	
	// Deleting a ArchProfile Relation (e.g. Qualityattributes)
	service.deleteRelation = function(idArchProfile, idRelation, endpoint) {
		var request = {
				method: 'DELETE',
				url: baseUrl + '/' + idArchProfile + '/' + endpoint + '/' + idRelation
		};
		return $http(request);
	};

    // Adding a new, empty relation to a given ArchProfile
	service.addRelation = function(idArchProfile, toAttribute, endpoint) {
        var request = {
            method: 'POST',
            url: baseUrl + '/' + idArchProfile + '/' + endpoint,
            data: toAttribute
        };
        return $http(request);
    };

	/**
	 * Creating new Diagrams
	 * @param images Array of images to upload / creating a diagram
	 */
	service.createArchProfileDiagram = function (idArchProfile, image) {

		// If image size is bigger than the max upload size -> do not upload image
		if(image.size > MAX_UPLOAD_SIZE) {
			return;
		}

		var config = {
			url: baseUrl + '/' + idArchProfile + '/' + URL.archProfile.relations.diagrams,
			file: image
		};

		return Upload.upload(config);
	};
	
	return service;
});