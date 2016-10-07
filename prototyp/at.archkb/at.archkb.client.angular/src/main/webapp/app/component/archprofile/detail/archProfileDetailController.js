angular.module('archkb.archprofile').controller('ArchProfileDetailController', function ($rootScope, $scope, $state, $uibModal, archProfile, SecurityService, ArchProfileService, CoreDataService) {

    var checkUserOwner = function () {

        // Cannot do anything if archProfile is undefined
        if(angular.isUndefined(archProfile)) {
            $state.go('master.base.startdash.newest-ap');
            return;
        }
        // Prevent an unauthorized user from looking at an unpublished ArchProfile
        // If user wants to access an unpublished archProfile
        if(!archProfile.published) {
            // Redirect if user is not the owner and not and administrator
            // if the user is the owner -> no need for redirection
            // if the user is admin -> no need for redirection
            if(!SecurityService.isPrincipalOwnerOfArchProfile(archProfile) && !SecurityService.isPrincipalAdministrator()) {
                $state.go('master.base.startdash.newest-ap');
            }
        }

        $scope.ui.editDisabled = !SecurityService.isPrincipalOwnerOfArchProfile(archProfile);
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
        addEmptyObjectToRelation($scope.archProfile.architecturestyles);
        addEmptyObjectToRelation($scope.archProfile.constraints);
        addEmptyObjectToRelation($scope.archProfile.designDecisions);
        addEmptyObjectToRelation($scope.archProfile.drivers);
        addEmptyObjectToRelation($scope.archProfile.qualityAttributes);
        addEmptyObjectToRelation($scope.archProfile.tradeoffs);
    };

    var removeDummyData = function () {
        // If empty -> do not add dummy data!
        removeEmptyObjectsFromRelation($scope.archProfile.architecturestyles);
        removeEmptyObjectsFromRelation($scope.archProfile.constraints);
        removeEmptyObjectsFromRelation($scope.archProfile.designDecisions);
        removeEmptyObjectsFromRelation($scope.archProfile.drivers);
        removeEmptyObjectsFromRelation($scope.archProfile.qualityAttributes);
        removeEmptyObjectsFromRelation($scope.archProfile.tradeoffs);
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

    $rootScope.$on('authenticationChanged', checkUserOwner);

    // to know what Relation has been altered -> simply using the urls of the archProfile Service
    $scope.relationIds = ArchProfileService.relationUrls;// to know what Relation has been altered -> simply using the urls of the archProfile Service
    $scope.relationIds = ArchProfileService.relationUrls;

    // Setting the Architecture Profile
    $scope.archProfile = archProfile;

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
     * Function to determine if the "Add to Core Data information" (e.g. Button) should be displayed for an specific ArchProfile Attribute
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

    $scope.ui.showAddToCoreDataTradeoffUnder = function (tradeoff) {

        // Do not show, if name under is empty
        if (angular.isUndefined(tradeoff.nameUnder) || _.isEmpty(tradeoff.nameUnder))
            return false;


        return tradeoff.noResultsUnder;
    };

    $scope.ui.showAddToCoreDataTradeoffOver = function (tradeoff) {

        // Do not show, if name over is empty
        if (angular.isUndefined(tradeoff.nameOver) || _.isEmpty(tradeoff.nameOver))
            return false;

        return tradeoff.noResultsOver;
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

    // Functions visible to the view
    $scope.updateTitle = function () {
        $scope.ui.showLoadIndicator = true;
        ArchProfileService.updateProperties(archProfile.id, 'title', archProfile.title).then(requestSuccessful, requestError);
    };
    $scope.updateDescription = function () {
        $scope.ui.showLoadIndicator = true;
        ArchProfileService.updateProperties(archProfile.id, 'description', archProfile.description).then(requestSuccessful, requestError);
    };

    // Updating Descriptions of ArchProfile Relations
    $scope.updateRelationDescription = function (item, relationId) {

        if (angular.isUndefined(item.id) || angular.isUndefined(item.description)) {
            console.log('Tried to update Relation with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;
        ArchProfileService.updateRelationProperties(archProfile.id, item.id, relationId, 'description', item.description).then(requestSuccessful, requestError);
    };

    // Updating Ordering of ArchProfile Relations
    $scope.updateRelationOrdering = function (item, direction, relationId) {
        if (angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(item.ordering) || angular.isUndefined(relationId)) {
            console.log('Tried to update Relation with undefined values');
            return;
        }
        // Not using general successful / error callback (handling should be somewhere else)
        return ArchProfileService.updateRelationProperties(archProfile.id, item.id, relationId, 'ordering', item.ordering + direction);
    };

    // Updating the Definition of ArchProfile relations
    $scope.updateRelationDefinition = function (item, relationId) {
        if (angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(relationId)) {
            console.log('Tried to update Definition with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;
        return ArchProfileService.updateRelationProperties(archProfile.id, item.id, relationId, 'definition', item.definition).then(requestSuccessful, requestError);
    };

    $scope.updateTradeoffRelationDefinitionOver = function (tradeoff) {
        if (angular.isUndefined(tradeoff) || angular.isUndefined(tradeoff.id)) {
            console.log('Tried to update Tradeoff Definition with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;

        return ArchProfileService.updateRelationProperties(archProfile.id, tradeoff.id, $scope.relationIds.tradeoffs, 'definitionOver', tradeoff.definitionOver).then(requestSuccessful, requestError);
    };

    $scope.updateTradeoffRelationDefinitionUnder = function (tradeoff) {
        if (angular.isUndefined(tradeoff) || angular.isUndefined(tradeoff.id)) {
            console.log('Tried to update Tradeoff Definition with undefined values');
            return;
        }

        $scope.ui.showLoadIndicator = true;

        return ArchProfileService.updateRelationProperties(archProfile.id, tradeoff.id, $scope.relationIds.tradeoffs, 'definitionUnder', tradeoff.definitionUnder).then(requestSuccessful, requestError);
    };

    // Deleting ArchProfile Relations
    $scope.deleteRelation = function (item, relationId) {
        if (angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(relationId)) {
            console.log('Tried to delete Relation with undefined values');
            return;
        }
        return ArchProfileService.deleteRelation(archProfile.id, item.id, relationId).then(requestSuccessful, requestError);
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

        ArchProfileService.addRelation(archProfile.id, relation, relationId).then(function (response) {

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

    /**
     * Adding a tradeoff relation
     * @param item -> the item from the typeahead
     * @param model -> the model value of the input
     * @param label -> Teh label value of the input
     * @param tradeoff -> the actual tradeoff object
     * @param isUnder -> true if tradeoff under was selected; false if tradeoff over was selected
     */
    $scope.addTradeoffRelation = function (item, tradeoff, isUnder) {

        if (isUnder) {
            tradeoff.idAttributeUnder = item.idAttribute;
            tradeoff.definitionUnder = item.definition;
            tradeoff.nameUnder = item.name;
            tradeoff.noResultsUnder = item.noResultsUnder;
        } else {
            tradeoff.idAttributeOver = item.idAttribute;
            tradeoff.definitionOver = item.definition;
            tradeoff.nameOver = item.name;
            tradeoff.noResultsOver = item.noResultsOver;
        }

        // Only try to create a new Tradeoff Relation if both Over and Under are valid!
        if (angular.isUndefined(tradeoff.idAttributeOver) || angular.isUndefined(tradeoff.idAttributeUnder)) {
            return;
        }

        $scope.ui.showLoadIndicator = true;

        ArchProfileService.addRelation(archProfile.id, tradeoff, $scope.relationIds.tradeoffs).then(function (response) {
            tradeoff.id = response.data.id;
            tradeoff.idAttributeOver = response.data.idAttributeOver;
            tradeoff.idAttributeUnder = response.data.idAttributeUnder;

            $scope.ui.showLoadIndicator = false;
        }, function (error) {
            // TODO error handling
            console.log(error);
            $scope.ui.showLoadIndicator = false;
        });
    };


    /**
     * Creating new Core data / ArchProfile Attribute
     * @param value The title for the new Core Data (e.g. ArchProfile Attribute)
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

    /**
     * Creating a new Tradeoff Attribute
     * @param item Which item
     */
    $scope.createNewTradeoffItem = function (tradeoffItem, isUnder) {

        var modelInstance = $uibModal.open({
            animation: false,
            templateUrl: 'views/createNewAttributeModalView.html',
            controller: 'CreateNewTradeoffAttributeController',
            size: 'sm',
            resolve: {
                tradeoffItem: function () {
                    return tradeoffItem;
                },
                isUnder: function () {
                    return isUnder;
                }
            }
        });

        modelInstance.result.then(function (newTradeoffItem) {
            // When closing the ModalInstance -> directly create a new Tradeoffitem
            // Server must create a new Attribute if the relation references a new Attribute that does not exist at the moment!

            // Setting the noResults Flags -> otherwise the "Define" Button will be showed all the time.
            if (isUnder) {
                newTradeoffItem.noResultsUnder = false;
            }
            else {
                newTradeoffItem.noResultsOver = false;
            }

            $scope.addTradeoffRelation(newTradeoffItem, tradeoffItem, isUnder);
        });

    };

    $scope.createDiagrams = function (images) {

        if (angular.isUndefined(images) || images.length <= 0) {
            console.log('Not uploading images - undefined values');
        }

        angular.forEach(images, function (image) {

            var createDiagramPromise = ArchProfileService.createArchProfileDiagram(archProfile.id, image);

            // For example if the file size exceeds the maximum upload limit!
            if(angular.isUndefined(createDiagramPromise))
                return;

            createDiagramPromise.then(function (response) {
                $scope.archProfile.diagrams.push(response.data);
                $scope.ui.uploadInProgress = false;
            }, function () {
                $scope.ui.uploadInProgress = false;
            }, function (event) {
                $scope.ui.uploadInProgress = true;
            });
        });
    };

    $scope.deleteDiagram = function(diagram) {
        if(angular.isUndefined(diagram) ||angular.isUndefined(diagram.id)) {
            console.log('Tried to delete Diagram with undefined values');
            return;
        }

        // Using the delete Relation Service function for deleting diagrams (same pattern)
        ArchProfileService.deleteRelation(archProfile.id, diagram.id, $scope.relationIds.diagrams).then(function() {
            // Remove the item from the collection on successful delete
            $scope.archProfile.diagrams.splice($scope.archProfile.diagrams.indexOf(diagram), 1);
        }, function(error) {
            console.log(error);
        });
    };

    $scope.getDiagramThumbnailStyle = function(diagram) {
        return {
          'background-image': 'url(' + diagram.path + ')'
        };
    };

    checkUserOwner();

    // Only applicable if user can edit
    if (!$scope.ui.editDisabled) {
        addDummyData();
    }
});