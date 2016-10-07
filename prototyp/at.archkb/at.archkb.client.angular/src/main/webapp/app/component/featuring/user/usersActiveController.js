angular.module('archkb.featuring').controller('UsersActiveController', function($scope, UserService) {
	
	UserService.getActiveUsers().then(function(response) {
		$scope.users = response.data;
	});
	
});