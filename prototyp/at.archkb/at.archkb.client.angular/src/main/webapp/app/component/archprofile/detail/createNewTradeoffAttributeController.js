/**
 * Created by Rainer on 23.05.2016.
 */
angular.module('archkb.archprofile').controller('CreateNewTradeoffAttributeController', function($scope, $uibModalInstance, CoreDataService, tradeoffItem, isUnder) {

    $scope.attribute = {};

    $scope.attribute.name = isUnder ? tradeoffItem.nameUnder : tradeoffItem.nameOver;
    $scope.attribute.definition = isUnder ? tradeoffItem.definitionUnder : tradeoffItem.definitionOver;

    $scope.saveAttribute = function() {

        CoreDataService.createTradeoffItem($scope.attribute).then(function(response) {
            $uibModalInstance.close(response.data);
        }, function(error) {
            // TODO Better error handling
            console.log(error);
        });
    };
});