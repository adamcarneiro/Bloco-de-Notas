/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

//Bibliotecas
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Window;

//Classes

/**
 *
 * @author Adamastor Chimalange
 */
public class MainFXMLController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private MenuItem wordWrap;

    String fileAddress;
    String fileName=null;
    File xeiro;
    boolean wordWarnOn=false;
    Font arial, timesNewRoman, comicSansMs,msGothic;

    @FXML
    private void criarNovoFicheiro(){
        File_Functions.newFile(textArea);
    }
    
    @FXML
    private void openFile(ActionEvent event){
        /*String fileAddress;
        String fileName;*/
        FileChooser filechooser=new FileChooser();
        Window ownerWindow=null;
       /*ownerWindow.centerOnScreen();*/
       filechooser.setTitle("Abrir Ficheiro");
        File ficheiro = filechooser.showOpenDialog(null);
        xeiro=ficheiro;
        if(ficheiro.length()!=0){

            fileAddress=ficheiro.getPath();
            fileName=ficheiro.getName();
            Notepad.janela.setTitle(fileName);
            //Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            //stage.setTitle(fileName);
            //System.out.println(fileAddress+" "+fileName);
        }else{
            System.out.println("Qual e aidea");
        }
        
        try{
            try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
                textArea.setText("");
                String line;
                while((line=br.readLine())!=null){
                    textArea.appendText(line + "\n");
                }
            }
        }catch(IOException e){
            System.out.println("FILE NOT OPENED");
            e.printStackTrace();
        }
    }
    
    @FXML
    public void saveAs(){
        //String fileName;
        String fileAddress;
        Window ownerWindow = null;
        FileChooser filechooser=new FileChooser();
        filechooser.setTitle("Save As");
        File file=filechooser.showSaveDialog(null);
        xeiro=file;
        if(file.length()!=0){
            System.out.println("Olha Ainda.");
        } else {
            fileName=file.getName();
            Notepad.janela.setTitle(fileName+ "- Notepad");
        }
        
        try{
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(textArea.getText());
            }
        }catch(IOException e){
           e.printStackTrace();
        }
    }

//Adicionei esse metodo
    @FXML
    public void save(){
        try {
            if (fileName == null) {
                if(xeiro==null){
                    saveAs();
                }
            } else {
                try {
                    FileWriter fileWriter = new FileWriter(xeiro);
                    fileWriter.write(textArea.getText());
                    Notepad.janela.setTitle(fileName + " - Notepad");
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void exit(){
        System.exit(0);
    }
    @FXML
    public void wordWrap(){
            if(!wordWarnOn){
                wordWarnOn=true;
                textArea.setWrapText(true);
                wordWrap.setText("Word Warp: On");
            }else if (wordWarnOn){
                wordWarnOn=false;
                textArea.setWrapText(false);
                wordWrap.setText("Word Warp: Off");
            }
    }

    public void creatFont(int sizeFont){
        arial=new Font("Arial",sizeFont);
        timesNewRoman=new Font("Times News Roman",sizeFont);
        comicSansMs =new Font("Comic Sans Ms ",sizeFont);
    }

    @FXML
    public void setNumber(Event event){
        if (event.getSource().toString().contains("font8")) {
            //creatFont(8);
            textArea.setFont(Font.font(8));
        } else if (event.getSource().toString().contains("font12")) {
            //creatFont(12);
            textArea.setFont(Font.font(12));
        } else if (event.getSource().toString().contains("font16")) {
            //creatFont(16);
            textArea.setFont(Font.font(16));
        } else if (event.getSource().toString().contains("font24")) {
            //creatFont(24);
            textArea.setFont(Font.font(24));
        } else if (event.getSource().toString().contains("font28")) {
            //creatFont(28);
            textArea.setFont(Font.font(28));
        }
    }
    @FXML
    public void setFont(Event event){
        if (event.getSource().toString().contains("arialFont")) {
            textArea.setFont(Font.font("Arial"));
        } else if (event.getSource().toString().contains("cosmicSamFont")) {
           textArea.setFont(Font.font("Cosmic Sam"));
        } else if (event.getSource().toString().contains("timesNewFont")) {
            textArea.setFont(Font.font("Times New Roman"));
        } else if (event.getSource().toString().contains("msGothicFont")){
            textArea.setFont(Font.font("MS Gothic"));
        }

    }
    @FXML
    public void changeColor(Event event){
        String color;
        if (event.getSource().toString().contains("menuItemBlack")){
            //color="Black";
            System.out.println("Black");
            textArea.lookup(".content").setStyle("-fx-background-color: #ACACAC; ");
            textArea.setStyle("-fx-text-fill: white");


            //textArea.setTextFormatter();
        }else {
            System.out.println("White");
            textArea.lookup(".content").setStyle("-fx-background-color: white");
            textArea.setStyle("-fx-text-fill: black");
        }

    }

    public void organiza(){
        wordWrap();
        textArea.setFont(Font.font("Times New Roman",16));
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //wordWrap();
        organiza();
    }    
    
}
