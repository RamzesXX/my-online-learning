package com.khanchych.friday;

import java.util.*;
import java.util.stream.Collectors;

enum Actions {Up, Down, Left, Right, Start}

enum Orientations {X, Y, Z}

class AllowedMovement {
    final int dX;
    final int dY;
    final Orientations toOrientation;

    public AllowedMovement(int dX, int dY, Orientations toOrientation) {
        this.dX = dX;
        this.dY = dY;
        this.toOrientation = toOrientation;
    }
}

class Position {
    int x;
    int y;
    Orientations orientation;

    public Position(int x, int y, Orientations orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public Position(int x, int y) {
        this(x, y, Orientations.Z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y && orientation == position.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, orientation);
    }
}

class VisitInfo {
    final Position fromPosition;
    final Actions action;

    public VisitInfo(Position fromPosition, Actions action) {
        this.fromPosition = fromPosition;
        this.action = action;
    }
}

public class RollBlockGame {
    private final static int BAR_SIZE = 3;
    private final static int ZERO = 0;
    private final static int ONE = 1;

    private final static Map<Orientations, Map<Actions, AllowedMovement>> allowedMovements = new HashMap<>();

    static {
        Map<Actions, AllowedMovement> movementsForOrientation;

        // from Z orientation movements
        movementsForOrientation = new HashMap<>();
        movementsForOrientation.put(Actions.Left, new AllowedMovement(-BAR_SIZE, ZERO, Orientations.X));
        movementsForOrientation.put(Actions.Right, new AllowedMovement(ONE, ZERO, Orientations.X));
        movementsForOrientation.put(Actions.Up, new AllowedMovement(ZERO, -BAR_SIZE, Orientations.Y));
        movementsForOrientation.put(Actions.Down, new AllowedMovement(ZERO, ONE, Orientations.Y));
        allowedMovements.put(Orientations.Z, movementsForOrientation);

        // from Y orientation movements
        movementsForOrientation = new HashMap<>();
        movementsForOrientation.put(Actions.Left, new AllowedMovement(-ONE, ZERO, Orientations.Y));
        movementsForOrientation.put(Actions.Right, new AllowedMovement(ONE, ZERO, Orientations.Y));
        movementsForOrientation.put(Actions.Up, new AllowedMovement(ZERO, -ONE, Orientations.Z));
        movementsForOrientation.put(Actions.Down, new AllowedMovement(ZERO, BAR_SIZE, Orientations.Z));
        allowedMovements.put(Orientations.Y, movementsForOrientation);

        // from X orientation movements
        movementsForOrientation = new HashMap<>();
        movementsForOrientation.put(Actions.Left, new AllowedMovement(-ONE, ZERO, Orientations.Z));
        movementsForOrientation.put(Actions.Right, new AllowedMovement(BAR_SIZE, ZERO, Orientations.Z));
        movementsForOrientation.put(Actions.Up, new AllowedMovement(ZERO, -ONE, Orientations.X));
        movementsForOrientation.put(Actions.Down, new AllowedMovement(ZERO, ONE, Orientations.X));
        allowedMovements.put(Orientations.X, movementsForOrientation);

    }

    private int fieldSizeX;
    private int fieldSizeY;
    private Map<Position, VisitInfo> visitTracker;

    RollBlockGame(int fieldSizeX, int fieldSizeY) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
    }

    private boolean isMovementPossible(Position currentPosition, AllowedMovement movement) {
        int newX = currentPosition.x + movement.dX;
        int newY = currentPosition.y + movement.dY;

        if (movement.toOrientation == Orientations.Z) {
            return newX >= 0 && newX < fieldSizeX &&
                    newY >= 0 && newY < fieldSizeY;
        }

        if (movement.toOrientation == Orientations.X) {
            return newX >= 0 && newX + BAR_SIZE < fieldSizeX &&
                    newY >= 0 && newY < fieldSizeY;
        }

        if (movement.toOrientation == Orientations.Y) {
            return newX >= 0 && newX < fieldSizeX &&
                    newY >= 0 && newY + BAR_SIZE < fieldSizeY;
        }

        return false;
    }

    private List<VisitInfo> pickPath(Position startPosition, Position finishPosition) {
        LinkedList<VisitInfo> path = new LinkedList<>();
        Position position = finishPosition;

        while (position != startPosition) {
            VisitInfo visitInfo = visitTracker.get(position);
            path.addFirst(visitInfo);
            position = visitInfo.fromPosition;
        }

        return path;
    }

    private List<VisitInfo> findPath(Position startPosition, Position finishPosition) {
        Queue<Position> positionsToProcess = new LinkedList<>();

        visitTracker = new HashMap<>();
        positionsToProcess.add(startPosition);
        visitTracker.put(startPosition, new VisitInfo(startPosition, Actions.Start));

        while (!positionsToProcess.isEmpty()) {
            Position currentPosition = positionsToProcess.remove();
            Map<Actions, AllowedMovement> availableMovements = allowedMovements.get(currentPosition.orientation);

            if (currentPosition.equals(finishPosition)) {
                return pickPath(startPosition, finishPosition);
            }

            for (Map.Entry<Actions, AllowedMovement> movementEntry : availableMovements.entrySet()) {
                AllowedMovement movement = movementEntry.getValue();

                if (isMovementPossible(currentPosition, movement)) {
                    Position newPosition = new Position(currentPosition.x + movement.dX, currentPosition.y + movement.dY, movement.toOrientation);

                    if (!visitTracker.containsKey(newPosition)) {
                        positionsToProcess.add(newPosition);
                        visitTracker.put(newPosition, new VisitInfo(currentPosition, movementEntry.getKey()));
                    }
                }
            }
        }

        return new LinkedList<>();
    }

    public String getPath(Position from, Position to) {
        return findPath(from, to).stream()
                .map(visitInfo -> visitInfo.action.toString())
                .collect(Collectors.joining(" -> "));
    }
}

