package Model;

/**
 * Clase Jugador - Representa a cada participante del juego
 * Autor: Franco Paiz
 * Fecha: Septiembre 2025
 * Descripción: Modelo de jugador con estadísticas y datos personales
 */
public class Jugador {
    private String nombre;
    private int paresEncontrados;
    private int partidasGanadas;
    
    /**
     * Constructor - Inicializa un jugador con su nombre
     * @param nombre Nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.paresEncontrados = 0;
        this.partidasGanadas = 0;
    }
    
    /**
     * Incrementa el contador de pares encontrados en la partida actual
     */
    public void incrementarPares() {
        this.paresEncontrados++;
    }
    
    /**
     * Incrementa el contador de partidas ganadas en la sesión
     */
    public void incrementarPartidasGanadas() {
        this.partidasGanadas++;
    }
    
    /**
     * Reinicia el contador de pares para una nueva partida
     */
    public void reiniciarPares() {
        this.paresEncontrados = 0;
    }
    
    /**
     * Obtiene el nombre del jugador
     * @return String con el nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la cantidad de pares encontrados en la partida actual
     * @return Número de pares encontrados
     */
    public int getParesEncontrados() {
        return paresEncontrados;
    }
    
    /**
     * Obtiene el total de partidas ganadas en la sesión
     * @return Número de partidas ganadas
     */
    public int getPartidasGanadas() {
        return partidasGanadas;
    }
}