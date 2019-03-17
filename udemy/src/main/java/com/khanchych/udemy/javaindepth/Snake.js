const FIELD_SIZE = 10;
const MAX_SPEED = 10;

const TICK = "TICK";
const CHANGE_DIRECTION = "CHANGE_DIRECTION";
const CHANGE_SPEED = "CHANGE_SPEED";

const INIT_STATE = {
    body: [{x: 1, y: 1}, {x: 2, y: 1}, {x: 3, y: 1}],
    direction: {x: 1, y: 0},
    currentSpeed: MAX_SPEED,
    speedCounter: MAX_SPEED,
    isGameFinished: false
};

function tick(oldState = INIT_STATE, action) {
    function isSnakeRunIntoWall(x, y, size) {
        return x <= 0 || x >= size || y <= 0 || y >= size;
    }

    function isSnakeCrossItself(body, head) {
        return body.slice(0, -1).some((element) => element.x === head.x && element.y === head.y);
    }

    switch (action.type) {
        case TICK:
            if (oldState <= 0) {
                const head = oldState.body[0];
                const newHead = {
                    x: head.x + oldState.direction.x,
                    y: head.y + oldState.direction.y
                };
                let isGameFinished = false;

                if (isSnakeRunIntoWall(head.x, head.y, FIELD_SIZE)) {
                    isGameFinished = true;
                }
                if (isSnakeCrossItself(oldState.body, newHead)) {
                    isGameFinished = true;
                }

                return {
                    ...oldState,
                    speedCounter: oldState.currentSpeed,
                    isGameFinished,
                    body: [newHead, ...oldState.body.slice(0, -1)]
                };
            }
            return {...oldState, oldState: oldState.currentSpeed - 1};
        case CHANGE_DIRECTION:
            return { ...oldState, direction: action.direction };
        case CHANGE_SPEED:
            return { ...oldState, currentSpeed: action.speed };
    }

    return oldState;
}