package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Corso;
import bean.MatrMatrC;
import bean.Studente;

public class Dao {
	
	public boolean isMatricolaPresente(int matricola){
		String query="select *  from studente s  where s.matricola=?";
		Connection conn = DBConnect.getConnection();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,  matricola);
			ResultSet res = st.executeQuery();
			if(res.next()){
				return true;
			} else {
				return false;
			}
		}catch(SQLException e ){
			e.printStackTrace();
			return false;
		}
	}
	
		public List<Corso> corsiDelloStudente(int matricola){
		String query="select distinct c.codins, c.crediti, c.nome, c.pd  from corso c , iscrizione i  where i.matricola=? and i.codins=c.codins";
		Connection conn = DBConnect.getConnection();
		try{
			List<Corso> corsi = new LinkedList<>();
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,  matricola);
			ResultSet res = st.executeQuery();
			while(res.next()){
			Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
			corsi.add(c);
			}
			conn.close();
			return corsi;			
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
		
		
		public List<Studente> getAllStudenti(){
			String query="select distinct * from studente ";
			Connection conn = DBConnect.getConnection();
			try{
				List<Studente> alls = new LinkedList<>();
				PreparedStatement st = conn.prepareStatement(query);
				ResultSet res = st.executeQuery();
				while(res.next()){
				Studente s = new Studente(res.getInt("matricola"), res.getString("cognome"), res.getString("nome"), res.getString("cds"));
				alls.add(s);
				}
				conn.close();
				return alls;			
			}catch(SQLException e ){
				e.printStackTrace();
				return null;
			}
		}
		
		
		public List<MatrMatrC> getGrafo(){  //ok
			String query="  select distinct i1.matricola as  matricola1, i2.matricola as matricola2 , count(i1.codins) as numCorsi  "
					+ "     from iscrizione i1, iscrizione i2  "
					+ "     where i1.codins=i2.codins   "
					+ "     and i1.matricola<>i2.matricola  "
					+ "     group by i1.matricola, i2.matricola   order by numCorsi desc";
			Connection conn = DBConnect.getConnection();
			try{
				List<MatrMatrC> grafo = new LinkedList<>();
				PreparedStatement st = conn.prepareStatement(query);
				ResultSet res = st.executeQuery();
				while(res.next()){
					MatrMatrC g  = new MatrMatrC(res.getInt("matricola1"), res.getInt("matricola2"), res.getInt("numCorsi"));
				    grafo.add(g);
				}
				conn.close();
				System.out.println(grafo);
				return grafo;			
			}catch(SQLException e ){
				e.printStackTrace();
				return null;
			}
		}
		
		public static void main(String [] args){
			Dao dao = new Dao();
			dao.getGrafo();
		}
	
		public List<Corso> getAllCorsi(){
			String query="select distinct * from corsi ";
			Connection conn = DBConnect.getConnection();
			try{
				List<Corso> corsiAll = new LinkedList<>();
				PreparedStatement st = conn.prepareStatement(query);
				ResultSet res = st.executeQuery();
				while(res.next()){
					Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				    corsiAll.add(c);
				}
				conn.close();
				return corsiAll;			
			}catch(SQLException e ){
				e.printStackTrace();
				return null;
			}
		}


		public List<Studente> getIscrittiByCorso(String codiceCorso){
			String query="select distinct s.matricola, s.cognome, s.nome, s.CDS   "
					+ "from studente s , iscrizione i   "
					+ "where i.codins=? and i.matricola=s.matricola";
			Connection conn = DBConnect.getConnection();
			try{
				List<Studente> iscritti = new LinkedList<>();
				PreparedStatement st = conn.prepareStatement(query);
				st.setString(1,  codiceCorso);
				ResultSet res = st.executeQuery();
				while(res.next()){
				Studente s = new Studente(res.getInt("matricola"), res.getString("cognome"), res.getString("nome"), res.getString("cds"));
				iscritti.add(s);
				}
				conn.close();
				return iscritti;			
			}catch(SQLException e ){
				e.printStackTrace();
				return null;
			}
		}
}
