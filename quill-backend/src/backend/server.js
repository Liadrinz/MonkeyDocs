const ws = require('nodejs-websocket');
const clientManager = require('./clientManager');
const handler = require('./handler');
const dispatcher = require('./dispatcher');

const createServer = () => {
    let server = ws.createServer(conn => {
        let [_, docId, userId] = conn.path.split('/');
        clientManager.putInfo(docId, userId, conn);
        conn.on('text', function(msg) {
            try {
                msg = JSON.parse(msg);
                switch (msg.type) {
                    case 'delta':
                        handler.handleDelta(msg.delta, msg.oldDelta);
                        break;
                    case 'req':
                        handler.handleReq(msg.delta.attributes.docId, conn);
                        break;
                    case 'save':
                        handler.handleSave(msg.delta.attributes.docId);
                        msg.type = 'ack';
                        msg.payload = null
                        conn.send(msg);
                        break;
                }
            } catch (e) {
                console.log(e);
            }
        })
        conn.on('close', function() {
            clientManager.clearInfo(docId, userId);
        })
        conn.on('error', function() {
            clientManager.clearInfo(docId, userId);
        })
    })
    return server
}

module.exports = createServer()