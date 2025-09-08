package Model;

public class Ficha {
	
	private String simbolo;
	private boolean estaRevelada;
	private boolean estaEmparejada;
	
	public Ficha(String simbolo){
		this.simbolo = simbolo;
		this.estaRevelada = false;
		this.estaEmparejada = false;
	}

	
	public void revelar(){
		estaRevelada = true;
	}
	
	public void ocultar() {
		estaRevelada = false;
	}
	
	public void emparejar() {
		estaEmparejada = true;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	
	public boolean isRevelada() {
		return this.estaRevelada;
	}
	
	public boolean isEmparejada() {
		return this.estaEmparejada;
	}
}

