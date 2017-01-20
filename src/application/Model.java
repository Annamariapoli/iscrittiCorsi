package application;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import bean.Corso;
import bean.MatrMatrC;
import bean.Studente;
import db.Dao;

public class Model {

	private Dao dao = new Dao();
	private SimpleWeightedGraph<Studente , DefaultWeightedEdge> grafo ;
	
	public boolean isMatricolaPresente(int matricola){
		return dao.isMatricolaPresente(matricola);
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		List<Corso> corsi = dao.corsiDelloStudente(matricola);
		return corsi;
	}
	
	public List<Studente> getAllStudenti(){
		List<Studente> allS = dao.getAllStudenti();
		return allS;
	}
	
	public List<Corso> getAllCorsi(){
		List<Corso> allC= dao.getAllCorsi();
		return allC;
	}
	
	
	public List<Studente> getStudentiDelCorso(String codiceCorso){
		List<Studente> iscritti = dao.getIscrittiByCorso(codiceCorso);
		return iscritti;
	}
	
	public List<MatrMatrC> getDaoGrafo (){
		List<MatrMatrC> g = dao.getGrafo();
		return g ;
	}
	 
	public void buildGraph(){    
		grafo = new SimpleWeightedGraph<Studente , DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo,  getAllStudenti());
		List<MatrMatrC> listagrafo= getDaoGrafo();
		for(Studente s1 : grafo.vertexSet()){
			for(Studente s2 : grafo.vertexSet()){
				if(!s1.equals(s2)){
					int numeroCorsi = calcolaNumeroCorsi(s1, s2, listagrafo);
					if(numeroCorsi>0){
						Graphs.addEdge(grafo,  s1,  s2,  numeroCorsi);
					}
				}
			}
		}
		System.out.println(grafo.toString());
	}
	
	public  int calcolaNumeroCorsi(Studente s1, Studente s2, List<MatrMatrC> listagrafo) {
		for(MatrMatrC elemento : listagrafo){
			if(elemento.getMatricola1()==s1.getMatricola() && elemento.getMatricola2()==s2.getMatricola()){
				return elemento.getNumeroCorsi();
			}
		}
		return 0;
	}
	
	public int getStudentiColMax(){
		int max=0;
		for(DefaultWeightedEdge arco : grafo.edgeSet()){
			int peso  = (int) grafo.getEdgeWeight(arco);
			if(peso> max){
				max = (int) grafo.getEdgeWeight(arco);
			}
		}
		return max;
	}

	public static void main(String [] args){
		Model m = new Model();
		m.buildGraph();
	}
}


