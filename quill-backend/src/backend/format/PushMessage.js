function PushMessage(sender, receivers, text) {
    this.sender = sender;
    this.receivers = receivers;
    this.text = text;
}

module.exports = PushMessage;