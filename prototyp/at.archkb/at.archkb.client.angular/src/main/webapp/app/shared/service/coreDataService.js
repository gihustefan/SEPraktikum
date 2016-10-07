angular.module('archkb.service').factory('CoreDataService', function($http, URL) {
	
	var service = {};

	var baseUrl = URL.api;
	
	// Getting a list of core data based on a partial title 
	service.getCoreData = function(titlePartial, relationUrl) {
		var config = {
			params: {
				titlePartial: titlePartial
			}	
		};
		var url = '/' + URL.api + '/' + relationUrl;
		
		return $http.get(url, config);
	};

	/**
	 * Creating a new TradeoffItem (core Data)
	 * @param tradeoffItem The data for the tradeoffItem
	 * @returns {HttpPromise} Returns a HTTP Promise
     */
	service.createTradeoffItem = function(tradeoffItem) {
		var url = baseUrl + '/' + URL.archProfile.relations.tradeoffItems;

		return $http.post(url, tradeoffItem);
	};
	
	return service;
});