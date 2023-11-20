(function(angular) {
	'use strict';

	function ifLoading($http) {
		return {
			restrict : 'A',
			link : function(scope, elem) {
				scope.isLoading = isLoading;

				scope.$watch(scope.isLoading, toggleElement);

				function toggleElement(loading) {
					if (loading) {
						elem.show();
						var target = angular.element(document.querySelector('#spinnermodel'));
						target.addClass('loading-bar');
					} else {
						elem.hide();
						var target = angular.element(document.querySelector('#spinnermodel'));
						target.removeClass('loading-bar');
					}
				}

				function isLoading() {
					return $http.pendingRequests.length > 0;
				}
			}
		};
	}

	ifLoading.$inject = [ '$http' ];

	angular.module('spinnerApp', []).directive('ifLoading', ifLoading);
}(angular));