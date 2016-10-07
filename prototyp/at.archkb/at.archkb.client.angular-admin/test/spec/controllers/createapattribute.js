'use strict';

describe('Controller: CreateapattributeCtrl', function () {

  // load the controller's module
  beforeEach(module('architectureKbadminApp'));

  var CreateapattributeCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CreateapattributeCtrl = $controller('CreateapattributeCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CreateapattributeCtrl.awesomeThings.length).toBe(3);
  });
});
