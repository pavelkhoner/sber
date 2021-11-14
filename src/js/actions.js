function rollDice(randomInt, context) {
    addAction({
        type: "roll_dice",
        randomInt: randomInt
    }, context);
}

function restart(context) {
    addAction({
        type: "restart",
        message: get_request(context)
    }, context);
}

function checkWon(context) {
    var items = get_items(get_request(context));
    return parseInt(items[0]) <= 99
}

function willWin(randomInt, context) {
    var items = get_items(get_request(context));
    return parseInt(items[0]) + randomInt > 99
}