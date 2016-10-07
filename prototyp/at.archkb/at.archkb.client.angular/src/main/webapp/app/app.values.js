// Static Values for the Relation Urls
angular.module('archkb').value('URL', {
	api: 'api',
	archProfile: {
		base: 'ap',
		relations: {
			qualityattributes: 'qualityattributes',
			constraints: 'constraints',
			tradeoffs: 'tradeoffs',
			architecturestyles: 'architecturestyles',
			drivers: 'drivers',
			designDecisions: 'designdecisions',
			diagrams: 'diagrams',
			tradeoffItems: 'tradeoffitems'
		}
	},
	decisionGuidanceModels: {
		base: 'dgm',
		relations: {
			potentialrequirements: 'potentialrequirements',
			designoptions: 'designoptions',
			relatedguidancemodels: 'relatedguidancemodels'
		}
	},
	designOptions: {
		base: 'do',
		relations: {
			implications: 'implications',
			addressedRequirements: 'addressedrequirements',
			effectedguidancemodels: 'effectedguidancemodels',
			components: 'components'
		}
	},
	decisionDocumentationModels: {
		base: 'ddm',
		relations: {
			designdecisions: 'designdecisions',
			addressedRequirements: 'addressedrequirements',
			effecteddecisiondocumentation: 'effecteddecisiondocumentation',
			tradeoffs: 'tradeoffs',
			implications: 'implications',
			requiredcomponents: 'requiredcomponents'
		}
	},
	authentications: {
		base: 'authentications',
		methods: {
			user: 'user'
		}
	},
	users: {
		base: 'users',
        relations: {
            archprofiles: 'archprofiles',
			decisionguidancemodels: 'decisionguidancemodels'
        }
	},
	logout: 'logout'
}).value('MESSAGE_TYPE', {
	ERROR: 'danger',
    WARNING: 'warning',
    INFO: 'info',
    SUCCESS: 'success'
}).value('AP_FETCH_TYPE', {
	NEWEST: 'NEWEST',
	ACTIVE: 'ACTIVE',
	FEATURED: 'FEATURED',
	USER: 'USER'
}).value('MAX_UPLOAD_SIZE', 2000000);