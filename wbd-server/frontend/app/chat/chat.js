'use strict';

angular.module('wbdApp.chat', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/chat', {
        templateUrl: 'chat/chat.html',
        controller: 'ChatController'
    });
}])

.controller('ChatController', function () {
    console.log('ChatController');
    var socket = new Socket('/ws/chat', function (event) {
        console.log('Received: ' + event.data);
        var msg = JSON.parse(event.data);
        $('#chat-messages').append($('<li>').text(msg.user + ': ' + msg.text));
    });

    $('#chat-form').submit(function () {
        var input = $('#chat-m');
        socket.send(JSON.stringify({userId: 12, text: input.val()}));
        input.val('');
    });
});