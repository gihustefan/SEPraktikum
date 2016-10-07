/**
 * Created by stefanhaselboeck on 11.08.16.
 */
angular.module('archkb.decisionguidance').controller('DecisionGuidanceModelDetailController', function ($rootScope, $scope, $state, $uibModal, decisionGuidanceModel, SecurityService, DecisionGuidanceModelService, CoreDataService) {

    var checkUserOwner = function () {

        // Cannot do anything if decisionGuidanceModel is undefined
        if(angular.isUndefined(decisionGuidanceModel)) {
            $state.go('master.base.decisionguidance.newest-ap');
            return;
        }
        // Prevent an unauthorized user from looking at an unpublished decisionGuidanceModel
        // If user wants to access an unpublished decisionGuidanceModel
        if(!decisionGuidanceModel.published) {
            // Redirect if user is not the owner and not and administrator
            // if the user is the owner -> no need for redirection
            // if the user is admin -> no need for redirection
            if(!SecurityService.isPrincipalOwnerOfDecisionGuidanceModel(decisionGuidanceModel) && !SecurityService.isPrincipalAdministrator()) {
                $state.go('master.base.decisionguidance.newest-ap');
            }
        }

        $scope.ui.editDisabled = !SecurityService.isPrincipalOwnerOfDecisionGuidanceModel(decisionGuidanceModel);
        if (!$scope.ui.editDisabled)
            addDummyData();
        else
            removeDummyData();
    };

    // TODO: general thoughts about error handling -> e.g. error messages from server and so on!
    // General successful callback
    var requestSuccessful = function () {
        $scope.ui.showLoadIndicator = false;
    };

    // General error callback
    var requestError = function () {
        $scope.ui.showLoadIndicator = false;
    };

    // Need to add Dummy Data (e.g. empty relation objects for editing)
    var addDummyData = function () {
        // If empty -> do not add dummy data!
        addEmptyObjectToRelation($scope.decisionGuidanceModel.potentialRequirements);
        addEmptyObjectToRelation($scope.decisionGuidanceModel.designOptions);
        addEmptyObjectToRelation($scope.decisionGuidanceModel.relatedGuidanceModels);
        addEmptyObjectToDesignOptionRelation($scope.decisionGuidanceModel.designOptions);
    };

    var removeDummyData = function () {
        // If empty -> do not add dummy data!
        removeEmptyObjectsFromRelation($scope.decisionGuidanceModel.potentialRequirements);
        removeEmptyObjectsFromRelation($scope.decisionGuidanceModel.designOptions);
        removeEmptyObjectsFromRelation($scope.decisionGuidanceModel.relatedGuidanceModels);
    };

    var addEmptyObjectToRelation = function (collection) {
        if (angular.isUndefined(collection))
            collection = [];

        if (collection.length <= 0) {
            collection.push({
                ordering: 0
            });
        }
    };

    var addEmptyObjectToDesignOptionRelation = function (collection) {
        angular.forEach(collection, function(value) {
            //Implications dummy data
            if (angular.isUndefined(value.implications)) {
                value.implications = [];
            }
            if (value.implications.length <= 0) {
                value.implications.push({
                    ordering: 0
                });
            }

            //AddressedRequirements dummy data
            if (angular.isUndefined(value.addressedRequirements)) {
                value.addressedRequirements = [];
            }
            if (value.addressedRequirements.length <= 0) {
                value.addressedRequirements.push({
                    ordering: 0
                });
            }

            //EffectedGuidanceModels dummy data
            if (angular.isUndefined(value.effectedGuidanceModels)) {
                value.effectedGuidanceModels = [];
            }
            if (value.effectedGuidanceModels.length <= 0) {
                value.effectedGuidanceModels.push({
                    ordering: 0
                });
            }

            //RequiredComponents dummy data
            if (angular.isUndefined(value.requiredComponents)) {
                value.requiredComponents = [];
            }
            if (value.requiredComponents.length <= 0) {
                value.requiredComponents.push({
                    ordering: 0
                });
            }
        });
    };

    var removeEmptyObjectsFromRelation = function (collection) {
        if (angular.isUndefined(collection)) {
            return;
        }

        var toRemove = [];
        angular.forEach(collection, function (value) {
            // Means not persisted at the moment
            if (angular.isUndefined(value.id)) {
                toRemove.push(value);
            }
        });

        var newValues = _.difference(collection, toRemove);

        // Clear the collection
        collection.splice(0, collection.length);

        // Add new values
        angular.forEach(newValues, function (value) {
            collection.push(value);
        });
    };

    // to know what Relation has been altered -> simply using the urls of the archProfile Service
    $scope.relationIds = DecisionGuidanceModelService.relationUrls;

    // to know what Relation has been altered -> simply using the urls of the archProfile Service
    $scope.designOptionRelationIds = DecisionGuidanceModelService.designOptionRelationIds;

    // Setting the Decision Guidance Model
    $scope.decisionGuidanceModel = decisionGuidanceModel;

    // UI Specific properties
    $scope.ui = {
        editDisabled: false,
        showLoadIndicator: false,
        uploadInProgress: false,
        addToCoreData: false,
        inputModelOptions: {
            updateOn: 'blur'
        },
        coreDataModelOptions: {
            debounce: 300
        }
    };

    /**
     * Determining if the other additional Infos about a relationship should be displayed (e.g. description, definition, ...)
     * @param relation The relation attribute under investigation
     * @returns {boolean} true if it should be displayed, else false
     */
    $scope.ui.showAdditionalRelationInfo = function (relation) {
        // Checking if relation has necessery information set to display additional information
        return angular.isDefined(relation.id) && (angular.isDefined(relation.idAttribute) || (angular.isDefined(relation.idAttributeOver && angular.isDefined(relation.idAttributeUnder))));
    };

    /**
     * Function to determine if the "Add to Core Data information" (e.g. Button) should be displayed for an specific DecisionGuidanceModel Attribute
     * e.g. if not existing in the DB
     * @param attribute which attribute
     */
    $scope.ui.showAddToCoreData = function (attribute) {

        // Do not show, if the attribute name is false!
        if (angular.isUndefined(attribute.name) || _.isEmpty(attribute.name))
            return false;

        // Check if the idAttribute not set -> means not persisted yet
        // and the attribut.noResults flag is set in the view -> e.g. type-head no results!
        return attribute.noResults;
    };

    // Functions visible to the view
    $scope.updateTitle = function () {
        $scope.ui.showLoadIndicator = true;
        DecisionGuidanceModelService.updateProperties(decisionGuidanceModel.id, 'title', decisionGuidanceModel.title).then(requestSuccessful, requestError);
    };
    $scope.updateDescription = function () {
        $scope.ui.showLoadIndicator = true;
        DecisionGuidanceModelService.updateProperties(decisionGuidanceModel.id, 'description', decisionGuidanceModel.description).then(requestSuccessful, requestError);
    };

    // Getting core data from a partial
    $scope.getCoreData = function (partial, relationId) {
        if (angular.isUndefined(partial) || angular.isUndefined(relationId)) {
            console.log('Tried to get CoreData with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;
        // Requesting the core Data from the server
        return CoreDataService.getCoreData(partial, relationId).then(function (response) {

            $scope.ui.showLoadIndicator = false;
            return response.data;

        }, function () {
            $scope.ui.showLoadIndicator = false;
            // TODO: Error handling
            console.log('Error trying to request Core Data');
        });
    };

    /**
     * Creating new Core data / DecisionGuidanceModel Attribute
     * @param value The title for the new Core Data (e.g. DecisionGuidanceModel Attribute)
     */
    $scope.createNewAttribute = function (item, relationId) {

        var modalInstance = $uibModal.open({
            animation: false,
            templateUrl: 'views/createNewAttributeModalView.html',
            controller: 'CreateNewAttributeModalController',
            size: 'sm',
            resolve: {
                attribute: function () {
                    return item;
                }
            }
        });

        modalInstance.result.then(function (newAttribute) {
            // When closing the ModalInstance -> directly create a new Relation!
            // Server must create a new Attribute if the relation references a new Attribute that does not exist at the moment!

            // Setting the noResults Flag -> otherwise the "Define" Button will be showed all the time.
            newAttribute.idAttribute = null; // Needs to be done - because this new attribute will be created during "addRelation"
            $scope.addRelation(newAttribute, relationId, item);
        });
    };

    // Adding relations to an item
    $scope.addRelation = function (item, relationId, relation) {
        if (angular.isUndefined(relationId)) {
            console.log('Tried to add new relation with undefined values');
        }

        $scope.ui.showLoadIndicator = true;


        // TODO: Currently must be done like this -> problem with uib-typeahead (model / typeahead problem).
        // Must assign to our relation (actual relation attribute)
        relation.idAttribute = item.idAttribute;
        relation.definition = item.definition;

        DecisionGuidanceModelService.addRelation(decisionGuidanceModel.id, relation, relationId).then(function (response) {

            _.mapObject(relation, function (value, key) {
                return response.data[key];
            });

            relation.noResults = false;
            relation.id = response.data.id;
            relation.idAttribute = response.data.idAttribute;
            $scope.ui.showLoadIndicator = false;

        }, function (error) {
            // TODO: Better error handling
            console.log(error.message);
            $scope.ui.showLoadIndicator = false;
        });
    };

    // Updating Ordering of DecisionGuidanceModel Relations
    $scope.updateRelationOrdering = function (item, direction, relationId) {
        if (angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(item.ordering) || angular.isUndefined(relationId)) {
            console.log('Tried to update Relation with undefined values');
            return;
        }
        // Not using general successful / error callback (handling should be somewhere else)
        return DecisionGuidanceModelService.updateRelationProperties(decisionGuidanceModel.id, item.id, relationId, 'ordering', item.ordering + direction);
    };

    // Updating the Definition of DecisionGuidanceModel relations
    $scope.updateRelationDefinition = function (item, relationId) {
        if (angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(relationId)) {
            console.log('Tried to update Definition with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;
        return DecisionGuidanceModelService.updateRelationProperties(decisionGuidanceModel.id, item.id, relationId, 'definition', item.definition).then(requestSuccessful, requestError);
    };


    // Updating Descriptions of ArchProfile Relations
    $scope.updateRelationDescription = function (item, relationId) {

        if (angular.isUndefined(item.id) || angular.isUndefined(item.description)) {
            console.log('Tried to update Relation with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;
        DecisionGuidanceModelService.updateRelationProperties(decisionGuidanceModel.id, item.id, relationId, 'description', item.description).then(requestSuccessful, requestError);
    };

    // Deleting DecisionGuidanceModel Relation
    $scope.deleteRelation = function (item, relationId) {
        if (angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(relationId)) {
            console.log('Tried to delete Relation with undefined values');
            return;
        }
        return DecisionGuidanceModelService.deleteRelation(decisionGuidanceModel.id, item.id, relationId).then(requestSuccessful, requestError);
    };


    /**
     * Creating new Core data / DesignOption Attribute
     * @param value The title for the new Core Data (e.g. DesignOption Attribute)
     */
    $scope.createNewDesignOptionAttribute = function (designOption, item, relationId) {

        var modalInstance = $uibModal.open({
            animation: false,
            templateUrl: 'views/createNewAttributeModalView.html',
            controller: 'CreateNewAttributeModalController',
            size: 'sm',
            resolve: {
                attribute: function () {
                    return item;
                }
            }
        });

        modalInstance.result.then(function (newAttribute) {
            // When closing the ModalInstance -> directly create a new Relation!
            // Server must create a new Attribute if the relation references a new Attribute that does not exist at the moment!

            // Setting the noResults Flag -> otherwise the "Define" Button will be showed all the time.
            newAttribute.idAttribute = null; // Needs to be done - because this new attribute will be created during "addRelation"
            $scope.addDesignOptionRelation(designOption, newAttribute, relationId, item);
        });
    };

    // Adding relations to a design option
    $scope.addDesignOptionRelation = function (designOption, item, relationId, relation) {
        if (angular.isUndefined(relationId)) {
            console.log('Tried to add new relation with undefined values');
        }

        $scope.ui.showLoadIndicator = true;


        // TODO: Currently must be done like this -> problem with uib-typeahead (model / typeahead problem).
        // Must assign to our relation (actual relation attribute)
        relation.idAttribute = item.idAttribute;
        relation.definition = item.definition;

        DecisionGuidanceModelService.addDesignOptionRelation(designOption.idAttribute, relation, relationId).then(function (response) {
            _.mapObject(relation, function (value, key) {
                return response.data[key];
            });

            relation.noResults = false;
            relation.id = response.data.id;
            relation.idAttribute = response.data.idAttribute;
            $scope.ui.showLoadIndicator = false;

        }, function (error) {
            // TODO: Better error handling
            console.log(error.message);
            $scope.ui.showLoadIndicator = false;
        });
    };

    // Updating Ordering of DecisionGuidanceModel Relations
    $scope.updateDesignOptionRelationOrdering = function (designOptionId, item, direction, relationId) {
        if (angular.isUndefined(designOptionId) || angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(item.ordering) || angular.isUndefined(relationId)) {
            console.log('Tried to update Relation with undefined values');
            return;
        }
        // Not using general successful / error callback (handling should be somewhere else)
        return DecisionGuidanceModelService.updateDesignOptionRelationProperties(designOptionId, item.id, relationId, 'ordering', item.ordering + direction);
    };

    // Updating the Definition of DecisionGuidanceModel relations
    $scope.updateDesignOptionRelationDefinition = function (designOptionId, item, relationId) {
        if (angular.isUndefined(designOptionId) || angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(relationId)) {
            console.log('Tried to update Definition with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;
        return DecisionGuidanceModelService.updateDesignOptionRelationProperties(designOptionId, item.id, relationId, 'definition', item.definition).then(requestSuccessful, requestError);
    };


    // Updating Descriptions of ArchProfile Relations
    $scope.updateDesignOptionRelationDescription = function (designOptionId, item, relationId) {

        if (angular.isUndefined(designOptionId) || angular.isUndefined(item.id) || angular.isUndefined(item.description)) {
            console.log('Tried to update Relation with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;
        DecisionGuidanceModelService.updateDesignOptionRelationProperties(designOptionId, item.id, relationId, 'description', item.description).then(requestSuccessful, requestError);
    };

    // Deleting DecisionGuidanceModel Relation
    $scope.deleteDesignOptionRelation = function (designOptionId, item, relationId) {
        if (angular.isUndefined(designOptionId) || angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(relationId)) {
            console.log('Tried to delete Relation with undefined values');
            return;
        }
        return DecisionGuidanceModelService.deleteDesignOptionRelation(designOptionId, item.id, relationId).then(requestSuccessful, requestError);
    };

    checkUserOwner();

    // Only applicable if user can edit
    if (!$scope.ui.editDisabled) {
        addDummyData();
    }
});