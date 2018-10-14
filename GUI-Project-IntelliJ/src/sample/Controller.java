package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import tech.kushan.JavaPracticalFile;
import tech.kushan.PracticalFile;

import java.io.File;
import java.io.IOException;

public class Controller {

    public Button Upload;
    public Button Home;
    public Button Help;
    public Button Information;

    public void button_upload(ActionEvent event) {
        Upload.setDisable(true);
        Main obj = new Main();
        Stage stage_set = obj.stage_give();


        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder Directory");

        File file = directoryChooser.showDialog(stage_set);

        if (file != null) {
            try {

                Stage myStage;
                Parent myRoot;

                Stage origStage = (Stage) ((Button) (event.getSource())).getScene().getWindow();

                myStage = origStage;

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("please_wait.fxml"));
                myRoot = FXMLLoader.load(getClass().getResource("please_wait.fxml"));

                Scene scene = new Scene(myRoot);
                myStage.setScene(scene);
                myStage.show();

                Upload.setDisable(false);

                FXMLLoader fxmlLoader1;

                // Validate Selected folder here
                PracticalFile javaFile = new JavaPracticalFile(file.getPath());

                if (javaFile.validateInput()) {

                    System.out.println("FFFFHERE");

                    javaFile.generate();

                    fxmlLoader1 = new FXMLLoader(getClass().getResource("ok.fxml"));
                } else {
                    System.out.println("FFFFHEREELSE");

                    fxmlLoader1 = new FXMLLoader(getClass().getResource("error.fxml"));
                }

                Parent OmyRoot = fxmlLoader1.load();

                Stage mainStage;
                mainStage = Main.parentWindow;
                mainStage.getScene().setRoot(OmyRoot);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Upload.setDisable(false);
        }

        // stage_set.close();


    }


    public void button_home(ActionEvent event1) throws IOException {

       /* Stage curstage = (Stage) Home.getScene().getWindow();
        curstage.close();*/
        ((Stage) (((Button) event1.getSource()).getScene().getWindow())).close();

        Platform.runLater(() -> {
            try {
                new Main().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


       /* FXMLLoader fxmlLoader_1 = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root_sample = (Parent) fxmlLoader_1.load();
        Scene scene = new Scene(root_sample);
        Stage stage = new Stage();

        scene.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });


        scene.setOnDragDropped(event -> {
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
                FXMLLoader fxmlLoader_2 = new FXMLLoader(getClass().getResource("please_wait.fxml"));
                Parent root_wait = (Parent) fxmlLoader_2.load();
                Stage stage1 = new Stage();
                Image anotherIcon = new Image("file:src\\sample\\Images\\icon.png");
                stage1.getIcons().add(anotherIcon);
                stage1.setTitle("Document Generation Automation");
                stage1.initStyle(StageStyle.DECORATED);
                stage1.setResizable(false);
                stage1.setScene(new Scene(root_wait));
                stage1.show();
                stage.close();

                FXMLLoader fxmlLoader_5 = new FXMLLoader(getClass().getResource("ok.fxml"));
                Parent root_ok = (Parent) fxmlLoader_5.load();
                Stage stage2 = new Stage();
                Image anotherIcon1 = new Image("file:src\\sample\\Images\\icon.png");
                stage2.getIcons().add(anotherIcon1);
                stage2.setTitle("Document Generation Automation");
                stage2.initStyle(StageStyle.DECORATED);
                stage2.setResizable(false);
                stage2.setScene(new Scene(root_ok));
                stage2.show();
                stage1.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Image anotherIcon = new Image("file:src\\sample\\Images\\icon.png");
        stage.getIcons().add(anotherIcon);
        stage.setTitle("Document Generation Automation");
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        Home.setDisable(false);
        Home.getScene().getWindow().hide();
        stage.show();*/


    }

    public void button_help(ActionEvent event) throws IOException {

        Upload.setDisable(true);
        Help.setDisable(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("help.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Image anotherIcon = new Image("file:src\\sample\\Images\\icon.png");
        stage.getIcons().add(anotherIcon);
        stage.setTitle("Document Generation Automation");
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(root1));
        stage.show();

        stage.setOnCloseRequest((WindowEvent event1) -> {
            Upload.setDisable(false);
            Help.setDisable(false);
        });

    }

    public void button_information(ActionEvent event) throws IOException {

        Upload.setDisable(true);
        Information.setDisable(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("information.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Image anotherIcon = new Image("file:src\\sample\\Images\\icon.png");
        stage.getIcons().add(anotherIcon);
        stage.setTitle("About Us");
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(root1));
        stage.show();


        Hyperlink link = (Hyperlink) root1.lookup("#link");
        link.setTooltip(new Tooltip("https://kushan02.github.io/-CHARUSAT-Document_Generation_Automation/"));


        link.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                String browserUrl = link.getTooltip().getText();
                Main.getHost().showDocument(browserUrl);
                event.consume();
            }
        });


        stage.setOnCloseRequest((WindowEvent event1) -> {
            Upload.setDisable(false);
            Information.setDisable(false);
        });

    }

}
