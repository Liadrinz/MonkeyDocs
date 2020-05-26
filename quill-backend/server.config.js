const serverConfig = {
    server: {
        host: '0.0.0.0',
        port: 8088
    },
    redis: {
        host: '',
        port: 6379,
        auth: '123456'
    },
    biz: {
        host: 'localhost:8089/MonkeyDocs'
    }
}

module.exports = serverConfig;