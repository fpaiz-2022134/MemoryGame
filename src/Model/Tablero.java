package Model;

import java.util.*;

/**
 * Clase Tablero - Gestiona la estructura bidimensional del juego
 * Autor: Franco Paiz
 * Fecha: Septiembre 2025
 * Descripción: Maneja el tablero de fichas, su inicialización y validaciones
 */
public class Tablero {
    private Ficha[][] fichas;
    private int filas;
    private int columnas;
    private String[] simbolosDisponibles;
    
    /**
     * Constructor - Inicializa el tablero con las dimensiones especificadas
     * @param filas Número de filas del tablero
     * @param columnas Número de columnas del tablero
     */
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.fichas = new Ficha[filas][columnas];
        
        // Símbolos emoji para las fichas
        this.simbolosDisponibles = new String[]{
            "🎵", "🎭", "🎨", "🎯", "🎪", "🎲", "🎸", "🎺", 
            "🎻", "🎤", "🎧", "🎬", "🎮", "🎯", "🎲", "🎪",
            "⚽", "🏀", "🏈", "⚾", "🎾", "🏐", "🏓", "🏸"
        };
        
        inicializarFichas();
    }
    
    /**
     * Crea y distribuye las fichas de forma aleatoria en el tablero
     */
    public void inicializarFichas() {
        int totalCasillas = filas * columnas;
        int paresNecesarios = totalCasillas / 2;
        
        // Crear lista con pares de símbolos
        List<String> simbolos = new ArrayList<>();
        for (int i = 0; i < paresNecesarios; i++) {
            String simbolo = simbolosDisponibles[i % simbolosDisponibles.length];
            simbolos.add(simbolo);
            simbolos.add(simbolo); // Agregar el par
        }
        
        // Mezclar los símbolos aleatoriamente
        Collections.shuffle(simbolos);
        
        // Llenar el tablero con las fichas
        int indice = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                fichas[i][j] = new Ficha(simbolos.get(indice++));
            }
        }
    }
    
    /**
     * Revela una ficha en la posición especificada
     * @param fila Fila de la ficha a seleccionar
     * @param columna Columna de la ficha a seleccionar
     * @return La ficha seleccionada
     */
    public Ficha seleccionarFicha(int fila, int columna) {
        if (esSeleccionValida(fila, columna)) {
            fichas[fila][columna].revelar();
            return fichas[fila][columna];
        }
        return null;
    }
    
    /**
     * Valida si la selección es permitida
     * @param fila Fila a validar
     * @param columna Columna a validar
     * @return true si es válida, false en caso contrario
     */
    public boolean esSeleccionValida(int fila, int columna) {
        // Verificar límites del tablero
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        
        Ficha ficha = fichas[fila][columna];
        // No se puede seleccionar una ficha ya emparejada o ya revelada
        return !ficha.isEmparejada() && !ficha.isRevelada();
    }
    
    /**
     * Oculta dos fichas que no coincidieron
     * @param ficha1 Primera ficha a ocultar
     * @param ficha2 Segunda ficha a ocultar
     */
    public void ocultarFichas(Ficha ficha1, Ficha ficha2) {
        if (ficha1 != null && ficha2 != null) {
            ficha1.ocultar();
            ficha2.ocultar();
        }
    }
    
    /**
     * Verifica si todas las parejas han sido emparejadas
     * @return true si el juego ha terminado, false en caso contrario
     */
    public boolean todasEmparejadas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!fichas[i][j].isEmparejada()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Genera la representación visual del tablero
     * @return String con el tablero formateado
     */
    public String mostrarTablero() {
        StringBuilder sb = new StringBuilder();
        
        // Encabezado con números de columna
        sb.append("   ");
        for (int j = 0; j < columnas; j++) {
            sb.append(String.format("%3d", j));
        }
        sb.append("\n");
        
        // Filas del tablero
        for (int i = 0; i < filas; i++) {
            sb.append(String.format("%2d ", i));
            for (int j = 0; j < columnas; j++) {
                Ficha ficha = fichas[i][j];
                if (ficha.isRevelada() || ficha.isEmparejada()) {
                    sb.append(" ").append(ficha.getSimbolo()).append(" ");
                } else {
                    sb.append(" ▢ "); // Símbolo para ficha oculta
                }
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    // Getters para acceso a las dimensiones
    public int getFilas() {
        return filas;
    }
    
    public int getColumnas() {
        return columnas;
    }
}