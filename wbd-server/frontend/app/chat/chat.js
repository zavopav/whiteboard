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
    var chatId = 1;
    var socket = new Socket('/ws/chat',
        function (event) {
            console.log('Connected to chat');
            socket.send(JSON.stringify({command: 'LOAD', chatId: chatId}));
        },
        function (event) {
            console.log('Received: ' + event.data);
            var msg = JSON.parse(event.data);
            $('#chat-messages').append($('<li>').text(msg.author + ': ' + msg.text));
        }
    );

    $('#chat-form').submit(function () {
        var input = $('#chat-m');
        socket.send(JSON.stringify({chatId: chatId, authorId: 12, text: input.val()}));
        input.val('');
    });
});