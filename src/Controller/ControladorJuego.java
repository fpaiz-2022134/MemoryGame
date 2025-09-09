package Controller;

import Model.Ficha;
import Model.Jugador;
import Model.Tablero;

/**
 * Clase ControladorJuego - Maneja la lógica principal del juego
 * Autor: Franco Paiz
 * Fecha: Septiembre 2025
 * Descripción: Coordina las interacciones entre jugadores, tablero y fichas
 */
public class ControladorJuego {
    private Tablero tablero;
    private Jugador[] jugadores;
    private int jugadorActual;
    private boolean juegoTerminado;
    
    /**
     * Constructor - Inicializa el controlador del juego
     * @param filas Número de filas del tablero
     * @param columnas Número de columnas del tablero
     * @param nombres Array con los nombres de los jugadores
     */
    public ControladorJuego(int filas, int columnas, String[] nombres) {
        this.tablero = new Tablero(filas, columnas);
        this.jugadores = new Jugador[2];
        this.jugadores[0] = new Jugador(nombres[0]);
        this.jugadores[1] = new Jugador(nombres[1]);
        this.jugadorActual = 0;
        this.juegoTerminado = false;
    }
    
    /**
     * Gestiona un turno completo del jugador actual
     * @param fila1 Fila de la primera selección
     * @param columna1 Columna de la primera selección
     * @param fila2 Fila de la segunda selección
     * @param columna2 Columna de la segunda selección
     * @return true si encontró un par, false en caso contrario
     */
    public boolean jugarTurno(int fila1, int columna1, int fila2, int columna2) {
        // Validar que las selecciones sean diferentes
        if (fila1 == fila2 && columna1 == columna2) {
            return false;
        }
        
        // Seleccionar las fichas
        Ficha ficha1 = tablero.seleccionarFicha(fila1, columna1);
        Ficha ficha2 = tablero.seleccionarFicha(fila2, columna2);
        
        // Verificar que ambas selecciones sean válidas
        if (ficha1 == null || ficha2 == null) {
            return false;
        }
        
        // Procesar las selecciones
        boolean encontroPar = procesarSelecciones(ficha1, ficha2);
        
        // Si no encontró par, cambiar turno
        if (!encontroPar) {
            cambiarTurno();
        }
        
        // Verificar si el juego terminó
        if (tablero.todasEmparejadas()) {
            finalizarPartida();
        }
        
        return encontroPar;
    }
    
    /**
     * Procesa el par de fichas seleccionadas
     * @param ficha1 Primera ficha seleccionada
     * @param ficha2 Segunda ficha seleccionada
     * @return true si las fichas coinciden, false en caso contrario
     */
    public boolean procesarSelecciones(Ficha ficha1, Ficha ficha2) {
        if (ficha1.getSimbolo().equals(ficha2.getSimbolo())) {
            // Las fichas coinciden
            ficha1.emparejar();
            ficha2.emparejar();
            jugadores[jugadorActual].incrementarPares();
            return true;
        } else {
            // Las fichas no coinciden se ocultarán después de mostrarlas
            return false;
        }
    }
    
    /**
     * Oculta las fichas que no coincidieron
     * @param ficha1 Primera ficha a ocultar
     * @param ficha2 Segunda ficha a ocultar
     */
    public void ocultarFichasNoCoincidentes(Ficha ficha1, Ficha ficha2) {
        tablero.ocultarFichas(ficha1, ficha2);
    }
    
    /**
     * Cambia el turno al siguiente jugador
     */
    public void cambiarTurno() {
        jugadorActual = (jugadorActual + 1) % 2;
    }
    
    /**
     * Finaliza la partida determinando el ganador
     */
    public void finalizarPartida() {
        this.juegoTerminado = true;
        
        // Determinar ganador
        if (jugadores[0].getParesEncontrados() > jugadores[1].getParesEncontrados()) {
            jugadores[0].incrementarPartidasGanadas();
        } else if (jugadores[1].getParesEncontrados() > jugadores[0].getParesEncontrados()) {
            jugadores[1].incrementarPartidasGanadas();
        }
        // En caso de empate, nadie incrementa partidas ganadas
    }
    
    /**
     * Reinicia el juego para una nueva partida
     */
    public void iniciarNuevaPartida() {
        // Reiniciar contadores de pares
        jugadores[0].reiniciarPares();
        jugadores[1].reiniciarPares();
        
        // Crear nuevo tablero
        this.tablero = new Tablero(tablero.getFilas(), tablero.getColumnas());
        this.jugadorActual = 0;
        this.juegoTerminado = false;
    }
    
    /**
     * Obtiene información del estado actual del juego
     * @return String con el estado del juego
     */
    public String obtenerEstadoJuego() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n		GAME STATE		\n");
        sb.append("Turno de: ").append(jugadores[jugadorActual].getNombre()).append("\n");
        sb.append("\nPuntuaciones actuales:\n");
        sb.append(jugadores[0].getNombre()).append(": ").append(jugadores[0].getParesEncontrados()).append(" pares\n");
        sb.append(jugadores[1].getNombre()).append(": ").append(jugadores[1].getParesEncontrados()).append(" pares\n");
        sb.append("\nPartidas ganadas en la sesión:\n");
        sb.append(jugadores[0].getNombre()).append(": ").append(jugadores[0].getPartidasGanadas()).append(" partidas\n");
        sb.append(jugadores[1].getNombre()).append(": ").append(jugadores[1].getPartidasGanadas()).append(" partidas\n");
        
        return sb.toString();
    }
    
    // Getters
    public Tablero getTablero() {
        return tablero;
    }
    
    public Jugador getJugadorActual() {
        return jugadores[jugadorActual];
    }
    
    public Jugador[] getJugadores() {
        return jugadores;
    }
    
    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}