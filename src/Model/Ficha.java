package Model;

public class Ficha {
	
	
	private String simbolo;
	private boolean estaRevelada;
	private boolean estaEmparejada;
	
	/**
     * Constructor que inicializa una ficha con su símbolo
	 * @param simbolo
	 */
	public Ficha(String simbolo){
		this.simbolo = simbolo;
		this.estaRevelada = false;
		this.estaEmparejada = false;
	}

	/**
	 * Hace que la ficha del usuario sea visible
	 */
	public void revelar(){
		if (!estaEmparejada) {
            this.estaRevelada = true;
        }
	}
	
	
	/**
	 * Oculta esta ficha
	 */
	public void ocultar() {
		if(!estaEmparejada) {
			this.estaRevelada = false;
		}
	}
	
	
	/**
	 * Marca la ficha como emparejada
	 */
	public void emparejar() {
		this.estaEmparejada = true;
		this.estaRevelada = true;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	
	
	/**
	 * Verifica si la ficha está revelada
	 * @return true cuando está revelada, false cuando no
	 */
	public boolean isRevelada() {
		return estaRevelada;
	}
	
	/**
	 * 
	  * Verifica si la ficha está emparejada
	 * @return true cuando está emparejada, false cuando no
	 */
	public boolean isEmparejada() {
		return estaEmparejada;
	}
}

