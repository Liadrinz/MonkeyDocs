const clientManager = require('./clientManager');
const handler = require('./handler');
const DAO = require('./DAO');

function initCollaborateService(conn, docId, userId) {
    let synched = false;
    clientManager.collaborate.putInfo(docId, userId, conn);
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
            clientManager.collaborate.clearInfo(docId, userId);
            if (clientManager.collaborate.getInfosByDocId(docId) == undefined) {
                console.log(clientManager.collaborate.getInfosByDocId(docId))
                DAO.delta.unload(docId);
            }
        } catch (e) {
            console.error(e);
        }
    })
    conn.on('error', function(e) {
        console.log(e);
    })
}

module.exports = initCollaborateService;