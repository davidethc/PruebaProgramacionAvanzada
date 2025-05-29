package ec.edu.utpl.computacion.proava.clases.java1;



import ec.edu.utpl.computacion.proava.clases.java1.model.Jugador;
import ec.edu.utpl.computacion.proava.clases.java1.Service.TorneoManager;

import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }

        TorneoManager manager = new TorneoManager();
        Jugador campeon = manager.jugarRonda("OCTAVOS DE FINAL", jugadores);

        System.out.println("🏆 ¡Campeón del torneo: " + campeon.getNombre() + "!");
        manager.shutdown();
    }
}