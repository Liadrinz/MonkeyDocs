const clientManager = require('./clientManager');

const dispatcher = {
    broadcast(msg, docId) {
        try {
            let userMap = clientManager.getInfosByDocId(docId);
            for (let key in userMap) {
                userMap[key].send(JSON.stringify(msg));
            }
        } catch (e) {
            console.error(e);
        }
    },
    respond(msg, conn) {
        try {
            conn.send(JSON.stringify(msg));
        } catch (e) {
            console.error(e);
        }
    }
}

module.exports = dispatcher;