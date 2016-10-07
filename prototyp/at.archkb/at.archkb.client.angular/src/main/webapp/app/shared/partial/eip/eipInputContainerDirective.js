angular.module('archkb.partial.eip').directive('archkbEipInputContainer', function() {
	
	var directive = {};
	
	directive.restrict = 'A';
	
	directive.scope = {
			ngDisabled: '=',
			collection: '=',
			item: '=',
			waitingForPromise: '=',
			showAdditional: '=',
			orderingUpPromise: '&',
			orderingDownPromise: '&',
			deletePromise: '&',
			definitionChangeCallback: '&',
			tradeoffDefinitionOverChangeCallback: '&',
			tradeoffDefinitionUnderChangeCallback: '&'
	};
	
	directive.transclude = true;
	
	directive.templateUrl = 'views/eipInputContainerView.html';
	
	directive.controller = 'EipInputContainerController';
	
	directive.link = function(scope, element, attributes) {
		element.addClass('archkb-eip-input-container');
		
		scope.$watch('ngDisabled', function(disabled) {
			
			if(disabled)
				element.addClass('disabled');
			else
				element.removeClass('disabled');
		});
	};
	
	return directive;
});