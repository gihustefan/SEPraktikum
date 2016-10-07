/**
 * Created by Rainer on 21.05.2016.
 */
angular.module('archkb.archprofile').controller('CreateNewAttributeModalController', function($scope, $uibModalInstance, attribute) {

    $scope.attribute = attribute;

    $scope.saveAttribute = function() {
        $uibModalInstance.close($scope.attribute);
    };

});
