angular.module('archkb').config(function($stateProvider, $urlRouterProvider) {

	// we dont want hash in url
	// TODO: should enable this (more beautiful urls), but issues with server side -> must redirect to all
	//$locationProvider.html5Mode(true);

	$urlRouterProvider.otherwise('/');

	$stateProvider
		.state('master', {
			abstract: true,
			templateUrl: 'views/masterPageView.html',
			controller: 'MasterPageController',
			resolve: {
				authentication: function($rootScope, SecurityService) {

					var updateAuthStatus = function() {
						$rootScope.authenticated = SecurityService.authenticated;
						$rootScope.principal = SecurityService.principal;
						$rootScope.$broadcast('authenticationChanged');
					};

					return SecurityService.login().then(updateAuthStatus, updateAuthStatus);
				}
			}
		})
		.state('master.base', {
			abstract: true,
			views: {
				'header@master': {
					templateUrl: 'views/navbarView.html',
					controller: 'NavbarController'
				},
				'below-header@master': {
					templateUrl: 'views/mainMenuView.html',
					controller: 'MainMenuController'
				},
				'footer@master': {
					templateUrl: 'views/footerView.html'
				}
			}
		})
		.state('master.base.error', {
			views: {
				'left-top@master': {
					templateUrl: 'views/errorView.html',
					controller: 'ErrorController'
				}
			},
			params: {
				status: '200',
				statusText: 'Everything fine'
			}
		})
		.state('master.base.startdash', {
			abstract: true,
			views: {
				'title@master.base.startdash': {
					template: '<h3>{{"DECISION_DOCUMENTATION_MODELS" | translate}}</h3>'
				},
				'left-top@master': {
					templateUrl: 'views/tabbedView.html',
					controller: 'TabbedController'
				},
				'right-top@master': {
					templateUrl: 'views/usersActiveView.html',
					controller: 'UsersActiveController'
				}
			},
			resolve: {
				tabs: function($translate) {
					// First have to define
					return $translate(['NEWEST', 'FEATURED', 'ACTIVE']).then(function(translations) {
						return [
							{
								name: translations.NEWEST,
								state: 'master.base.startdash.newest-ap',
								viewName: 'newest-tab'
							},
							{
								name: translations.FEATURED,
								state: 'master.base.startdash.featured-ap',
								viewName: 'featured-tab'
							},
							{
								name: translations.ACTIVE,
								state: 'master.base.startdash.active-ap',
								viewName: 'active-tab'
							}
						];
					});
				}
			}

		})
		.state('master.base.startdash.newest-ap', {
			url: '/',
			views: {
				'newest-tab@master.base.startdash': {
					templateUrl: 'views/archProfileListView.html',
					controller: 'ArchProfileListController'
				}
			},
			resolve: {
				fetchStrategy: function(AP_FETCH_TYPE) {
					return {
						type: AP_FETCH_TYPE.NEWEST
					};
				}
			}
		})
		.state('master.base.startdash.featured-ap', {
			url: '/featured',
			views: {
				'featured-tab@master.base.startdash': {
					templateUrl: 'views/archProfileListView.html',
					controller: 'ArchProfileListController'
				}
			},
			resolve: {
				fetchStrategy: function(AP_FETCH_TYPE) {
					return {
						type: AP_FETCH_TYPE.FEATURED
					};
				}
			}
		})
		.state('master.base.startdash.active-ap', {
			url: '/active',
			views: {
				'active-tab@master.base.startdash': {
					templateUrl: 'views/archProfileListView.html',
					controller: 'ArchProfileListController'
				}
			},
			resolve: {
				fetchStrategy: function(AP_FETCH_TYPE) {
					return {
						type: AP_FETCH_TYPE.ACTIVE
					};
				}
			}
		})
		.state('master.base.archprofile', {
			url: '/archprofiles/{id:int}',

			views: {
				'left-top@master': {
					templateUrl: 'views/archProfileDetailView.html',
					controller: 'ArchProfileDetailController'
				},
				'right-top@master': {
					templateUrl: 'views/archProfileSummaryView.html',
					controller: 'ArchProfileSummaryController'
				}
			},
			// ArchProfile must be resolved before change to state -> will be injected into controller
			// Also available for the child states
			resolve: {
				archProfile: function(ArchProfileService, $state, $stateParams, $rootScope) {
					$rootScope.global.showLoad = true;

					return ArchProfileService.getById($stateParams.id).then(function(response) {
						$rootScope.global.showLoad = false;
						return response.data;
					}, function(error) {
						$rootScope.global.showLoad = false;
						$state.go('master.base.error', {status: error.status, statusText: error.statusText});
					});
				}
			}
		})
		.state('master.base.archprofile_new', {
			url: '/archprofile/new',
			resolve: {
				archProfile: function(ArchProfileService, $state) {
					// Creating a new, empty ArchProfile -> afterwards the information will be added
					// makes it easier to build the model on client side
					return ArchProfileService.createNewEmpty().then(function(response) {
						// Redirect to the archProfile Detail view right away, after creating a new, empty ArchProfile
						$state.go('master.base.archprofile', {id: response.data.id });
					});
				}
			}
		})
		.state('master.base.user', {
			abstract: true,
			url: '/users/{un}',
			views: {
				'title@master.base.user': {
					template: '<h3>{{"PROFILE" | translate}}</h3>'
				},
				'left-top@master': {
					templateUrl: 'views/tabbedView.html',
					controller: 'TabbedController'
				}
			},
			resolve: {
				tabs: function($translate) {
					// First have to define
					return $translate(['PROFILE', 'DECISION_GUIDANCE_MODELS', 'DECISION_DOCUMENTATION_MODELS']).then(function(translations) {
						return [
							{
								name: translations.PROFILE,
								state: 'master.base.user.profile',
								viewName: 'info-tab'
							},
							{
								name: translations.DECISION_GUIDANCE_MODELS,
								state: 'master.base.user.decisionguidancemodels',
								viewName: 'dgm-tab'
							},
							{
								name: translations.DECISION_DOCUMENTATION_MODELS,
								state: 'master.base.user.archprofiles',
								viewName: 'ap-tab'
							}
						];
					});
				},
				// First need to resolve the user before switching to userprofile view
				user: function(UserService, $stateParams, $rootScope) {
					$rootScope.global.showLoad = true;
					return UserService.getByUsername($stateParams.un).then(function(response) {
						$rootScope.global.showLoad = false;
						return response.data;
					}, function() {
						$rootScope.global.showLoad = true;
					});
				}
			}
		})
		.state('master.base.user.profile', {
			url: '',
			views: {
				'info-tab@master.base.user': {
					templateUrl: 'views/userProfileView.html',
					controller: 'UserProfileController'
				}
			}
		})
		.state('master.base.user.archprofiles', {
			url: '/archprofiles',
			views: {
				'ap-tab@master.base.user': {
					templateUrl: 'views/archProfileListView.html',
					controller: 'ArchProfileListController'
				}
			},
			resolve: {
				fetchStrategy: function(AP_FETCH_TYPE, user) {
					return {
						type: AP_FETCH_TYPE.USER,
						params: {
							idUser: user.id
						}
					};
				}
			}
		})
		.state('master.base.decisionguidance', {
			abstract: true,
			views: {
				'title@master.base.decisionguidance': {
					template: '<h3>{{"DECISION_GUIDANCE_MODELS" | translate}}</h3>'
				},
				'left-top@master': {
					templateUrl: 'views/tabbedView.html',
					controller: 'TabbedController'
				},
				'right-top@master': {
					templateUrl: 'views/usersActiveView.html',
					controller: 'UsersActiveController'
				}
			},
			resolve: {
				tabs: function($translate) {
					// First have to define
					return $translate(['NEWEST', 'FEATURED', 'ACTIVE']).then(function(translations) {
						return [
							{
								name: translations.NEWEST,
								state: 'master.base.decisionguidance.newest-dgm',
								viewName: 'newest-tab'
							},
							{
								name: translations.FEATURED,
								state: 'master.base.decisionguidance.featured-dgm',
								viewName: 'featured-tab'
							},
							{
								name: translations.ACTIVE,
								state: 'master.base.decisionguidance.active-dgm',
								viewName: 'active-tab'
							}
						];
					});
				}
			}
		})
		.state('master.base.decisionguidance.newest-dgm', {
			url: '/',
			views: {
				'newest-tab@master.base.decisionguidance': {
					templateUrl: 'views/decisionGuidanceListView.html',
					controller: 'DecisionGuidanceListController'
				}
			},
			resolve: {
				fetchStrategy: function(AP_FETCH_TYPE) {
					return {
						type: AP_FETCH_TYPE.NEWEST
					};
				}
			}
		})
		.state('master.base.decisionguidance.featured-dgm', {
			url: '/',
			views: {
				'featured-tab@master.base.decisionguidance': {
					templateUrl: 'views/decisionGuidanceListView.html',
					controller: 'DecisionGuidanceListController'
				}
			},
			resolve: {
				fetchStrategy: function(AP_FETCH_TYPE) {
					return {
						type: AP_FETCH_TYPE.FEATURED
					};
				}
			}
		})
		.state('master.base.decisionguidance.active-dgm', {
			url: '/',
			views: {
				'active-tab@master.base.decisionguidance': {
					templateUrl: 'views/decisionGuidanceListView.html',
					controller: 'DecisionGuidanceListController'
				}
			},
			resolve: {
				fetchStrategy: function(AP_FETCH_TYPE) {
					return {
						type: AP_FETCH_TYPE.ACTIVE
					};
				}
			}
		})
		.state('master.base.user.decisionguidancemodels', {
			url: '/decisionguidancemodels',
			views: {
				'dgm-tab@master.base.user': {
					templateUrl: 'views/decisionGuidanceListView.html',
					controller: 'DecisionGuidanceListController'
				}
			},
			resolve: {
				fetchStrategy: function(AP_FETCH_TYPE, user) {
					return {
						type: AP_FETCH_TYPE.USER,
						params: {
							idUser: user.id
						}
					};
				}
			}
		})
		.state('master.base.decisionguidancemodel', {
			url: '/decisionguidancemodel/{id:int}',

			views: {
				'left-top@master': {
					templateUrl: 'views/decisionGuidanceModelDetailView.html',
					controller: 'DecisionGuidanceModelDetailController'
				},
				'right-top@master': {
					templateUrl: 'views/decisionGuidanceModelSummaryView.html',
					controller: 'DecisionGuidanceModelSummaryController'
				}
			},
			// ArchProfile must be resolved before change to state -> will be injected into controller
			// Also available for the child states
			resolve: {
				decisionGuidanceModel: function(DecisionGuidanceModelService, $state, $stateParams, $rootScope) {
					$rootScope.global.showLoad = true;

					return DecisionGuidanceModelService.getDGMById($stateParams.id).then(function(response) {
						$rootScope.global.showLoad = false;
						return response.data;
					}, function(error) {
						$rootScope.global.showLoad = false;
						$state.go('master.base.error', {status: error.status, statusText: error.statusText});
					});
				}
			}
		})
		.state('master.base.decisionguidancemodel_new', {
			url: '/decisionguidancemodel/new',
			resolve: {
				decisionGuidanceModel: function(DecisionGuidanceModelService, $state) {
					// Creating a new, empty DecisionGuidanceModel -> afterwards the information will be added
					// makes it easier to build the model on client side
					return DecisionGuidanceModelService.createNewEmptyDGM().then(function(response) {
						// Redirect to the archProfile Detail view right away, after creating a new, empty ArchProfile
						$state.go('master.base.decisionguidancemodel', {id: response.data.id });
					});
				}
			}
		});
});