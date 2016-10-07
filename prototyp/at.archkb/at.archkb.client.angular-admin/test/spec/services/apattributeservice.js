'use strict';

describe('Service: apattributeservice', function () {

  // load the service's module
  beforeEach(module('architectureKbadminApp'));

  // instantiate service
  var apattributeservice;
  beforeEach(inject(function (_apattributeservice_) {
    apattributeservice = _apattributeservice_;
  }));

  it('should do something', function () {
    expect(!!apattributeservice).toBe(true);
  });

});
