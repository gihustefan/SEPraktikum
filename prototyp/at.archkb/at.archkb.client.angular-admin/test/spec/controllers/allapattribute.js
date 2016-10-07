'use strict';

describe('Controller: AllapattributeCtrl', function () {

  // load the controller's module
  beforeEach(module('architectureKbadminApp'));

  var AllapattributeCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AllapattributeCtrl = $controller('AllapattributeCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AllapattributeCtrl.awesomeThings.length).toBe(3);
  });
});
