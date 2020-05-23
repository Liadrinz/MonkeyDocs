const clientManager = require('./clientManager');

const dispatcher = {
    broadcast(msg, docId) {
        let userMap = clientManager.getInfosByDocId(docId);
        for (let key in userMap) {
            userMap[key].send(JSON.stringify(msg));
        }
    },
    respond(msg, conn) {
        conn.send(JSON.stringify(msg));
    }
}

module.exports = dispatcher;