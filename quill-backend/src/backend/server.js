const ws = require('nodejs-websocket');
const initCollaborateService = require('./collaborate');
const initPushService = require('./push');

const createServer = () => {
    let server = ws.createServer(conn => {
        let [_, root, path1, path2] = conn.path.split('/');
        console.log(root, path1, path2);
        if (root === 'collaborate') {
            initCollaborateService(conn, path1, path2);
        } else if (root === 'push') {
            initPushService(conn, path1);
        }
    })
    return server
}

module.exports = createServer()