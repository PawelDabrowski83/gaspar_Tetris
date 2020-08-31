package gaspar.coding;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class Tetris extends Application {

    // Variables
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static final int XMAX = SIZE * 12;
    public static final int YMAX = SIZE * 24;
    public static final int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    private static Pane groupe = new Pane();
    private static Form obj;
    private static Scene scene = new Scene(groupe, XMAX + 150, YMAX);
    public static int score = 0;
    private static boolean game = true;
    private static Form nextObj = controller.makeRect();
    private static int linesNo = 0;

    // creating scene and start the game
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        for (int[] a : MESH) {
            Arrays.fill(a, 0);
        }

        // creating score and level text
        Line line = new Line(XMAX, 0, XMAX, YMAX);
        Text scoretext = new Text("Score: ");
        scoretext.setStyle("-fx-font: 20 arial;");
        scoretext.setY(50);
        scoretext.setX(XMAX + 5);
        Text level = new Text("Lines: ");
        level.setStyle("-fx-font: 20 arial;");
        level.setY(100);
        level.setX(XMAX + 5);
        level.setFill(Color.GREEN);
        groupe.getChildren().addAll(scoretext, line, level);

        // Creating first block and stage
        Form a = nextObj;
        groupe.getChildren().addAll(a.a, a.b, a.c, a.d);
        moveOnKeyPressed(a);
        obj a;
        nextObj = Controller.makeRect();
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();

        // Timer
        Timer fall = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                Platform.runLater(new Runnable(){
                    public void run(){
                        if (obj.a.getY() == 0 || obj.b.getY() == 0 || obj.c.getY() == 0 || obj.d.getY() == 0){
                            top++;
                        } else {
                            top = 0;
                        }
                        if (top == 2) {
                            // GAME OVER
                            Text over = new Text("GAME OVER");
                            over.setFill(Color.RED);
                            over.setStyle("-fx-font: 70 arial;");
                            over.setY(250);
                            over.setX(10);
                            groupe.getChildren().add(over);
                            game = false;
                        }

                        if (top == 15) {
                            System.exit(0);
                        }

                        if (game) {
                            moveDown(obj);
                            scoretext.setText("Score: " + Integer.toString(score));
                            level.setText("Lines: " + Integer.toString(linesNo));
                        }
                    }
                });
            }

        };
        fall.schedule(task, 0, 300);

    }

    private void moveOnKeyPressed(Form form) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case RIGHT:
                        Controller.moveRight(form);
                        break;
                    case DOWN:
                        moveDown(form);
                        score++;
                        break;
                    case LEFT:
                        Controller.moveLeft(form);
                        break;
                    case UP:
                        moveTurn(form);
                        break;
                }
            }
        });
    }




}
