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
    this.message = {userId: 12, text:''};
    this.socket = new WebSocket("ws://" + location.hostname + "/ws/chat");
    this.socket.onmessage = function (event) {
        var msg = JSON.parse(event.data);
        console.log(msg);
        $('#messages').append($('<li>').text(msg.user + ": " + msg.text));
    }
    // $('form').submit(function(){
    //     ChatController.send();
    //     return false;
    // });
}

ChatController.prototype.send = function () {
    this.socket.send(JSON.stringify(this.message));
    this.message.text = '';
};