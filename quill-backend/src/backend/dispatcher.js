const clientManager = require('./clientManager');

const dispatcher = {
    broadcast(msg, docId) {
        try {
            let userMap = clientManager.collaborate.getInfosByDocId(docId);
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
    },
    push(receivers) {
        try {
            for (let recvId of receivers) {
                let client = clientManager.push.getInfo(recvId);
                if (client)
                    client.send('null');
            }
        } catch (e) {
            console.error(e);
        }
    }
}

module.exports = dispatcher;