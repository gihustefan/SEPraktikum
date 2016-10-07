angular.module('archkb.partial.eip').directive('archkbEipInput', function() {
	
	var directive = {};
	
	directive.restrict = 'A';
	
	directive.scope = {
			ngDisabled: '='
	};
	
	directive.link = function(scope, element, attributes) {
		
		var size = attributes.size === 'undefined' ? '' : attributes.size;
		
		var eipContainer = angular.element('<div class="archkb-eip-input-wrapper"></div>');
		eipContainer.addClass(scope.ngDisabled ? 'disabled' : '');
		eipContainer.append('<div class="archkb-eip-icon archkb-eip-input-icon-edit"><span class="glyphicon glyphicon-pencil"></span></div>');
		element.addClass('archkb-eip-input ' + size);
		element.wrap(eipContainer);
		
		scope.$watch('ngDisabled', function(disabled) {
			
			if(disabled)
				element.parent().addClass('disabled');
			else
				element.parent().removeClass('disabled');
		});
		
	};
	
	return directive;
});