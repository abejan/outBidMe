var loginModule = angular.module('login', []);

loginModule.controller("LoginController", ["$scope", "$http", "$q" , function($scope, $http, $q){
	
	this.credentials = {};
	this.authMessage = "";
	this.promise =-$q.defer();
	
	this.sendCredentials = function(){
	    $http({
            method: "post",
            url:    "sign-in",
            data:    this.credentials
        }).success(response());
	};
	
	this.resetCredentials = function(){
		this.credentials = {};
	};
	
	this.getAuthenticatedMessage = function(){
		return this.authMessage;
	};
	
	/*Private members*/
	var response = function(data){
	    $scope.authResponse = data;
	};
	
}]);
