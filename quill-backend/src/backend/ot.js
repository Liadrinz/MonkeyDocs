const Delta = require('../../dist/Delta');

function ot(history, delta, oldDelta) {
    try {
        history = new Delta().insert('\n').compose(history);
        let diff = oldDelta.diff(history);
        if (diff.ops.length > 0)
            return diff.transform(delta, true);
        return delta;
    } catch (e) {
        console.error(e);
        return delta;
    }
}

module.exports = ot;