/**
 * Created by Rainer on 09.06.2016.
 */
angular.module('archkb.error').controller('ErrorController', function($scope, $stateParams) {

    $scope.title = $stateParams.status;
    $scope.message = $stateParams.statusText;

});