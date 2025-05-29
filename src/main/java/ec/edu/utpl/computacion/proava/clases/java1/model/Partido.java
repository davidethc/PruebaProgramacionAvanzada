package ec.edu.utpl.computacion.proava.clases.java1.model;

import java.util.Random;
import java.util.concurrent.Callable;

public class Partido implements Callable<ResultadoPartido> {
    private final Jugador j1;
    private final Jugador j2;
    private final Random random = new Random();

    public Partido(Jugador j1, Jugador j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

    @Override
    public ResultadoPartido call() throws Exception {
        ResultadoPartido resultado = new ResultadoPartido();

        int setJ1 = 0, setJ2 = 0;
        int setCount = 0;

        while (setJ1 < 2 && setJ2 < 2) {
            Thread.sleep(1500 + random.nextInt(501)); // Simula 1.5 - 2 seg
            Jugador ganadorSet = random.nextBoolean() ? j1 : j2;
            resultado.agregarGanadorSet(ganadorSet);

            if (ganadorSet == j1) setJ1++;
            else setJ2++;
            setCount++;
        }

        resultado.setGanador(setJ1 == 2 ? j1 : j2);
        return resultado;
    }

    public Jugador getJ1() {
        return j1;
    }

    public Jugador getJ2() {
        return j2;
    }
}