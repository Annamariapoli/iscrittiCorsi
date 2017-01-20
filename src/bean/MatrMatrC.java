package bean;

public class MatrMatrC {
	
	private int matricola1;
	private int matricola2;
	private int numeroCorsi;
	
	
	public MatrMatrC(int matricola1, int matricola2, int numeroCorsi) {
		super();
		this.matricola1 = matricola1;
		this.matricola2 = matricola2;
		this.numeroCorsi = numeroCorsi;
	}
	public int getMatricola1() {
		return matricola1;
	}
	public void setMatricola1(int matricola1) {
		this.matricola1 = matricola1;
	}
	public int getMatricola2() {
		return matricola2;
	}
	public void setMatricola2(int matricola2) {
		this.matricola2 = matricola2;
	}
	public int getNumeroCorsi() {
		return numeroCorsi;
	}
	public void setNumeroCorsi(int numeroCorsi) {
		this.numeroCorsi = numeroCorsi;
	}
	
	
	public String toString(){
		String r ;
		r = matricola1+ " "+matricola2+" "+numeroCorsi+" \n";
		return r;
	}

}
