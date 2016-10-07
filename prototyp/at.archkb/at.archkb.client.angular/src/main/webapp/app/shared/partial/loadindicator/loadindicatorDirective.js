angular.module('archkb.partial').directive('archkbLoadindicator', function() {
	
	var directive = {};
	
	directive.restict = 'E';
	
	directive.templateUrl = 'views/loadindicatorView.html';
	
	directive.scope = {
			show: '='
	};
	
	return directive;
	
});