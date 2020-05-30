const clientManager = require('./clientManager');
const handler = require('./handler');

function initPushService(conn, userId) {
    clientManager.push.putInfo(userId, conn);
    conn.on('text', function(msg) {
        try {
            msg = JSON.parse(msg);
            handler.handlePush(msg.sender, msg.receivers, msg.text);
        } catch (e) {
            console.error(e);
        }
    })
    conn.on('close', function() {
        try {
            clientManager.push.clearInfo(userId);
        } catch (e) {
            console.error(e);
        }
    })
    conn.on('error', function(e) {
        console.log(e);
    })
}

module.exports = initPushService;