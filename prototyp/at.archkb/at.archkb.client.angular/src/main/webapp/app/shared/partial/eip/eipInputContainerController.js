angular.module('archkb.partial.eip').controller('EipInputContainerController', function($scope) {

    // Help method for removing an item from the collection
    var removeItem = function(item) {
        // all lower order must be moved up by one
        angular.forEach($scope.collection, function(value, key) {
            if(value.ordering > item.ordering && value != item) {
                // Moving up items - that were below the deleted item
                value.ordering = value.ordering - 1;
            }
        });
        // only remove if there are more -> otherwise only clear element and leave it there
        $scope.collection.splice($scope.collection.indexOf(item), 1);
    };

    // Help function -> moving items within a collection
    var replaceItemInCollection = function(item, collection, promise) {
        if(angular.isUndefined(item) || angular.isUndefined(item.id) || angular.isUndefined(item.ordering) || angular.isUndefined(collection) || angular.isUndefined(promise)) {
            console.log('Not moving - because of undefined values');
            return;
        }
        var itemOrderBefore = item.ordering;

        // Show Wait for Promise
        $scope.waitingForPromise = true;

        // The Promise is given by the parent scope -> we have to wait for the results before updating the UI!
        promise().then(function(response) {
            // Successfully moved
            item.ordering = response.data.ordering;
            angular.forEach(collection, function(value, key) {
                // Have to find the other value with the same ordering -> replace them
                if(value.ordering === item.ordering && value != item) {
                    // Replace the values
                    value.ordering = itemOrderBefore;
                    return;
                }
            });
            $scope.waitingForPromise = false;
        }, function(error) {
            // TODO: Better error handling
            console.log('Not moving - Server error: ' + error);
            $scope.waitingForPromise = false;
        });
    };

    // Adding a dummy item if the user for example removed all of them
    var addDummyItem = function() {
        if($scope.collection.length === 0) {
            $scope.collection.push({
                ordering: 0
            });
        }
    };

	// Move item one up -> information about what promise should be used!
	$scope.up = function(item) {

        if(angular.isUndefined(item) || angular.isUndefined(item.ordering)) {
            console.log('Not Moving item Up - undefined values');
            return;
        }
        // Not moving up, if already on top
        if(item.ordering <= 0)
            return;

		replaceItemInCollection(item, $scope.collection, $scope.orderingUpPromise);
	};

	// Move item one down -> information about what promise should be used!
	$scope.down = function(item) {
        if(angular.isUndefined(item) || angular.isUndefined(item.ordering)) {
            console.log('Not Moving item Up - undefined values');
            return;
        }
        // Not moving down if already on bottom
        if(item.ordering + 1 >= $scope.collection.length)
            return;

		replaceItemInCollection(item, $scope.collection, $scope.orderingDownPromise);
	};

	// Remove item from collection
	$scope.remove = function(item) {
		if(angular.isUndefined(item) || angular.isUndefined($scope.collection)) {
			console.log('Delete not performed - item or collection not defined.');
			return;
		}

        // Means not present at the server at the moment -> just remove from the collection!
        if(angular.isUndefined(item.id)) {
            removeItem(item);
            addDummyItem();
            return;
        }

        $scope.waitingForPromise = true;

		// Executing the delete Promise (defined by parent promise)
		$scope.deletePromise().then(function() {
			removeItem(item);
            addDummyItem();
            $scope.waitingForPromise = false;
		}, function(error) {
			// TODO: Better error handling
			console.log('Not moving - Server error: ' + error);
            $scope.waitingForPromise = false;
		});
	};

	// Add item at position
	$scope.add = function(position) {
		
		// Moving all the following items one down
		angular.forEach($scope.collection, function(value, key) {
			if(value.ordering >= position) {
				value.ordering = value.ordering + 1;
			}
		});

		// adding a new item -> setting the ordering value to length of the collection
		$scope.collection.push({
			ordering: position
		});
	};
});