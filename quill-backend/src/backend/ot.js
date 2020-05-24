const Delta = require('../../dist/Delta');

function ot(history, delta, oldDelta) {
    console.log('before', history)
    history = new Delta().insert('\n').compose(history);
    console.log('later', history);
    let diff = oldDelta.diff(history);
    if (diff.ops.length > 0)
        return diff.transform(delta, true);
    return delta;
}

module.exports = ot;