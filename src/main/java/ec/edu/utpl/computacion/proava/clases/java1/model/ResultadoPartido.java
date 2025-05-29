package ec.edu.utpl.computacion.proava.clases.java1.model;


import java.util.ArrayList;
import java.util.List;

public class ResultadoPartido {
    private final List<Jugador> ganadoresPorSet = new ArrayList<>();
    private Jugador ganador;

    public void agregarGanadorSet(Jugador jugador) {
        ganadoresPorSet.add(jugador);
    }

    public List<Jugador> getGanadoresPorSet() {
        return ganadoresPorSet;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }
}