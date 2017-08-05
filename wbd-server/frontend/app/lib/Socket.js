var Socket = function (url, callback) {
    var ws = new WebSocket('ws://' + location.hostname + url);
    ws.onmessage = callback;
    console.log('Connected to ' + ws.url);
    this.send = function (text) {
        ws.send(text);
    };
};