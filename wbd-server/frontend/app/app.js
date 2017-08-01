'use strict';

// Declare app level module which depends on views, and components
var wbdApp = angular.module('wbdApp', [
  'ngRoute',
  'wbdApp.chat',
  'wbdApp.view2',
  'wbdApp.version'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/chat'});
}]);
