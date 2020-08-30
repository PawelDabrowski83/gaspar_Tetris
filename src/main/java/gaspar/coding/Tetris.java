package gaspar.coding;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class Tetris extends Application {

    // Variables
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static final int XMAX = SIZE * 12;
    public static final int YMAX = SIZE * 24;
    public static final int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    private static Pane groupe = new Pane();
    private static Form object;
    private static Scene scene = new Scene(groupe, XMAX + 150, YMAX);
    public static int score = 0;
    private static boolean game = true;
    private static Form nextObj = controller.makeRect();
    private static int linesNo = 0;

    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {

    }
}
