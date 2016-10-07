angular.module('archkb.archprofile').controller('ArchProfileListController', function ($scope, $rootScope, ArchProfileService, UserService, SecurityService, fetchStrategy, AP_FETCH_TYPE) {

    var callbackArchProfilesSuccess = function (response) {
        $scope.archProfiles = response.data.archProfiles;
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

    $scope.getArchProfiles = function () {

        $scope.ui.loading = true;

        var page = $scope.ui.currentPage - 1;
        var itemsPerPage = $scope.ui.itemsPerPage;

        // Fetching the correct ArchProfiles (according to the fetch strategy (resolved in the state) ).
        switch (fetchStrategy.type) {
            case AP_FETCH_TYPE.NEWEST:
            {
                return ArchProfileService.getNewestArchProfiles(page, itemsPerPage).then(callbackArchProfilesSuccess, receiveError);
            }
            case AP_FETCH_TYPE.FEATURED:
            {
                return ArchProfileService.getNewestArchProfiles(page, itemsPerPage).then(callbackArchProfilesSuccess, receiveError);
            }
            case AP_FETCH_TYPE.ACTIVE:
            {
                return ArchProfileService.getNewestArchProfiles(page, itemsPerPage).then(callbackArchProfilesSuccess, receiveError);
            }
            case AP_FETCH_TYPE.USER:
            {
                return UserService.getArchProfilesOfUser(page, itemsPerPage, fetchStrategy.params.idUser).then(callbackArchProfilesSuccess, receiveError);
            }
        }

    };

    $scope.checkIfOwn = function (archProfile) {
        return SecurityService.isPrincipalOwnerOfArchProfile(archProfile);
    };

    $scope.publishArchProfile = function (archProfile) {
        $scope.ui.loading = true;
        ArchProfileService.updateProperties(archProfile.id, 'published', true).then(function(response) {
            $scope.ui.loading = false;
            archProfile.published = response.data.published;
        }, receiveError);
    };

    $scope.unpublishArchProfile = function (archProfile) {
        $scope.ui.loading = true;
        ArchProfileService.updateProperties(archProfile.id, 'published', false).then(function(response) {
            $scope.ui.loading = false;
            archProfile.published = response.data.published;
        }, receiveError);
    };

    $scope.getArchProfiles();

    // When authentication changes -> fetch the ArchProfiles again!
    $rootScope.$on('authenticationChanged', $scope.getArchProfiles);
});