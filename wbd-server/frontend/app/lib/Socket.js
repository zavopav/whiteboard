var Socket = function (url, onopen, onmessage) {
    var ws = new WebSocket('ws://' + location.hostname + url);
    ws.onopen = onopen;
    ws.onmessage = onmessage;
    console.log('Connected to ' + ws.url);
    this.send = function (text) {
        ws.send(text);
    };
};