package bowlingTable;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    @FXML
    VBox vboxTable;
    @FXML
    VBox vboxTable1;
    @FXML
    VBox vboxTable2;
    @FXML
    public void initialize() throws IOException {
        System.out.println("Witaj w BOWLING");
    }
    @FXML
    private List<String> readFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        String filePath = "";
        if (returnVal == JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getPath();
        }
        FileReader fileReader = new FileReader(filePath);
        BufferedReader inputText = new BufferedReader(fileReader);
        String line;
        List<String> lines = new ArrayList<>();
        try {
            int i = 0;
            while ((line= inputText.readLine()) != null){
               System.out.println(line);
               lines.add(line);
            }
       }catch (FileNotFoundException e){
           System.out.println("Brak pliku");
       }
       return lines;
    }
    @FXML
    private void loadTable() throws IOException {
        try {
            List<String> lines = readFile();
            boolean opisSuma = false;
            boolean opisPunkty = false;
            for (int i = 1; i < lines.size(); i+=2) {
                if (!opisSuma){
                    Label sumaLabel = new Label("Wynik");
                    sumaLabel.setStyle("-fx-padding: 5;" +
                            "-fx-border-style: solid inside;" +
                            "-fx-border-width: 1;" +
                            "-fx-border-insets: 2;" +
                            "-fx-border-radius: 2;" +
                            "-fx-border-color: blue;");
                    sumaLabel.setPrefWidth(50);
                    vboxTable1.getChildren().add(sumaLabel);
                    opisSuma = true;
                }
                HBox hboxTable = new HBox();
                int sum = 0;
                Label sumLabel = new Label();
                sumLabel.setStyle("-fx-padding: 5;" +
                        "-fx-border-style: solid inside;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-insets: 2;" +
                        "-fx-border-radius: 2;" +
                        "-fx-border-color: red;");
                for (int j = 0; j < lines.get(i).length(); j+=3) {
                    if (!opisPunkty){
                        Label punktyLabel = new Label("Punkty");
                        punktyLabel.setStyle("-fx-padding: 5;" +
                                "-fx-border-style: solid inside;" +
                                "-fx-border-width: 1;" +
                                "-fx-border-insets: 2;" +
                                "-fx-border-radius: 2;" +
                                "-fx-border-color: blue;");
                        punktyLabel.setPrefWidth(500);
                        vboxTable2.getChildren().add(punktyLabel);
                        opisPunkty = true;
                    }
                    int point = Integer.valueOf(lines.get(i).charAt(j)-48);
                    sum+=point;
                    Label pointLabel =new Label((point+""));
                    pointLabel.setStyle("-fx-padding: 5;" +
                            "-fx-border-style: solid inside;" +
                            "-fx-border-width: 1;" +
                            "-fx-border-insets: 2;" +
                            "-fx-border-radius: 2;" +
                            "-fx-border-color: red;");
                    hboxTable.getChildren().add(pointLabel);
                }
                sumLabel.setText(sum+"");
                sumLabel.setPrefWidth(50);
                vboxTable1.getChildren().add(sumLabel);
                vboxTable2.getChildren().add(hboxTable);
            }
            boolean opisZawodnik = false;
            for (int i = 0; i < lines.size(); i+=2) {
                if (!opisZawodnik){
                    Label zawodnikLabel = new Label("Zawodnik");
                    zawodnikLabel.setStyle("-fx-padding: 5;" +
                            "-fx-border-style: solid inside;" +
                            "-fx-border-width: 1;" +
                            "-fx-border-insets: 2;" +
                            "-fx-border-radius: 2;" +
                            "-fx-border-color: blue;");
                    zawodnikLabel.setPrefWidth(120);
                    vboxTable.getChildren().add(zawodnikLabel);
                    opisZawodnik = true;
                }
                Label nameLabel = new Label(lines.get(i));
                nameLabel.setStyle("-fx-padding: 5;" +
                        "-fx-border-style: solid inside;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-insets: 2;" +
                        "-fx-border-radius: 2;" +
                        "-fx-border-color: blue;");
                nameLabel.setPrefWidth(120);
                vboxTable.getChildren().add(nameLabel);
            }
        }catch (IOException e){
            System.out.println("Nie wybrałeś pliku !");
        }

    }
}
