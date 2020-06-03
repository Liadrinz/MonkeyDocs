function Message(type, delta, oldDelta) {
    this.type = type;
    this.delta = delta;
    this.oldDelta = oldDelta;
}

module.exports = Message;