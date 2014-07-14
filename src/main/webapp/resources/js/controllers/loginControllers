var loginModule = angular.module('login', []);

loginModule.controller("LoginController", ["$scope", "$http" , function($scope, $http){
	this.credentials = {};
	this.sendCredentials = function(){
		$http.post("/sign-in", this.credentials);
		this.credentials = {};
	};
}]);
