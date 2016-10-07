angular.module('archkb.archprofile').controller('ArchProfileSummaryController', function ($scope, $rootScope, $state, archProfile, ArchProfileService, SecurityService) {
    $scope.archProfile = archProfile;

    var checkUserOwner = function () {
        $scope.isOwner = SecurityService.isPrincipalOwnerOfArchProfile(archProfile);
    };

    /**
     * Callback for published change success
     * @param response
     */
    var publisedChangeSuccessCallback = function (response) {
        $scope.archProfile.published = response.data.published;
    };

    /**
     * Callback for published change error
     * @param error
     */
    var publishedChangeErrorCallback = function (error) {
        // TODO: Error handling
        console.log(error);
    };

    $scope.publishArchProfile = function () {
        ArchProfileService.updateProperties(archProfile.id, 'published', true).then(publisedChangeSuccessCallback, publishedChangeErrorCallback);
    };

    $scope.unpublishArchProfile = function () {
        ArchProfileService.updateProperties(archProfile.id, 'published', false).then(publisedChangeSuccessCallback, publishedChangeErrorCallback);
    };

    $scope.deleteArchProfile = function () {
        ArchProfileService.deleteById(archProfile.id).then(function () {
            $state.go('master.base.user.archprofiles', {un: archProfile.ownerId});
        }, function (error) {
            // TODO better error handling
            console.log(error);
        });
    };


    checkUserOwner();
    $rootScope.$on('authenticationChanged', checkUserOwner);
});