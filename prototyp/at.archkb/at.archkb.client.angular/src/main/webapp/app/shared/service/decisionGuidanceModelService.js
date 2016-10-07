/**
 * Created by stefanhaselboeck on 11.08.16.
 */
angular.module('archkb.service').factory('DecisionGuidanceModelService', function($http, URL, Upload, MAX_UPLOAD_SIZE) {

    var service = {};

    var baseUrl = URL.api + "/" + URL.decisionGuidanceModels.base;

    var baseUrlDesignOption = URL.api + "/" + URL.designOptions.base;

    // Endpoints for DGM Relations
    service.relationUrls = URL.decisionGuidanceModels.relations;

    // Endpoints for DO Relations
    service.designOptionRelationIds = URL.designOptions.relations;

    service.createNewEmptyDGM = function() {
        // Creating a new DecisionGuidanceModel
        return $http.post(baseUrl);
    };

    // Get single DecisionGuidanceModel by id
    service.getDGMById = function(id) {
        return $http.get(baseUrl + '/' + id);
    };

    // Getting all the DecisionGuidanceModels
    service.getNewestDecisionGuidanceModels = function(page, size) {

        var config = {
            params: {
                page: page,
                size: size
            }
        };

        return $http.get(baseUrl, config);
    };

    // Update only one Property of the DecisionGuidanceModel
    service.updateProperties = function(idDecisionGuidanceModel, propertyName, newPropertyValue) {

        var data = {};
        data[propertyName] = newPropertyValue;

        return $http.patch(baseUrl + '/' + idDecisionGuidanceModel, data);
    };

    // Delete a single DecisionGuidanceModel with ID
    service.deleteById = function (idDecisionGuidanceModel) {
        var config = {
            method: 'DELETE',
            url: baseUrl + '/' + idDecisionGuidanceModel
        };

        return $http(config);
    };

    // Adding a new, empty relation to a given DecisionGuidanceModel
    service.addRelation = function(idDecisionGuidanceModel, toAttribute, endpoint) {
        var request = {
            method: 'POST',
            url: baseUrl + '/' + idDecisionGuidanceModel + '/' + endpoint,
            data: toAttribute
        };
        return $http(request);
    };

    // Update the Properties of any DecisionGuidanceModel Relation (e.g. Qualityattribute, ...)
    service.updateRelationProperties = function(idDecisionGuidanceModel, idRelation, endpoint, propertyName, newPropertyValue) {
        var data = {};
        data[propertyName] = newPropertyValue;
        return $http.patch(baseUrl + '/' + idDecisionGuidanceModel + '/' + endpoint + '/' + idRelation, data);
    };

    // Deleting a DecisionGuidanceModel Relation (e.g. Qualityattributes)
    service.deleteRelation = function(idDecisionGuidanceModel, idRelation, endpoint) {
        var request = {
            method: 'DELETE',
            url: baseUrl + '/' + idDecisionGuidanceModel + '/' + endpoint + '/' + idRelation
        };
        return $http(request);
    };

    // Adding a new, empty relation to a given DesignOption
    service.addDesignOptionRelation = function (idDesignOption, toAttribute, endpoint) {
        var request = {
            method: 'POST',
            url: baseUrlDesignOption + '/' + idDesignOption + '/' + endpoint,
            data: toAttribute
        };
        return $http(request);
    };

    //update
    service.updateDesignOptionRelationProperties = function (idDesignOption, idRelation, endpoint, propertyName, newPropertyValue) {
        var data = {};
        data[propertyName] = newPropertyValue;
        return $http.patch(baseUrlDesignOption + '/' + idDesignOption + '/' + endpoint + '/' + idRelation, data);
    };

    //Deleting a DesignOption Relation (e.g. Qualityattributes)
    service.deleteDesignOptionRelation = function(idDesignOption, idRelation, endpoint) {
        var request = {
            method: 'DELETE',
            url: baseUrlDesignOption + '/' + idDesignOption + '/' + endpoint + '/' + idRelation
        };
        return $http(request);
    };

    return service;
});