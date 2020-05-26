const ws = require('nodejs-websocket');
const clientManager = require('./clientManager');
const handler = require('./handler');

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
                        break;
                    default:
                        break;
                }
            } catch (e) {
                console.error(e);
            }
        })
        conn.on('close', function() {
            try {
                clientManager.clearInfo(docId, userId);
            } catch (e) {
                console.error(e);
            }
        })
        conn.on('error', function() {
            try {
                clientManager.clearInfo(docId, userId);
            } catch (e) {
                console.error(e);
            }
        })
    })
    return server
}

module.exports = createServer()