const axios = require('axios').default;
const serverConfig = require('../../server.config');
const redisClient = require('./redisClient');

const bizHost = serverConfig.biz.host;
const prefix = 'http://' + bizHost + '/';

const DAO = {
    delta: {
        maxSize: 100,
        counter: 0,
        getByDocId(docId) {
            return axios.get(prefix + 'rest/delta.json?docid=' + docId);
        },
        // persist all deltas
        createAll(deltaList) {
            return axios.post(prefix + 'mvc/delta/createAll', JSON.stringify({ deltas: JSON.stringify(deltaList) }), {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
        },
        // sync the content in redis and mysql
        syncDoc(docId, immediate = false, callback = () => {}) {
            if (this.counter++ >= this.maxSize || immediate) {
                this.counter = 0;
                this.getByDocId(docId).then((res) => {
                    let pData = res.data;
                    redisClient.lrange('history-' + docId, 0, -1, (err, reply) => {
                        let tData = reply.reverse();
                        if (pData.length == tData.length) {
                            callback();
                            return;
                        }
                        if (pData.length < tData.length) {
                            let buffer = [];
                            for (let i = pData.length; i < tData.length; ++i) {
                                buffer.push({
                                    docid: docId,
                                    userid: JSON.parse(tData[i]).attributes.userId,
                                    content: tData[i].replace('u0027', "'")
                                });
                            }
                            this.createAll(buffer).then(callback);
                        } else {
                            for (let i = tData.length; i < pData.length; ++i) {
                                let content = pData[i].content;
                                redisClient.lpush('history-' + docId, content.substr(1, content.length - 2), callback);
                            }
                        }
                    })
                })
            }
        },
        makeCheckpoint(docId) {
            return this.getByDocId(docId).then((res) => {
                let len = res.data.length;
                return res.data[len - 1].id;
            }).then((lastDelta) => {
                return axios.post(prefix + 'rest/checkpoint', {
                    docid: docId,
                    lastDelta: lastDelta
                })
            })
        },
        // unload the document from redis
        unload(docId) {
            this.syncDoc(docId, true, () => {
                console.log('history-' + docId);
                redisClient.del('history-' + docId);
            })
        }
    }
}

module.exports = DAO;