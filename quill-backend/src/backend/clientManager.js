const clientManager = {
    map: {},
    getInfosByDocId(docId) {
        return this.map[docId];
    },
    getInfo(docId, userId) {
        return this.map[docId][userId];
    },
    putInfo(docId, userId, info) {
        if (this.map[docId] == undefined) {
            this.map[docId] = {};
        }
        this.map[docId][userId] = info;
    },
    clearInfo(docId, userId) {
        delete this.map[docId][userId];
        if (Object.keys(this.map[docId]).length == 0) delete this.map[docId];
    }
}

module.exports = clientManager;