'use strict';

angular.module('wbdApp.chat', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/chat', {
            templateUrl: 'chat/chat.html',
            controller: 'ChatController'
        });
    }])
    .controller('ChatController', ChatController);

function ChatController() {
    this.message = '';
    this.socket = new WebSocket("ws://localhost/ws");
    this.socket.onmessage = function (event) {
        var msg = event.data;
        console.log(msg);
        $('#messages').append($('<li>').text(msg));
    }
    // $('form').submit(function(){
    //     ChatController.send();
    //     return false;
    // });
}

ChatController.prototype.send = function () {
    this.socket.send(this.message);
    this.message = '';
};