/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, KeepaliveProvider, $httpProvider, $locationProvider) {

	//$httpProvider.interceptors.push('myInterceptor');
	
	//To Remove ! sysmbol in Route Url( /#!/)
	//Ref : https://github.com/angular/angular.js/issues/15547
	$locationProvider.hashPrefix('');
	$locationProvider.html5Mode({
	    enabled: false,
	    requireBase: true
	});
	
    // Configure Idle settings
    IdleProvider.idle(600); // in seconds
    IdleProvider.timeout(120); // in seconds

   //$urlRouterProvider.otherwise("/hadoop_profile");

    $urlRouterProvider.otherwise(function($injector, $location){
    	//debugger;
    	var state = $injector.get('$state');
    	//var path =  $location.absUrl();
    	//state.go('hdclusters');
    	state.go('reg_form');
    	return $location.path();
    });
    
    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });
                           
    $stateProvider.state('dashboard', {
        url: "/dashboard",
        templateUrl: "dashboard_temp",
        data: { pageTitle: 'Dahsboard' },
        external: true,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                	/*{
                        serie: true,
                        files: ['resources/js/plugins/dataTables/datatables.min.js','resources/css/plugins/dataTables/datatables.min.css']
                    },
                    {
                        serie: true,
                        name: 'datatables',
                        files: ['resources/js/plugins/dataTables/angular-datatables.js']
                    },
                    {
                        serie: true,
                        name: 'datatables.colreorder',
                        files: ['resources/js/plugins/dataTables/colreorder/angular-datatables.colreorder.js']
                    },
                    {
                        serie: true,
                        files: ['resources/js/plugins/dataTables/dataTables.responsive.min.js',
                        	'resources/js/plugins/dataTables/responsive.dataTables.min.css']
                    },*/
                    
                    /*{
                        insertBefore: '#loadBefore',
                        name: 'toaster',
                        files: ['resources/js/plugins/toastr/toastr.min.js', 'resources/css/plugins/toastr/toastr.min.css']
                    },*/
                    
                ]);
            }
        }
     })
     .state('reg_form', {
        url: "/reg_template",
        templateUrl: "reg_template",
        data: { pageTitle: 'Registration Form' },
        external: true,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                	{
                        files: ['resources/js/jquery.validate.min.js']
                    },
                    {
                    	name:'localytics.directives',
                    	files: ["https://cdnjs.cloudflare.com/ajax/libs/angular-chosen-localytics/1.9.2/angular-chosen.min.js"]
                    }
                ]);
            }
        }
     })
     .state('edit-profile', {
        url: "/edit/{id}/profile",
        templateUrl: "reg_template",
        data: { pageTitle: 'Registration Form' },
        external: true,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                	{
                        files: ['resources/js/jquery.validate.min.js']
                    }
                ]);
            }
        }
     })
     .state('profile', {
        url: "/user/{id}/profile",
        templateUrl: "user/profile",
        data: { pageTitle: 'Registration Form' },
        external: true,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                	{
                        files: ['resources/js/jquery.validate.min.js']
                    }
                ]);
            }
        }
     })
     .state('users', {
        url: "/users",
        templateUrl: "users_template",
        data: { pageTitle: 'Registration Form' },
        external: true,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
					
                ]);
            }
        }
     })
     .state('my_reference_members', {
        url: "/myreferencemembers",
        templateUrl: "my_reference_members",
        data: { pageTitle: 'my reference members' },
        external: true,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
					
                ]);
            }
        }
     })
     .state('employees', {
        url: "/employees",
        templateUrl: "user/employees_temp",
        data: { pageTitle: 'Employees' },
        external: true,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                	{
                        files: ['resources/js/jquery.validate.min.js']
                    }
                ]);
            }
        }
     })
     
}

var mod = angular.module('apsf');

mod.config(config)
.run(function($rootScope, $state) {
    $rootScope.$state = $state;
    $rootScope.$on('$stateChangeSuccess', function(event, current, previous) {
    	$rootScope.pageTitle = current.data.pageTitle
    	$scope.pageTitle = current.data.pageTitle
    });
});

mod.directive('updateTitle', ['$rootScope', '$timeout',
	  function($rootScope, $timeout) {
	    return {
	      link: function(scope, element) {

	        var listener = function(event, toState) {

	          var title = 'Default Title';
	          if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle;

	          $timeout(function() {
	            element.text(title);
	          }, 0, false);
	        };

	        $rootScope.$on('$stateChangeSuccess', listener);
	      }
	    };
	  }
	]);
