const ws = require('nodejs-websocket');
const clientManager = require('./clientManager');
const handler = require('./handler');
const DAO = require('./DAO');

const createServer = () => {
    let server = ws.createServer(conn => {
        let [_, docId, userId] = conn.path.split('/');
        let synched = false;
        clientManager.putInfo(docId, userId, conn);
        DAO.delta.syncDoc(docId, true, () => {
            synched = true;
        });
        conn.on('text', function(msg) {
            try {
                msg = JSON.parse(msg);
                switch (msg.type) {
                    case 'delta':
                        handler.handleDelta(msg.delta, msg.oldDelta);
                        break;
                    case 'req':
                        let s = setInterval(() => {
                            if (!synched) return;
                            handler.handleReq(msg.delta.attributes.docId, conn);
                            clearInterval(s);
                        })
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
                if (clientManager.getInfosByDocId(docId) == undefined) {
                    console.log(clientManager.getInfosByDocId(docId))
                    DAO.delta.unload(docId);
                }
            } catch (e) {
                console.error(e);
            }
        })
        conn.on('error', function(e) {
            console.log(e);
        })
    })
    return server
}

module.exports = createServer()