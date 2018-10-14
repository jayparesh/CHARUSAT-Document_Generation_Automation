package sample;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class Main extends Application {

    public static Stage parentWindow;

    private Stage stage_giving;

    protected static HostServices hostServices;

    public static HostServices getHost() {
        return hostServices;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        hostServices = getHostServices();

        parentWindow = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);


        /*scene.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });
*/
      /*  scene.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();

            boolean success = false;

            if (db.hasFiles()) {
                success = true;
                String filePath;
                for (File file : db.getFiles()) {
                    filePath = file.getAbsolutePath();
                    System.out.println(filePath);
                    //primaryStage.close();
                }

            }
            event.setDropCompleted(success);
            event.consume();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("please_wait.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                Image anotherIcon1 = new Image("file:src\\sample\\Images\\icon.png");
                stage.getIcons().add(anotherIcon1);
                stage.setTitle("Document Generation Automation");
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                stage.show();
                primaryStage.close();

                stage.close();
                FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("ok.fxml"));
                Parent root2 = (Parent) fxmlLoader1.load();
                Stage stage1 = new Stage();
                Image anotherIcon2 = new Image("file:src\\sample\\Images\\icon.png");
                stage1.getIcons().add(anotherIcon2);
                stage1.setTitle("Document Generation Automation");
                stage1.initStyle(StageStyle.DECORATED);
                stage1.setResizable(false);
                stage1.setScene(new Scene(root2));
                stage1.show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/


        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Image anotherIcon = new Image("file:src\\sample\\Images\\icon.png");
        primaryStage.getIcons().add(anotherIcon);
        primaryStage.setTitle("Document Generation Automation");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        stage_giving = primaryStage;
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    Stage stage_give() {
        return stage_giving;
    }

}
