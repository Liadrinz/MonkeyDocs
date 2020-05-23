const Delta = require('../../dist/Delta');

function ot(history, delta, oldDelta) {
    console.log('before', history)
    history = new Delta().insert('\n').compose(history);
    console.log('later', history);
    let diff = history.diff(oldDelta);
    // let i = 1;
    // let lastEnterInsert = diff.ops[diff.ops.length - i];
    // while (!lastEnterInsert.insert || lastEnterInsert.insert.indexOf('\n') == -1) {
    //     lastEnterInsert = diff.ops[diff.ops.length - ++i];
    // }
    // lastEnterInsert.insert = lastEnterInsert.insert.substr(1);
    // if (lastEnterInsert.insert == '') diff.ops.splice(-i, 1);
    if (diff.ops.length > 0)
        return diff.transform(delta, true);
    return delta;
}

module.exports = ot;