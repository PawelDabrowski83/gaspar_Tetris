package gaspar.coding;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

import static gaspar.coding.Controller.MARGIN_LEFT;
import static gaspar.coding.Controller.MARGIN_RIGHT;

public class Tetris extends Application {

    // Variables
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static final int XMAX = SIZE * 12;
    public static final int YMAX = SIZE * 24;
    public static final int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    private static final Pane groupe = new Pane();
    private static Form object;
    private static final Scene scene = new Scene(groupe, XMAX + 150, YMAX);
    public static int score = 0;
    private static int top = 0;
    private static boolean game = true;
    private static Form nextObj = Controller.makeRect();
    private static int linesNo = 0;
    private static final int GAME_SPEED = 300;

    // creating scene and start the game
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        for (int[] a : MESH) {
            Arrays.fill(a, 0);
        }

        // creating score and level text
        Line line = new Line(XMAX, 0, XMAX, YMAX);
        Text scoreText = new Text("Score: ");
        decorateText(scoreText, 20, 50, Color.BLACK);
        Text level = new Text("Lines: ");
        decorateText(level, 20, 100, Color.GREEN);
        groupe.getChildren().addAll(scoreText, line, level);

        // Creating first block and stage
        Form a = nextObj;
        groupe.getChildren().addAll((Node) a.a, (Node)a.b, (Node)a.c, (Node)a.d);
        moveOnKeyPressed(a);
        object =  a;
        nextObj = Controller.makeRect();
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();

        // Timer
        Timer fall = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                Platform.runLater(() -> {
                    if (isTouchingTopBoundary(object)){
                        top++;
                    } else {
                        top = 0;
                    }
                    if (top == 4) {
                        // GAME OVER
                        Text over = new Text("GAME OVER");
                        decorateText(over, 70, 250, Color.RED);
                        over.setX(10);
                        groupe.getChildren().add(over);
                        game = false;
                    }

                    if (top == 15) {
                        System.exit(0);
                    }

                    if (game) {
                        moveDown(object);
                        scoreText.setText(String.format("Score: %d", score));
                        level.setText(String.format("Lines: %d", linesNo));
                    }
                });
            }

        };
        fall.schedule(task, 0, GAME_SPEED);

    }

    private boolean isTouchingTopBoundary(Form form) {
        for (Square square : form.getBlocks()) {
            if (square.getY() == 0) {
                return true;
            }
        }
        return false;
    }

    private void decorateText(Text text, int fontSize, int height, Color color) {
        String style = String.format("-fx-font: %d arial;", fontSize);
        text.setStyle(style);
        text.setY(height);
        text.setX(XMAX + 5);
        text.setFill(color);
    }

    private void moveOnKeyPressed(Form form) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT -> Controller.moveRight(form);
                case DOWN -> {
                    moveDown(form);
                    score++;
                }
                case LEFT -> Controller.moveLeft(form);
                case UP -> moveTurn(form);
            }
        });
    }

    private void moveTurn(Form form) {
//        Square a = form.a;
//        Square b = form.b;
//        Square c = form.c;
//        Square d = form.d;

        int[] vectors;
        int[] center = findRotationCenter(form);

        boolean isMoveAllowed = form.canBeRotated();
        if (form.canBeRotated()) {
            for (Square square : form.getBlocks()) {
                vectors = square.findMoveVectors(center);
                if (!checkedForBoundaries(square, vectors[0], vectors[1])) {
                    isMoveAllowed = false;
                }
            }
        };

        if (isMoveAllowed) {
            for (Square square : form.getBlocks()) {
                square.rotateBlock(square.findMoveVectors(center));
            }
        }

    }

    private int[] findRotationCenter(Form form) {
        switch(form.getName()) {
            case "s", "t" -> {
                return form.b.getPosition();
            }
            case "z" -> {
                return form.a.getPosition();
            }
            case "i", "l", "j" -> {
                return form.c.getPosition();
            }
            default -> {
                return new int[]{0, 0};
            }
        }
    }



    private void removeRows() {
        List<Node> rects = new ArrayList<>();
        List<Integer> lines = new ArrayList<>();
        List<Node> newRects = new ArrayList<>();
        int full = 0;
        // check which line is full
        for (int i = 0; i < MESH[0].length; i++) {
            for (int[] mesh : MESH) {
                if (mesh[i] == 1) {
                    full++;
                }
                if (full == MESH.length) {
                    lines.add(i);
                }
            }
            full = 0;
        }

        // deleting the row
        if (lines.size() > 0) {
            do {
                for (Node node : Tetris.groupe.getChildren()) {
                    if (node instanceof Square) {
                        rects.add(node);
                    }
                }
                score += 50;
                linesNo++;
                // deleting block on row
                for (Node node : rects) {
                    Square a = (Square) node;
                    if (a.getY() == lines.get(0) * SIZE) {
                        MESH[a.getMeshXPosition()][a.getMeshYPosition()] = 0;
                        Tetris.groupe.getChildren().remove(node);
                    } else {
                        newRects.add(node);
                    }
                }

                for (Node node : newRects) {
                    Square a = (Square) node;
                    if (a.getY() < lines.get(0) * SIZE) {
                        MESH[a.getMeshXPosition()][a.getMeshYPosition()] = 0;
                        a.setY(a.getY() + SIZE);
                    }
                }
                    lines.remove(0);
                    rects.clear();
                    newRects.clear();

                for (Node node : Tetris.groupe.getChildren()) {
                    if (node instanceof Square) {
                        rects.add(node);
                    }
                }

                for (Node node : rects) {
                    Square a = (Square) node;
                    try {
                        MESH[a.getMeshXPosition()][a.getMeshYPosition()] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // nothing
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
        }
    }



    public void moveDown(Form form) {
        // moving if down is full
        if (checkForHittingBottom(form) || isBelowOccupied(form)) {
            for (Square square : form.getBlocks()) {
                MESH[square.getMeshXPosition()][square.getMeshYPosition()] = 1;
            }
            removeRows();

            // creating new block and adding it to the scene
            Form a = nextObj;
            object = a;
            nextObj = Controller.makeRect();
            for (Square square : a.getBlocks()) {
                groupe.getChildren().add((Node) square);
            }
            moveOnKeyPressed(a);
        }

        // Moving one block down if down is not full
        if (checkIfNoBlockBelow(form)) {
            for (Square square : form.getBlocks()) {
                square.stepDown(MOVE);
            }
        }
    }
    private boolean checkIfNoBlockBelow(Form form) {
        for (Square square : form.getBlocks()) {
            if (square.getMeshYPosition() < MESH[0].length) {
                if (MESH[square.getMeshXPosition()][square.getMeshYPosition() + 1] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkForHittingBottom(Form form) {
        for (Square square : form.getBlocks()) {
            if (square.getY() == YMAX - SIZE) {
                return true;
            }
        }
        return false;
    }

    private boolean isBelowOccupied(Form form) {
        for (Square square : form.getBlocks()) {
            if (MESH[square.getMeshXPosition()][square.getMeshYPosition() + 1] == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkedForBoundaries(Square square, int x, int y) {
        boolean yb = false;
        boolean xb = false;
        if (x >= 0) {
            xb = square.getX() + x * MOVE <= MARGIN_RIGHT;
        }
        if (x < 0) {
            xb = square.getX() + x * MOVE >= MARGIN_LEFT;
        }
        if (y >= 0) {
            yb = square.getY() - y * MOVE > 0;
        }
        if (y < 0) {
            yb = square.getY() + y * MOVE < YMAX;
        }
        boolean isEmpty = MESH[square.getMeshXPosition() + x][square.getMeshYPosition() - y] == 0;

        return xb && yb && isEmpty;
    }




}
