'use strict';

/**
 * Services document
 */
(function () {
/**
 * Common actions to application 
 * @param $scope
 * @param $compile
 * @param $http
 * @param $state
 * @param $parse
 * @returns
 */
function UtilService($q,$http,toaster){
	
	/**
	 * If any Method added in this service, Need to declare in services object variable
	 */
	
	/**
	 * Get service call
	 */
	function getObject(url,object,headers)
	{	
		var deferred = $q.defer();
		var res;
		$http({
	   	      method  : 'GET',
	   	      url     : url,
	   	      headers : headers,
	   	      params :object
	   	      })
	   	      .then(function success(response) {
	   	    	  deferred.resolve(response.data);
	   	      },
	   	      function error(response){
	   	    	deferred.resolve(response);
	   	      });
		
		res = deferred.promise;
		return $q.when(res);
	}
	
	/**
	 * Post service call
	 */
	function postObject(url,object,headers)
	{	
		var deferred = $q.defer();
		var res;
		$http({
   	      method  : 'POST',
   	      url     : url,
   	      headers : headers,
   	      data :object
   	      }).then(function success(response) {
   	    	  deferred.resolve(response.data);
   	      },
   	      function error(response){
   	    	deferred.resolve(response);
   	      });
		res = deferred.promise;
		return $q.when(res);
	}
	
	/**
	 * Post service call
	 */
	function putObject(url,object,headers)
	{	
		var deferred = $q.defer();
		var res;
		$http({
   	      method  : 'PUT',
   	      url     : url,
   	      headers : headers,
   	      data :object
   	      }).then(function success(response) {
   	    	  deferred.resolve(response.data);
   	      },
   	      function error(response){
   	    	deferred.resolve(response);
   	      });
		res = deferred.promise;
		return $q.when(res);
	}
	
	/**
	 * Post service call
	 */
	function patchObject(url,object,headers)
	{	
		var deferred = $q.defer();
		var res;
		$http({
   	      method  : 'PATCH',
   	      url     : url,
   	      headers : headers,
   	      data :object
   	      }).then(function success(response) {
   	    	  deferred.resolve(response.data);
   	      },
   	      function error(response){
   	    	deferred.resolve(response);
   	      });
		res = deferred.promise;
		return $q.when(res);
	}
	
	/**
	 * Post service call
	 */
	function deleteObject(url,object,headers)
	{	
		var deferred = $q.defer();
		var res;
		$http({
   	      method  : 'DELETE',
   	      url     : url,
   	      headers : headers,
   	      data :object
   	      }).then(function success(response) {
   	    	  deferred.resolve(response.data);
   	      },
   	      function error(response){
   	    	deferred.resolve(response);
   	      });
		res = deferred.promise;
		return $q.when(res);
	}
	
	
	
	/**
	 * Toaster Popup display
	 * messgToaster
	 * parameters: Type(success,error), Message(message to display)
	 */
	function messageToaster(type,message,timeout){
		
        toaster.pop({
            type: type,
            body: message,
            showCloseButton: true,
            timeout: timeout?timeout:4000
        });
		
    };
	function messgToaster1(type,message){
			
		toastr.options = {
		  "closeButton": true,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": false,
		  "rtl": false,
		  "positionClass": "toast-top-right",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": 300,
		  "hideDuration": 1000,
		  "timeOut": 5000,
		  "extendedTimeOut": 1000,
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
		}
		toastr['success'](message);	
	 };
    
    var possible = "abcdefghijklmnopqrstuvwxyz0123456789";
    function randomTxt() {
    	var text = "";
    	for (var i = 0; i < 5; i++)
    		text += possible.charAt(Math.floor(Math.random() * possible.length));
    	return text;
    }
    
    /**
	 * Take Request Object and process return response
	 */
	function oboePostObj( url, reqObj ){
		var deferred = $q.defer();
		var res;
		oboe({
	   	     method: 'POST',
	   	     url: url,
	   	     body: reqObj
   	 	})
   	    .fail(function(response) {
   	    	// we don't got it
   	    	console.log(response);
   	    	deferred.resolve(response);  
   		})
   	    .done(function(response){
   	    	deferred.resolve(response);	
	   	});
		res = deferred.promise;
		return $q.when(res);
	}
	
	/**
	 * If any Method added in this service, 
	 * Need to declare in services object variable
	 */
	var services={
		getObject:getObject,
		postObject:postObject,
		putObject:putObject,
		patchObject:patchObject,
		deleteObject:deleteObject,
		messageToaster:messageToaster,
		randomTxt:randomTxt,
		oboePostObj:oboePostObj
	};
	return services;
}

function UserService(){
	
	function getStatusClass(status){
		var cls = '';
		if(status == 'VERIFIED'){
			cls = 'label-success';
		}else if(status == 'PENDING'){
			cls = 'label-warning';
		}else if(status == 'DELETED'){
			cls = 'label-danger';
		}else if(status == 'SUSPENDED'){
			cls = 'label-danger';
		}
		return cls;
	}
	var services={
		getStatusClass:getStatusClass
	};
	return services;
 }

//Ingest functions to controllers into module    
angular.module('apsf')
	.factory("UtilService", UtilService)
	.factory("UserService", UserService);
})();
