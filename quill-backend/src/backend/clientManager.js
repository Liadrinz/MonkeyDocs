function Manager(type) {
    if (type == 'collaborate') {

    } else {
        this.getInfo = function(userId) {
            return this.map[userId];
        };
        this.putInfo = function(userId, info) {
            this.map[userId] = info;
        };
        this.clearInfo = function(userId) {
            delete this.map[userId];
        };
    }
}

const clientManager = {
    collaborate: {
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
        },
    },
    push: {
        map: {},
        getInfo(userId) {
            return this.map[userId];
        },
        putInfo(userId, info) {
            this.map[userId] = info;
        },
        clearInfo(userId) {
            delete this.map[userId];
        },
    }
}

module.exports = clientManager;