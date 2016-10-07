angular.module('archkb.error', ['ui.router']);

angular.module('archkb.service', ['ngFileUpload']);

angular.module('archkb.user', ['archkb.service']);

angular.module('archkb.featuring', ['archkb.service']);

angular.module('archkb.archprofile', ['archkb.service', 'ngFileUpload']);

angular.module('archkb.decisionguidance', ['archkb.service', 'ngFileUpload']);

angular.module('archkb.tabbed', ['archkb.featuring', 'pascalprecht.translate']);

angular.module('archkb.partial', ['archkb.partial.eip']);

angular.module('archkb.partial.eip', ['monospaced.elastic']);

angular.module('archkb.masterpage', ['archkb.tabbed', 'archkb.featuring', 'archkb.archprofile', 'archkb.decisionguidance', 'archkb.partial', 'archkb.user', 'archkb.service']);

angular.module('archkb', ['archkb.masterpage', 'archkb.error', 'ui.router', 'pascalprecht.translate', 'ngSanitize', 'ui.bootstrap', 'ngResource', 'monospaced.elastic']);




