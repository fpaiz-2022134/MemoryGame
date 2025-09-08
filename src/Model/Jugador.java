package Model;


/**
 * Clase Jugador - Representa a cada participante del juego
 * 
 * @author Franco Paiz 25780
 * @since 2025-09-07
 */
public class Jugador {
	
	private String nombre;
	private int paresEncontrados;
	private int partidasGanadas;
	
	/**
     * Constructor que inicializa a un jugador con su nombre
     * @param nombre
     */
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.paresEncontrados = 0;
		this.partidasGanadas = 0;
	}
	
	/**
	 * Método que incrementa contador de pares encontrados
	 */
	public void incrementarPares() {
		this.paresEncontrados++;
	}
	
	/**
	 * Método que incrementa contador de partidas ganadas
	 */
	public void incrementarPartidasGanadas() {
		this.partidasGanadas++;
	}
	
	/**
	 * Reinicia contador de los pares
	 */
	public void reiniciarPares() {
		this.paresEncontrados =0;
	}
	
	/**
     * Obtiene el nombre del jugador
     * @return Nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la cantidad de pares encontrados en la partida actual
     * @return Num. pares encontrados
     */
    public int getParesEncontrados() {
        return paresEncontrados;
    }
    
    /**
     * Obtiene el total de partidas ganadas en la sesión
     * @return Num. partidas ganadas
     */
    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    
	
	
}
