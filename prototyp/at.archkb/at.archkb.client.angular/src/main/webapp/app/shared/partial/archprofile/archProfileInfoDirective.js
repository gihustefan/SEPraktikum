angular.module('archkb.partial').directive('archkbArchprofileInfo', function() {
	
	var directive = {};
	
	directive.restrict = 'E';
	
	directive.templateUrl = 'views/archProfileInfoView.html';
	
	directive.scope =  {
		archProfile: '=archprofile',
		isOwn: '=',
        publishArchProfile: '&publishArchprofile',
        unpublishArchProfile: '&unpublishArchprofile'
	};
	
	return directive;
});