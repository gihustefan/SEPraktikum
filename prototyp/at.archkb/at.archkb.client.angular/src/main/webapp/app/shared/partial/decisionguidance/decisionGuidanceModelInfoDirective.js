angular.module('archkb.partial').directive('archkbDecisionguidancemodelInfo', function() {

	var directive = {};

	directive.restrict = 'E';

	directive.templateUrl = 'views/decisionGuidanceModelInfoView.html';

	directive.scope =  {
		decisionGuidanceModel: '=decisionguidancemodel',
		isOwn: '=',
		publishArchProfile: '&publishArchprofile',
		unpublishArchProfile: '&unpublishArchprofile'
	};

	return directive;
});