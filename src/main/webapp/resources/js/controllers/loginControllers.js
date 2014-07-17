var loginModule = angular.module('login', [])

  .factory('postCredentialsService', function($http, $q){
	  
		  var service = {};
		  
		  service.doPost = function(credentials){
			  
			  var deferred = $q.defer();
			  
			  $http({
		            method: "post",
		            url:    "sign-in",
		            data:   credentials
		       }).success( function(data){
		    	    deferred.resolve(data);
			   }).error(function(data){
				    deferred.reject(data);
			   });
			    
			  return deferred.promise;
		  };
		  
		  return service;
  })

  .controller("LoginController", ["$scope", "postCredentialsService", function($scope, postCredentialsService){

	  	init();
		  
		function init(){
			this.credentials = {};
			$scope.authMessage = "";
		};
		
		this.sendCredentials = function(){
			postCredentialsService.doPost(this.credentials).then(function(response) {
	             $scope.authMessage   = response.authMessage;
	             $scope.authenticated = response.authenticated;
	          }, function(error) {
	        	 $scope.authMessage = "A server error occured while processing credentials: " + error;
	          }); 
		};
	
}]);
