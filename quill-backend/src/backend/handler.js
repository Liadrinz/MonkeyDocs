const dispatcher = require('./dispatcher');
const clientManager = require('./clientManager');
const redisClient = require('./redisClient');
const Message = require('./Message');
const ot = require('./ot');
const Delta = require('../../dist/Delta');

const handler = {
    handleDelta(delta, oldDelta) {
        try {
            redisClient.lrange('history-' + delta.attributes.docId, 0, -1, (err, reply) => {
                try {
                    let historyDelta = new Delta();
                    let history = JSON.parse('[' + reply.reverse().toString() + ']');
                    for (let record of history) {
                        historyDelta = historyDelta.compose(new Delta(record.ops));
                    }
                    Object.assign(delta, ot(historyDelta, new Delta(delta), new Delta(oldDelta)));
                    redisClient.lpush('history-' + delta.attributes.docId, JSON.stringify(delta));
                    dispatcher.broadcast(new Message('mod', delta, historyDelta), delta.attributes.docId);
                } catch (e) {
                    console.error(e);
                }
            })
        } catch (e) {
            console.error(e);
        }
    },
    handleReq(docId, conn) {
        try {
            redisClient.lrange('history-' + docId, 0, -1, (err, reply) => {
                try {
                    let msg = new Message();
                    msg.type = 'res';
                    msg.delta = new Delta();
                    let history = JSON.parse('[' + reply.reverse().toString() + ']');
                    for (let record of history) {
                        msg.delta = msg.delta.compose(new Delta(record.ops));
                    }
                    dispatcher.respond(msg, conn);
                } catch (e) {
                    console.error(e);
                }
            })
        } catch (e) {
            console.error(e);
        }
    },
    handleSave(docId) {

    }
}

module.exports = handler;