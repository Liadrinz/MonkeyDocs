const serverConfig = require('../../server.config');
const redis = require('redis');
const redisClient = redis.createClient(serverConfig.redis.port, serverConfig.redis.host);
redisClient.auth(serverConfig.redis.auth);
module.exports = redisClient;