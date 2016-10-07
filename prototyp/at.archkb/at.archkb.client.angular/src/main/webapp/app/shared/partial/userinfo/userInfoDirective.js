angular.module('archkb.partial').directive('archkbUserInfo', function() {
	
	var directive = {};
	
	directive.restrict = 'E';
	
	directive.scope = {
			username: '='
	};
	
	directive.templateUrl = 'views/userInfoView.html';
	
	return directive;
});