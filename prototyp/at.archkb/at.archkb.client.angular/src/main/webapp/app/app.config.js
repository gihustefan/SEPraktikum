angular.module('archkb').config(function($translateProvider, $httpProvider) {

	$httpProvider.defaults.headers.common.Accept = '*/*';
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

	// Setting up translations
	$translateProvider.useSanitizeValueStrategy('sanitize');
	
	$translateProvider.useStaticFilesLoader({
		prefix: 'translations/',
		suffix: '.json'
	});
	
	$translateProvider.preferredLanguage('en_GB');
});