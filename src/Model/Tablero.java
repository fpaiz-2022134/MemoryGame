package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Clase Tablero - Gestiona la estructura bidimensional del juego
 * 
 * @author Franco Paiz 25780
 * @since 2025-09-07
 */
public class Tablero {
	private Ficha[][] fichas;
	private int filas;
	private int columnas;
	private String[] simbolosDisponibles = {
		"🌟", "🎈", "🎨", "🎭", "🎪", "🎯", "🎲", "🎳",
        "🎸", "🎹", "🎺", "🎻", "🎼", "🎵", "🎶", "🎷",
        "🏀", "🏈", "⚽", "🎾", "🏐", "🏓", "🏸", "🥊"
	};
	
	/**
	 * Constructor del tablero
	 * @param filas
	 * @param columnas
	 */
	public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.fichas = new Ficha[filas][columnas];
        inicializarFichas();
    }
	
	
	/**
	 * Creación de fichas de forma aleatoria
	 */
	private void inicializarFichas() {
		int totalFichas =  filas * columnas;
		int totalPares = totalFichas/2;
		//Lista de símbolos a distribuir
		List<String> simbolosDistribuir = new ArrayList<>();
	
		for(int i = 0; i < totalPares; i++) {
			String simbolo = simbolosDisponibles[i % simbolosDisponibles.length];
			simbolosDistribuir.add(simbolo);
			simbolosDistribuir.add(simbolo);
		}
		
		//Mezclar de manera aleatoria
		Collections.shuffle(simbolosDistribuir);
		
		//Distribución dentro del tablerou
		int index =0;
		for(int i = 0; i <filas; i++) {
			for(int j = 0; j<columnas; j++) {
				fichas[i][j] = new Ficha(simbolosDistribuir.get(index));
				index++;
			}
		}
	}
	
	public Ficha seleccionarFicha(int fila, int columna) {
		if(esSeleccionValida(fila, columna)) {
			fichas[fila][columna].revelar();
			return fichas[fila][columna];
		}
		
		return null;
	}
	
	/**
	     * Valida selección
     * @param fila 
     * @param columna
     * @return true si la selección es válida, false cuando no
     */
    public boolean esSeleccionValida(int fila, int columna) {
        // Verificando límites del tablero
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        
        Ficha ficha = fichas[fila][columna];
        return !ficha.isRevelada() && !ficha.isEmparejada();
    }
    
    /**
     * Oculta las fichas que no coinciden
     * @param ficha1
     * @param ficha2
     */
    public void ocultarFichas(Ficha ficha1, Ficha ficha2) {
    	if(ficha1 != null && ficha2 != null) {
    		ficha1.ocultar();
    		ficha2.ocultar();
    	}
    }
    
    /**
     * Verifica las parejas emparejadas
     * @return true si están las parejas emparejadas, false si no
     */
    
    public boolean todasEmparejadas() {
    	for (int i = 0; i<filas; i++) {
    		for(int j= 0; j<columnas; j++) {
    			if(fichas[i][j].isEmparejada()) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    

    /**
     * Visualización del tablero
     * @return String 
     */
    public String mostrarTablero() {
        StringBuilder sb = new StringBuilder();
        
        // Encabezado con números de columna
        sb.append("   ");
        for (int j = 0; j < columnas; j++) {
            sb.append(String.format("%3d ", j));
        }
        sb.append("\n");
        
        // Filas del tablero
        for (int i = 0; i < filas; i++) {
            sb.append(String.format("%2d ", i));
            for (int j = 0; j < columnas; j++) {
                Ficha ficha = fichas[i][j];
                if (ficha.isRevelada()) {
                    sb.append(String.format(" %s ", ficha.getSimbolo()));
                } else {
                    sb.append(" ❓ ");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    // Getters para acceso desde GUI
    public Ficha getFicha(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return fichas[fila][columna];
        }
        return null;
    }
    
    public int getFilas() {
        return filas;
    }
    
    public int getColumnas() {
        return columnas;
    }

}
