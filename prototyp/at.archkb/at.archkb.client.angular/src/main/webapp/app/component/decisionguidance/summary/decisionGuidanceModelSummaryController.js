/**
 * Created by stefanhaselboeck on 16.08.16.
 */
angular.module('archkb.decisionguidance').controller('DecisionGuidanceModelSummaryController', function ($scope, $rootScope, $state, decisionGuidanceModel, DecisionGuidanceModelService, SecurityService) {
    $scope.decisionGuidanceModel = decisionGuidanceModel;

    var checkUserOwner = function () {
        $scope.isOwner = SecurityService.isPrincipalOwnerOfDecisionGuidanceModel(decisionGuidanceModel);
    };

    /**
     * Callback for published change success
     * @param response
     */
    var publisedChangeSuccessCallback = function (response) {
        $scope.decisionGuidanceModel.published = response.data.published;
    };

    /**
     * Callback for published change error
     * @param error
     */
    var publishedChangeErrorCallback = function (error) {
        // TODO: Error handling
        console.log(error);
    };

    $scope.publishDecisionGuidanceModel = function () {
        DecisionGuidanceModelService.updateProperties(decisionGuidanceModel.id, 'published', true).then(publisedChangeSuccessCallback, publishedChangeErrorCallback);
    };

    $scope.unpublishDecisionGuidanceModel = function () {
        DecisionGuidanceModelService.updateProperties(decisionGuidanceModel.id, 'published', false).then(publisedChangeSuccessCallback, publishedChangeErrorCallback);
    };

    $scope.deleteDecisionGuidanceModel = function () {
        DecisionGuidanceModelService.deleteById(decisionGuidanceModel.id).then(function () {
            $state.go('master.base.user.decisionguidancemodels', {un: decisionGuidanceModel.ownerId});
        }, function (error) {
            // TODO better error handling
            console.log(error);
        });
    };


    checkUserOwner();
    $rootScope.$on('authenticationChanged', checkUserOwner);
});