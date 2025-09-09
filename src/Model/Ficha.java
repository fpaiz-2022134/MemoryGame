package Model;

/**
 * Clase Ficha - Representa una casilla individual del tablero
 * Autor: Franco Paiz
 * Fecha: Septiembre 2025
 * Descripción: Modelo de una ficha del juego de memoria con su símbolo y estados
 */
public class Ficha {
    private String simbolo;
    private boolean estaRevelada;
    private boolean estaEmparejada;
    
    /**
     * Constructor - Inicializa una ficha con su símbolo
     * @param simbolo Emoji que representa el contenido de la ficha
     */
    public Ficha(String simbolo) {
        this.simbolo = simbolo;
        this.estaRevelada = false;
        this.estaEmparejada = false;
    }
    
    /**
     * Hace visible la ficha revelando su símbolo
     */
    public void revelar() {
        if (!estaEmparejada) {
            this.estaRevelada = true;
        }
    }
    
    /**
     * Oculta la ficha nuevamente
     */
    public void ocultar() {
        if (!estaEmparejada) {
            this.estaRevelada = false;
        }
    }
    
    /**
     * Marca la ficha como emparejada permanentemente
     */
    public void emparejar() {
        this.estaEmparejada = true;
        this.estaRevelada = true;
    }
    
    /**
     * Obtiene el símbolo de la ficha
     * @return String con el símbolo 
     */
    public String getSimbolo() {
        return simbolo;
    }
    
    /**
     * Verifica si la ficha está revelada
     * @return true si está visible, false si está oculta
     */
    public boolean isRevelada() {
        return estaRevelada;
    }
    
    /**
     * Verifica si la ficha ya fue emparejada
     * @return true si está emparejada, false en caso contrario
     */
    public boolean isEmparejada() {
        return estaEmparejada;
    }
}