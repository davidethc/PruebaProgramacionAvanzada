package ec.edu.utpl.computacion.proava.clases.java1.Service;

import ec.edu.utpl.computacion.proava.clases.java1.model.Jugador;
import ec.edu.utpl.computacion.proava.clases.java1.model.Partido;
import ec.edu.utpl.computacion.proava.clases.java1.model.ResultadoPartido;

import java.util.*;
import java.util.concurrent.*;

public class TorneoManager {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public Jugador jugarRonda(String nombreRonda, List<Jugador> jugadores) throws InterruptedException {
        System.out.println("===== " + nombreRonda + " =====");
        List<Future<ResultadoPartido>> futuros = new ArrayList<>();
        List<Jugador> ganadores = new ArrayList<>();

        if (nombreRonda.equals("OCTAVOS DE FINAL")) {
            int total = jugadores.size();
            for (int i = 0; i < total / 2; i++) {
                Jugador j1 = jugadores.get(i);
                Jugador j2 = jugadores.get(total - 1 - i);
                Partido partido = new Partido(j1, j2);
                Future<ResultadoPartido> futuro = executor.submit(partido);
                futuros.add(futuro);
            }
        } else {
            for (int i = 0; i < jugadores.size(); i += 2) {
                Jugador j1 = jugadores.get(i);
                Jugador j2 = jugadores.get(i + 1);
                Partido partido = new Partido(j1, j2);
                Future<ResultadoPartido> futuro = executor.submit(partido);
                futuros.add(futuro);
            }
        }

        for (int i = 0; i < futuros.size(); i++) {
            Future<ResultadoPartido> f = futuros.get(i);
            Jugador j1, j2;
            if (nombreRonda.equals("OCTAVOS DE FINAL")) {
                int total = jugadores.size();
                j1 = jugadores.get(i);
                j2 = jugadores.get(total - 1 - i);
            } else {
                j1 = jugadores.get(i * 2);
                j2 = jugadores.get(i * 2 + 1);
            }

            System.out.println(j1.getNombre() + " vs " + j2.getNombre());
            try {
                ResultadoPartido resultado = f.get();
                int setNum = 1;
                for (Jugador gSet : resultado.getGanadoresPorSet()) {
                    System.out.println("Set " + setNum++ + ": " + gSet.getNombre());
                }
                System.out.println("Ganador del partido: " + resultado.getGanador().getNombre() + "\n");
                ganadores.add(resultado.getGanador());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return ganadores.size() == 1 ? ganadores.get(0) : jugarRonda(siguienteRonda(nombreRonda), ganadores);
    }

    private String siguienteRonda(String ronda) {
        return switch (ronda) {
            case "OCTAVOS DE FINAL" -> "CUARTOS DE FINAL";
            case "CUARTOS DE FINAL" -> "SEMIFINAL";
            case "SEMIFINAL" -> "FINAL";
            default -> "FINAL";
        };
    }

    public void shutdown() {
        executor.shutdown();
    }
}
