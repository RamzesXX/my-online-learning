let solver = {
    step: 0,
    solution: [
        "2 \n1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0",
        "3 \n1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0",
        "3 \n1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0",
        "3 \n1 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0",
        "3 \n1 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0",
        "3 \n1 2 1 1 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0",
        "3 \n1 2 1 1 0 0 0 0 0 0 0 0 0 0 0 0 2 2 0 0",
        "4 \n1 2 1 1 0 0 0 0 0 0 0 3 0 0 0 0 2 2 0 0",
        "4 \n1 2 1 1 0 0 0 0 0 0 0 3 0 0 0 0 2 2 0 0",
        "4 \n1 2 1 1 0 0 1 0 0 0 0 3 0 0 0 0 2 2 0 0",
        "4 \n1 2 1 1 0 0 1 1 0 0 0 3 0 0 0 0 2 2 0 0",
        "4 \n1 2 1 1 0 0 1 1 0 0 0 3 0 0 0 0 2 2 0 0",
        "4 \n1 2 1 1 3 0 1 1 0 0 0 3 0 0 0 0 2 2 0 0",
        "4 \n1 2 1 1 3 0 1 1 0 0 0 3 0 0 0 1 2 2 0 0",
        "4 \n1 2 1 1 3 0 1 1 0 0 0 3 0 0 0 1 2 2 0 0",
        "4 \n1 2 1 1 3 0 1 1 0 0 0 3 0 0 0 1 2 2 0 0",
        "4 \n1 2 1 1 3 0 1 1 0 0 0 3 0 2 0 1 2 2 0 0",
        "4 \n1 2 1 1 3 0 1 1 0 0 0 3 0 2 0 1 2 2 0 0",
        "4 \n1 2 1 1 3 0 1 1 0 1 0 3 0 2 0 1 2 2 0 0",
        "4 \n1 2 1 1 3 0 1 1 0 1 0 3 0 2 0 1 2 2 0 0",
    ]
};

function nextStep() {
    parseSolutionText(solver.solution[solver.step]);
    if (solver.solution.length - 1 > solver.step) {
        solver.step++;
    }

    return false;
}
function prevStep() {
    if (solver.step > 0) {
        solver.step--;
    }
    parseSolutionText(solver.solution[solver.step]);

    return false;
}