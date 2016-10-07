'use strict';

describe('Controller: ApattributedetailCtrl', function () {

  // load the controller's module
  beforeEach(module('architectureKbadminApp'));

  var ApattributedetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ApattributedetailCtrl = $controller('ApattributedetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ApattributedetailCtrl.awesomeThings.length).toBe(3);
  });
});
