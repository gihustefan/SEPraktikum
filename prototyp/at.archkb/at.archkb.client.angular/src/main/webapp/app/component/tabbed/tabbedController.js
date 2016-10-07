angular.module('archkb.tabbed').controller('TabbedController', function($scope, $state, $rootScope, tabs) {
	$scope.tabs = tabs;
	
	// Must switch to the correct tab when entering this view.
	$rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
		setActiveTab(toState);
	});
	
	var setActiveTab = function(state) {

		angular.forEach(tabs, function(tab, key) {
			if(tab.state === state.name) {
				$scope.activeTab = key;
			}
		});
	};
	
	
	setActiveTab($state.current);
});