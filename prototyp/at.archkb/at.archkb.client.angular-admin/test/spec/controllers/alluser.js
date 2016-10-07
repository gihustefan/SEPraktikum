'use strict';

describe('Controller: AlluserCtrl', function () {

  // load the controller's module
  beforeEach(module('architectureKbadminApp'));

  var AlluserCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AlluserCtrl = $controller('AlluserCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AlluserCtrl.awesomeThings.length).toBe(3);
  });
});
