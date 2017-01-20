package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bean.Corso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCorsi;

    @FXML
    private Button btnSimili;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCorsi(ActionEvent event) {
    	txtResult.clear();
    	try{
    		int matricola = Integer.parseInt(txtMatricola.getText());
    		if(!m.isMatricolaPresente(matricola)){
    			txtResult.appendText("La matricola non esiste nel db!\n");
    			return;
    		}
//    		m.buildGraph();
//    		
//    		int peso = m.getStudentiColMax();
//    		txtResult.appendText("Num : "+ peso);
    		List<Corso> corsi = m.getCorsiStudente(matricola);
    		if(corsi.isEmpty()){
    			txtResult.appendText("Lo studente non è iscritto a nessun corso!\n");
    			return;
    		}
    		for(Corso c : corsi){
    			txtResult.appendText(c+ " \n");
    		}
    		
    		
    		
    	}catch(Exception e){
    		txtResult.appendText("Il formato non è valido!\n");
    		return;
    	}

    }

    @FXML
    void doSimili(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnSimili != null : "fx:id=\"btnSimili\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

    }
}

