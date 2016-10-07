/**
 * Created by stefanhaselboeck on 11.08.16.
 */
angular.module('archkb.decisionguidance').controller('DecisionGuidanceListController', function ($scope, $rootScope, DecisionGuidanceModelService, UserService, SecurityService, fetchStrategy, AP_FETCH_TYPE) {

    var callbackDecisionGuidanceModelsSuccess = function (response) {
        $scope.decisionGuidanceModels = response.data.decisionGuidanceModels;
        $scope.ui.totalItems = response.data.countTotal;
        $scope.ui.loading = false;
    };

    var receiveError = function (error) {
        $scope.ui.loading = false;
        console.log(error);
    };

    $scope.ui = {
        currentPage: 1,
        itemsPerPage: 5,
        totalItems: 0,
        maxPageSize: 10,
        loading: false
    };

    $scope.getDecisionGuidanceModels = function () {

        $scope.ui.loading = true;

        var page = $scope.ui.currentPage - 1;
        var itemsPerPage = $scope.ui.itemsPerPage;

        // Fetching the correct DecisionGuidanceModels (according to the fetch strategy (resolved in the state) ).
        switch (fetchStrategy.type) {
            case AP_FETCH_TYPE.NEWEST:
            {
                return DecisionGuidanceModelService.getNewestDecisionGuidanceModels(page, itemsPerPage).then(callbackDecisionGuidanceModelsSuccess, receiveError);
            }
            case AP_FETCH_TYPE.FEATURED:
            {
                return DecisionGuidanceModelService.getNewestDecisionGuidanceModels(page, itemsPerPage).then(callbackDecisionGuidanceModelsSuccess, receiveError);
            }
            case AP_FETCH_TYPE.ACTIVE:
            {
                return DecisionGuidanceModelService.getNewestDecisionGuidanceModels(page, itemsPerPage).then(callbackDecisionGuidanceModelsSuccess, receiveError);
            }
            case AP_FETCH_TYPE.USER:
            {
                return UserService.getDecisionGuidanceModelsOfUser(page, itemsPerPage, fetchStrategy.params.idUser).then(callbackDecisionGuidanceModelsSuccess, receiveError);
            }
        }

    };

    $scope.checkIfOwn = function (decisionGuidanceModel) {
        return SecurityService.isPrincipalOwnerOfDecisionGuidanceModel(decisionGuidanceModel);
    };

    $scope.publishDecisionGuidanceModel = function (decisionGuidanceModel) {
        $scope.ui.loading = true;
        DecisionGuidanceModelService.updateProperties(decisionGuidanceModel.id, 'published', true).then(function (response) {
            $scope.ui.loading = false;
            decisionGuidanceModel.published = response.data.published;
        }, receiveError);
    };

    $scope.unpublishDecisionGuidanceModel = function (decisionGuidanceModel) {
        $scope.ui.loading = true;
        DecisionGuidanceModelService.updateProperties(decisionGuidanceModel.id, 'published', false).then(function (response) {
            $scope.ui.loading = false;
            decisionGuidanceModel.published = response.data.published;
        }, receiveError);
    };

    $scope.getDecisionGuidanceModels();

    // When authentication changes -> fetch the ArchProfiles again!
    $rootScope.$on('authenticationChanged', $scope.getDecisionGuidanceModels);
});