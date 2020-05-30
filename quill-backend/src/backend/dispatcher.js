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
    push(msg, receivers) {
        try {
            console.log(clientManager.push.map)
            for (let recvId of receivers) {
                let client = clientManager.push.getInfo(recvId);
                console.log(client);
                if (client)
                    client.send(JSON.stringify(msg));
            }
        } catch (e) {
            console.error(e);
        }
    }
}

module.exports = dispatcher;