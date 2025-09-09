package View;

import java.util.Scanner;

import Controller.ControladorJuego;
import Model.Jugador;

/**
 * Clase Principal - Maneja la interacción con el usuario
 * @author Franco Paiz
 * Fecha: Septiembre 2025
 * Descripción: Interfaz de consola para el juego de memoria
 * Programa: Juego de Memoria - Laboratorio 2 POO
 * 
 * PSDT: Traté hacerlo gráfico pero morí
 */
public class Principal {
    private Scanner scanner;
    private ControladorJuego juego;
    private Jugador[] jugadoresPrevios;
    
    /**
     * Constructor - Inicializa el scanner para entrada de usuario
     */
    public Principal() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Punto de entrada del programa
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.ejecutar();
    }
    
    /**
     * Método principal que ejecuta el flujo del programa
     */
    private void ejecutar() {
        mostrarMenu();
        
        String[] nombres = solicitarNombresJugadores();
        
        do {
            int[] dimensiones = solicitarDimensiones();
            
            juego = new ControladorJuego(dimensiones[0], dimensiones[1], nombres);
            
            // Si no es la primera partida, restaurar estadísticas previas
            if (jugadoresPrevios != null) {
                juego.getJugadores()[0].setPartidasGanadas(jugadoresPrevios[0].getPartidasGanadas());
                juego.getJugadores()[1].setPartidasGanadas(jugadoresPrevios[1].getPartidasGanadas());
            }
            
            // Jugar partida
            jugarPartida();
            
            // Guardar referencia a los jugadores para la próxima partida
            jugadoresPrevios = juego.getJugadores();
            
            // Mostrar resultados
            mostrarResultados();
            
        } while (confirmarNuevaPartida());
        
        mostrarEstadisticasFinales();
        scanner.close();
    }
    
    /**
     * Muestra el menú principal del juego
     */
    public void mostrarMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        	 MEmORy GAMEEE  :0    		 ║");
        System.out.println("║                                      ║");
        System.out.println("║  Encuentra todos los pares de fichas ║");
        System.out.println("║  para ganar puntos y demostrar tu    ║");
        System.out.println("║  memoria visual.                     ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
    
    /**
     * Solicita las dimensiones del tablero al usuario
     * @return Array con [filas, columnas]
     */
    public int[] solicitarDimensiones() {
        int[] dimensiones = new int[2];
        
        System.out.println("\n 		Creando Tablero	");
        
        do {
            System.out.print("Ingrese número de filas (mínimo 2, par): ");
            try {
                dimensiones[0] = Integer.parseInt(scanner.nextLine());
                if (dimensiones[0] < 2 || dimensiones[0] % 2 != 0) {
                    System.out.println("Error: Ingrese un número par mayor o igual a 2.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        } while (true);
        
        do {
            System.out.print("Ingrese número de columnas (mínimo 2): ");
            try {
                dimensiones[1] = Integer.parseInt(scanner.nextLine());
                if (dimensiones[1] < 2) {
                    System.out.println("❌ Error: Ingrese un número mayor o igual a 2.");
                    continue;
                }
                
                // Verificar que el total de casillas sea par
                int total = dimensiones[0] * dimensiones[1];
                if (total % 2 != 0) {
                    System.out.println("❌ Error: El total de casillas debe ser par para formar parejas.");
                    System.out.println("   Total actual: " + total + " casillas");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Ingrese un número válido.");
            }
        } while (true);
        
        return dimensiones;
    }
    
    /**
     * Solicita los nombres de los jugadores
     * @return Array con los nombres de los jugadores
     */
    public String[] solicitarNombresJugadores() {
        String[] nombres = new String[2];
        
        System.out.println("\n	Registro de Jugadores	");
        
        System.out.print("Nombre del Jugador 1: ");
        nombres[0] = scanner.nextLine().trim();
        if (nombres[0].isEmpty()) {
            nombres[0] = "Jugador 1";
        }
        
        System.out.print("Nombre del Jugador 2: ");
        nombres[1] = scanner.nextLine().trim();
        if (nombres[1].isEmpty()) {
            nombres[1] = "Jugador 2";
        }
        
        return nombres;
    }
    /**
     * Controla el flujo de una partida completa
     */
    private void jugarPartida() {
        System.out.println("\n🎮 ¡Comenzando la partida!");
        
        while (!juego.isJuegoTerminado()) {
            // Mostrar estado actual
            System.out.println(juego.obtenerEstadoJuego());
            System.out.println("\nTablero actual:");
            System.out.println(juego.getTablero().mostrarTablero());
            
            // Verificar si ya terminó antes de solicitar selecciones
            if (juego.isJuegoTerminado()) {
                break;
            }
            
            // Solicitar selecciones del jugador actual
            int[][] selecciones = solicitarDosSelecciones();
            
            // Procesar turno
            boolean encontroPar = juego.jugarTurno(
                selecciones[0][0], selecciones[0][1],
                selecciones[1][0], selecciones[1][1]
            );
            
            // Mostrar resultado del turno si el juego no terminó
            if (!juego.isJuegoTerminado()) {
                if (encontroPar) {
                    System.out.println("🎉 ¡Excelente! " + juego.getJugadorActual().getNombre() + " encontró un par!");
                    System.out.println("   Continúa jugando...");
                } else {
                    System.out.println("❌ Las fichas no coinciden. Turno perdido.");
                    // Pausa para que vean las fichas antes de ocultarlas
                    System.out.println("   Presiona Enter para continuar...");
                    scanner.nextLine();
                }
            } else {
                // Si el juego terminó con este par
                if (encontroPar) {
                    System.out.println("🎉 ¡" + juego.getJugadorActual().getNombre() + " encontró el último par!");
                }
            }
        }
    }
    
    /**
     * Solicita dos selecciones de coordenadas al jugador
     * @return Array bidimensional con las coordenadas [primera][fila,columna], [segunda][fila,columna]
     */
    private int[][] solicitarDosSelecciones() {
        int[][] selecciones = new int[2][2];
        
        System.out.println("\n" + juego.getJugadorActual().getNombre() + ", es tu turno:");
        
        // Primera selección
        selecciones[0] = solicitarSeleccion("primera");
        
        // Segunda selección
        do {
            selecciones[1] = solicitarSeleccion("segunda");
            
            // Verificar que no sea la misma posición
            if (selecciones[0][0] == selecciones[1][0] && selecciones[0][1] == selecciones[1][1]) {
                System.out.println("No puedes seleccionar la misma casilla dos veces :/");
            } else {
                break;
            }
        } while (true);
        
        return selecciones;
    }
    
    /**
     * Solicita una selección de coordenadas
     * @param orden Indica si es la primera o segunda selección
     * @return Array con [fila, columna]
     */
    public int[] solicitarSeleccion(String orden) {
        int[] coordenadas = new int[2];
        
        do {
            try {
                System.out.print("Ingrese fila para " + orden + " selección: ");
                coordenadas[0] = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Ingrese columna para " + orden + " selección: ");
                coordenadas[1] = Integer.parseInt(scanner.nextLine());
                
                // Validar que la selección sea válida
                if (juego.getTablero().esSeleccionValida(coordenadas[0], coordenadas[1])) {
                    break;
                } else {
                    System.out.println("Selección no válida. Verifica las coordenadas y que la ficha no esté emparejada.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese números válidos por favor.");
            }
        } while (true);
        
        return coordenadas;
    }
    
    /**
     * Muestra los resultados de la partida
     */
    public void mostrarResultados() {
        System.out.println("\n🏁 ¡La partida terminó!");
        System.out.println("═════════════════════════");
        
        Jugador[] jugadores = juego.getJugadores();
        System.out.println("\n📊 STONKS (Resultados):");
        System.out.println(jugadores[0].getNombre() + ": " + jugadores[0].getParesEncontrados() + " pares");
        System.out.println(jugadores[1].getNombre() + ": " + jugadores[1].getParesEncontrados() + " pares");
        
        // Determinar ganador
        if (jugadores[0].getParesEncontrados() > jugadores[1].getParesEncontrados()) {
            System.out.println("\n🏆 ¡" + jugadores[0].getNombre() + " Ganó!");
        } else if (jugadores[1].getParesEncontrados() > jugadores[0].getParesEncontrados()) {
            System.out.println("\n🏆 ¡" + jugadores[1].getNombre() + " Ganó!");
        } else {
            System.out.println("\n🤝 ¡Empate! Ambos jugadores son unos cricks, que barbaro.");
        }
    }
    
    /**
     * Pregunta si desea continuar con otra partida
     * @return true si quiere seguir jugando, false en caso contrario
     */
    public boolean confirmarNuevaPartida() {
        System.out.print("\n¿Desean jugar otra partida? (s/n): ");
        String respuesta = scanner.nextLine().toLowerCase().trim();
        return respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí");
    }
    
    /**
     * Muestra las estadísticas finales de la sesión
     */
    private void mostrarEstadisticasFinales() {
        if (juego != null) {
            System.out.println("\n📈 Final Stonks (Estadísticas)");
            System.out.println("═════════════════════════════════════");
            
            Jugador[] jugadores = juego.getJugadores();
            System.out.println(jugadores[0].getNombre() + ": " + jugadores[0].getPartidasGanadas() + " partidas ganadas");
            System.out.println(jugadores[1].getNombre() + ": " + jugadores[1].getPartidasGanadas() + " partidas ganadas");
            
            // Determinar campeón de la sesión
            if (jugadores[0].getPartidasGanadas() > jugadores[1].getPartidasGanadas()) {
                System.out.println("\n🏆 ¡" + jugadores[0].getNombre() + " es el CAMPEÓN de la sesión!");
            } else if (jugadores[1].getPartidasGanadas() > jugadores[0].getPartidasGanadas()) {
                System.out.println("\n🏆 ¡" + jugadores[1].getNombre() + " es el CAMPEÓN de la sesión!");
            } else {
                System.out.println("\n🤝 ¡Empate! Ambos jugadores son unos cricks, que barbaro.");
            }
        }
        
        System.out.println("\n💭 ¡Gracias por jugar vuelva pronto!");
        System.out.println("═════════════════════════════════════");
    }
}