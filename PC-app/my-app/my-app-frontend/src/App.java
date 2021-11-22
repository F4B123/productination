import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;



public class App extends Application implements Initializable{

//main
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Prototipo.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene= new Scene(root);
        primaryStage.setTitle("Kenja Time");
        primaryStage.setScene(scene);
        primaryStage.show();
        initialize();

    }

//
    ListaDePaginas paginas;
    boolean pomodore=false;
    String actual=null;
    String namae=null;
    int mins=0,secs=0;
    boolean detenido=true;
    Timer temporizador;
    @FXML
    ObservableList<String> lista;


//añadidos ----------------------------------------------------------------------------------------------------------------------
    @FXML
    private WebView webView;
    @FXML
    private TextField textField;

    private WebEngine engine;
    private WebHistory history;
    private String homePage;
    private double webZoom;


    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		engine = webView.getEngine();
		homePage = "www.google.com";
		textField.setText(homePage);
		webZoom = 1;
		loadPage();
	}

    public void loadPage() {
		
		//engine.load("http://www.google.com");
		//engine.load("http://"+textField.getText());
        engine.load("file:/" + getLastModified("C:/Users/F4B/Documents/Productividad/testoutput"));
	}

    public void refreshPage() {
		
		engine.reload();
	}
	
	public void zoomIn() {
		
		webZoom+=0.25;
		webView.setZoom(webZoom);
	}
	
	public void zoomOut() {
		
		webZoom-=0.25;
		webView.setZoom(webZoom);
	}
	
	public void displayHistory() {
		
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		
		for(WebHistory.Entry entry : entries) {
			
			//System.out.println(entry);
			System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
		}
	}
	
	public void back() {
		
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(-1);
		
		textField.setText(entries.get(history.getCurrentIndex()).getUrl());
	}
	
	public void forward() {
		
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(1);
		
		textField.setText(entries.get(history.getCurrentIndex()).getUrl());
	}
	
	public void executeJS() {
		
		engine.executeScript("window.location = \"https://www.youtube.com\";");
	}
//  ----------------------------------------------------------------------------------------------------------------------

public static String getLastModified(String directoryFilePath)
{
    File directory = new File("C:/Users/F4B/Documents/Productividad/testoutput");
    File[] files = directory.listFiles(File::isFile);
    long lastModifiedTime = Long.MIN_VALUE;
    File chosenFile = null;

    if (files != null)
    {
        for (File file : files)
        {
            if (file.lastModified() > lastModifiedTime)
            {
                chosenFile = file;
                lastModifiedTime = file.lastModified();
            }
        }
    }
    
    return chosenFile.getPath();
}




// ----------------------------------------------------------------------------------------------------------------------

    ObservableList<String> iniciaLista(){
        lista=FXCollections.observableArrayList();
        Scanner sc;
        try {
            sc = new Scanner(new File("bloqueadores"));
            
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                lista.addAll(input);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("a");
        }
        
        lista.add("Nueva lista");
        System.out.println("uwu");
        return lista;
        
    }
    
    @FXML
    private Button comenzarBloqueo;

    @FXML
    private Label minutos;

    @FXML
    private Label segundos;

    @FXML
    private CheckBox pomodoro;

    @FXML
    public ChoiceBox<String> listaDePaginas=new ChoiceBox<>();
    
    @FXML
    private void initialize(){
        listaDePaginas.setItems(iniciaLista());
        
    }

    @FXML
    private TextField tiempoTabajo;

    @FXML
    private TextField tiempoDescanso;

    @FXML
    private TextArea contenidoPagina;

    @FXML
    private Button guardarPagina;

    @FXML
    private Button canelarPagina;

    @FXML
    private Button detenerBloqueo;

    @FXML
    private Button confirmation;

    @FXML
    private TextField nuevoNombre;

    @FXML
    private Button kether;

    @FXML
    void vinah(ActionEvent event) throws InterruptedException, IOException {
        namae=nuevoNombre.getText();
        paginas=new ListaDePaginas(namae);
        paginas.anadir("");
        lista.add(namae);
        listaDePaginas.setItems(lista);
        listaDePaginas.setValue(namae);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader3=new FXMLLoader(getClass().getResource("derrota.fxml"));
        Parent root3 = fxmlLoader3.load();
        Scene scene3= new Scene(root3);
        Stage stage3=new Stage();
        stage3.setTitle("Nueva Lista");
        stage3.setScene(scene3);
        stage3.show();
        /*
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠔⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣦⣀⠀⠀⢰⡟⠁⠉⣷⡶⠟⠻⣦⣤⠞⢃⣿⣀⣀⣀
        ⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⣷⣄⣨⣷⣦⣄⠉⡀⠀⣀⣾⠇⠀⠘⠋⠉⠀⠉
        ⠀⠀⠀⠀⠀⠀⢀⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⢠⠖⣿⣿⠀⠙⢿⣷⡶⠿⠿⢿⣄⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⢦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡟⠉⠻⣄⠈⠛⠻⢶⣖⡙⠁⠀⠀⠀⣿⠒⠚⠉⠉⠀⠀
        ⠀⠀⠀⢀⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡏⠑⢄⠀⠈⠛⢶⣤⣀⠙⠿⣦⣄⢀⣼⣿⡀⠀⠀⠀⠀⠀
        ⠀⠀⣠⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢷⡀⠀⠑⢄⠀⠀⠈⠉⢳⡄⠈⠛⢿⡏⠈⣧⣠⣤⣤⠤⣀
        ⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣧⡀⠀⠀⠀⠀⢀⣠⡀⠀⠘⢷⡄⠀⠀⠑⠀⠀⠀⠀⢿⡄⠀⠸⣧⡀⠉⠁⠀⣹⠆⠀
        ⠁⠀⠀⠀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡄⠀⠀⠀⢸⣇⠉⠳⢦⣌⡻⢦⠀⠀⠀⠀⢀⠀⠈⢿⡄⠀⣿⣧⣠⣤⡾⠏⠀⠀
        ⣰⠁⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣄⠀⠀⠀⠙⣧⣀⠀⠉⠛⠷⣷⡀⠀⠀⠀⢧⠀⠈⠷⠀⢻⠻⣟⠉⢦⠀⠀⢀
        ⠇⠀⠀⠀⢠⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣆⠀⠀⠀⠈⠛⢷⣄⠀⠀⠙⠷⣤⡀⠀⠈⠀⠀⠀⠀⠀⠃⠘⢷⡸⡆⠰⣯
        ⠀⠀⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣆⠀⠀⠀⠀⠀⠙⢷⣄⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⣿⠀⠀
        ⠀⠀⠀⠀⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣆⠀⠀⠀⠀⠀⠀⠹⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⠀⠀
        ⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣆⠀
        ⠀⠀⠀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⡀⠀⠀⠀⠀⠀⠹⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡀
        ⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠘⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⠀⠀⠀⠀⠀⠀⢻⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇
        ⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡄⠙⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣇⠀⠀⠀⠀⠀⠀⠙⢿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇
        ⠀⠀⢠⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠘⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡄⠀⠀⠀⠀⠀⠀⠀⠘⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣇
        ⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠈⢻⣄⠀⠀⢀⣀⣤⣤⡶⠀⠀⠀⠀⠀⠘⣧⠀⠀⠀⠀⠀⠀⠀⠀⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻
        ⠀⠀⠸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡷⣤⢼⣧⣤⣤⣤⣶⡿⢿⣿⠛⠋⠉⠁⠀⠀⠀⠀⠀⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈
        ⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠙⠷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣧⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⠤⠤⠤⣿⠾⣷⠾⠛⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠷⣦⣄⡀⠀⠀⠀⠀⠀⢿⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⢻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢻⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠷⢶⣤⣤⣀⣸⡆⢰⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠸⣇⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⣿⠀⠀⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣤⣶⣶⣶⣾⣿⣿⡿⠿⠿⠿⡇⣸⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⢿⡄⠀⠀⠀⠀⠀⠀⠸⡄⠀⠀⠀⢹⠀⠀⠈⢻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡏⠀⠀⠀⠠⣴⣿⠿⢿⡟⠛⠋⠉⠉⠉⠉⠻⣷⣄⠀⠀⠀⣇⣿⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⡀⠀⠀⠀⠘⣧⠀⠀⠀⠀⠀⠀⠀⢻⡄⢀⣠⣼⣧⣴⣶⣶⣿⣦⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠁⠀⠀⠀⠀⠉⠀⠀⣼⡇⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⠀⠀⣿⡇⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣇⠀⠀⠀⠀⢹⣆⠀⠀⠀⠀⠀⠀⠀⠻⣯⠉⠉⣿⠁⣿⡏⠈⠙⢿⣯⡉⠀⣄⠀⠀⠀⠀⠀⢰⡀⢠⠇⠀⠀⠀⠀⠀⠀⠀⠀⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡇⢠⡿⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣿⠀⠀⠀⠀⠀⢻⣆⠀⠀⠀⠀⠀⠀⡇⠈⠳⣤⡘⣆⢿⣧⠀⠀⠀⠈⠛⢦⣼⣟⠶⠶⠶⠶⠶⠛⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣄⠀⠀⠀⠀⠀⠀⠀⣀⣿⢃⣾⠁⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⢸⡇⠀⠀⠀⠀⠀⡿⣦⠀⠀⠀⠀⠀⣇⡀⠀⠈⠉⠛⠙⣿⣆⠀⠀⠀⠀⠀⠈⢻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠷⣶⣤⣤⣴⣶⠾⠛⣡⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠈⣿⠀⠀⠀⠀⢀⡇⠘⢷⣄⠀⠀⠀⣟⠻⣦⣀⠀⠀⠀⠈⠻⣷⣤⣀⣀⣀⣀⣀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠁⠀⠀⠀⠀⠀⢀⣠⣴⠟⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⢻⡆⠀⠀⠀⢸⣀⡠⢀⠙⠳⣄⠀⢿⠀⠈⠻⣦⡀⠀⠀⠀⠈⠉⠛⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⠷⢦⣤⣤⣤⣴⡶⠞⠛⠉⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠸⣷⠀⠀⠀⠀⠀⠀⠘⡄⠀⠈⠙⠚⠇⠀⠀⠈⠻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠟⠁⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⣿⡀⠀⠀⠀⠀⢰⠀⢷⠀⠀⠀⠀⠀⠀⠀⣴⠆⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣀⣀⣀⣀⣀⡤⠶⠋⠉⠉⠉⠉⠹⣦⡀⠀⠀⠀⠀⢀⣤⣾⢿⠁⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⢹⡇⠀⠀⠀⢀⣿⠀⢸⣧⠀⠀⠀⠀⠀⢰⠇⠸⡄⠀⠻⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣏⠉⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⡄⢀⣠⣶⡿⣻⡷⡿⠀⠀⡆⠀⠀⠀⠀⠀⢹⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⢸⣧⠀⠀⢠⡾⢹⡀⡾⠹⣆⠀⠀⠀⠀⣾⠀⠀⢳⡄⠀⠘⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣽⣷⣿⢻⣿⣿⣿⣿⠇⠀⣀⡇⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⣿⠀⣰⠟⠀⠘⣧⡇⠀⢻⣆⠀⠀⠀⣿⠀⣠⡾⠻⣦⡀⡇⠈⠙⠻⢶⣦⣤⣤⣀⣀⣀⣀⣘⣷⣄⣀⣀⣀⣀⣀⣀⣀⣤⣴⣾⣿⣿⣿⣭⣍⣻⣿⡅⣽⣿⡴⢿⣽⠇⠀⠀⠀⠀⠀⠀⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⣿⡼⠃⠀⠀⠀⠙⠇⠀⢸⡟⣧⡀⠀⣿⡟⠯⣄⡀⣿⠙⠃⠀⠀⠀⠀⠀⠈⣩⡉⠛⣿⢿⡟⠛⣿⠛⢻⡛⣿⠛⢻⣿⠛⠉⠉⠈⠁⣉⣽⣿⢟⣿⠷⡶⠛⣧⣨⡿⠀⠀⠀⠀⠀⠀⢀⣽⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠈⣻⣤⣿⠉⠳⣮⣻⣧⣴⢳⣄⠀⠀⠀⠀⠀⡇⠙⢦⣽⣿⣷⡀⣿⠀⢸⡇⢿⡄⢸⣿⡄⢀⣠⣤⣾⠟⢋⣴⣿⠏⣀⣷⠀⢸⡿⠁⠀⠀⢀⣀⣤⣾⠿⠛⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣷⠞⠁⠀⠋⠀⠀⠈⠻⣦⡻⣷⡝⢷⣄⡀⠀⠀⢻⡄⠀⠈⠉⠻⣿⡟⠀⠀⣿⡜⢿⣄⠻⣿⣟⣉⣥⣤⣾⡿⣿⣿⡾⣿⡿⣶⡿⣥⣴⣶⣾⠿⠛⠋⢀⣠⡶⠿⣿⡀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡿⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⡘⢯⣄⠙⠛⠷⠶⠬⠿⠀⠀⠀⢰⡟⠀⠀⠀⠘⣷⡀⠹⣿⣿⣿⠿⢻⡟⠁⣴⡟⠁⠀⣀⡀⠘⢷⣬⡇⠀⢿⣆⣠⡶⠟⠉⣀⣴⣿⣷⡀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⠘⣿⡇⠀⠀⠀⠀⠀⠀⠀⢠⡿⠁⠀⠀⠀⠀⠘⢿⣄⠈⠉⣀⣤⣿⡁⢀⣿⠃⠀⠀⣿⡇⠀⠀⠻⣿⠀⢸⣿⢁⣠⣴⡾⠟⠋⠀⠈⠻⢦⣤⣤⣤⡴⠾
          */
    }

    @FXML
    private void activarPomodoro(ActionEvent event) {
        pomodore=!pomodore;
        System.out.println(pomodore);
    }

    @FXML
    private void cancelar(ActionEvent event) throws Exception {
        String hod =listaDePaginas.getValue();
        contenidoPagina.setText(new Bloqueador().fileToString(hod));
    }

    @FXML
    private void detener(ActionEvent event) throws Exception {
        paginas=new ListaDePaginas(listaDePaginas.getValue());
        paginas.desbloquear();
        System.out.println("A");
        detenido=true;
    }

    @FXML
    private void empezarBloqueo(ActionEvent event) throws Exception {
        detenido=false;
        paginas=new ListaDePaginas(listaDePaginas.getValue());
        if(pomodore==false){
            paginas.bloquear();
        } else {
            try {
                int tiempo=Integer.parseInt(tiempoTabajo.getText());
                paginas.pomodoro(tiempo);
                mins=tiempo;
                if (tiempo<10){
                    minutos.setText("0"+Integer.toString(tiempo));
                } else{
                    minutos.setText(Integer.toString(tiempo));
                }
                secs=0;
                segundos.setText("00");
            } catch (Exception e) {
                System.out.println(e);
            }
            temporizador=new Timer();
            temporizador.scheduleAtFixedRate(new tempo(), 0, 1000);
            
        }
    }
    @FXML
    void actualizar(String nombre){
        contenidoPagina.setText("owo");
    }

    @FXML
    private void guardar(ActionEvent event) throws Exception {
        String hod =listaDePaginas.getValue();
        
        try (PrintWriter out = new PrintWriter(hod)) {
            out.println(contenidoPagina.getText());
        }
    }
    @FXML
    private void mostrarListas(MouseEvent event) {
        
    }

    @FXML
    void confirmacionProv(ActionEvent event) throws Exception {
        String hod =listaDePaginas.getValue();
        if (hod=="Nueva lista"){
            FXMLLoader fxmlLoader2=new FXMLLoader(getClass().getResource("emergente1.fxml"));
            Parent root2 = fxmlLoader2.load();
            Scene scene2= new Scene(root2);
            Stage stage2=new Stage();
            stage2.setTitle("Nueva Lista");
            stage2.setScene(scene2);
            stage2.show();
        } else {
            contenidoPagina.setText(new Bloqueador().fileToString(hod));
        }        
    }
    class tempo extends TimerTask{
        
        @Override
        public void run(){
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    if(detenido!=true){
                        if (secs==0){
                            mins--;
                            if (mins<10){
                                minutos.setText("0"+Integer.toString(mins));
                            } else {
                                minutos.setText(Integer.toString(mins));
                            }
                            secs=59;
                            segundos.setText(Integer.toString(secs));
                        } else {
                            secs--;
                            if (secs<10){
                                segundos.setText("0"+Integer.toString(secs));
                            } else {
                                segundos.setText(Integer.toString(secs));
                            }
                        }
                        
                    }
                    if ((mins==0 && secs==0)||detenido==true){
                        temporizador.cancel();
                        detenido=true;
                    }
                    System.out.println(detenido);
                    
                }
            });
        }
    }
}
