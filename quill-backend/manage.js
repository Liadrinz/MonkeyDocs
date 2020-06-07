const serverConfig = require('./server.config');
const server = require('./src/backend/server');
server.listen(serverConfig.server.port, serverConfig.server.host);