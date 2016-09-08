import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

/**
 * Runs the Paint application
 * @author Aaron Friesen
 * @version 1.0
 */
public class PaintFX extends Application {

    private Tool currTool;

    @Override
    public void start(Stage stage) {

        BorderPane borderpane = new BorderPane();
        VBox menu = new VBox();
        borderpane.setLeft(menu);
        Canvas canvas = new Canvas(600, 700);
        Canvas bufferCanvas = new Canvas(600, 700);
        ColorPicker colorPicker = new ColorPicker();
        Color c = colorPicker.getValue();

        StackPane stack = new StackPane();
        borderpane.setCenter(stack);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        // GraphicsContext gcBuffer = bufferCanvas.getGraphicsContext2D();
        // stack.getChildren().addAll(canvas, bufferCanvas);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        HBox root = new HBox();

        Button pencilSelect = new Button("Pencil");
        pencilSelect.setOnAction(e ->
            { currTool = new Pencil(); });
        Button rectangleSelect = new Button("Rectangle");
        rectangleSelect.setOnAction(e ->
            { currTool = new RectDraw(); });
        Button eraser = new Button("Eraser");
        eraser.setOnAction(e ->
            { currTool = new Eraser(); });
        Button clear = new Button("Clear");
        clear.setOnAction(e ->
            {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, 600, 700);
            }
        );

        canvas.setOnMousePressed(e ->
            {
                gc.setStroke(colorPicker.getValue());
                currTool.onPress(e, gc);
            }
        );
        canvas.setOnMouseDragged(e -> { currTool.onDrag(e, gc); });
        canvas.setOnMouseReleased(e -> { currTool.onRelease(e, gc); });

        menu.getChildren().addAll(pencilSelect, rectangleSelect,
                                    eraser, colorPicker, clear);
        root.getChildren().addAll(borderpane, canvas);
        Scene scene = new Scene(root, 600, 700);

        stage.setTitle("Paint");
        stage.setScene(scene);
        stage.show();
    }
}