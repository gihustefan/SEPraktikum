/**
 * Created by Rainer on 25.05.2016.
 */
angular.module('archkb.partial').directive('archkbConfirmButton', function () {

    var directive = {};

    directive.restrict = 'E';

    directive.templateUrl = 'views/confirmButtonView.html';

    directive.scope = {
        onConfirm: '&',
        text: '@'
    };

    return directive;
});