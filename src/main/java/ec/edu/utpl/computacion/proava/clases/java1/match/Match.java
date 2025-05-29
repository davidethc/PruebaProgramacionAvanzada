package ec.edu.utpl.computacion.proava.clases.java1.match;





import ec.edu.utpl.computacion.proava.clases.java1.model.Jugador;

import java.util.Random;
import java.util.concurrent.Callable;

public class Match implements Callable<Jugador> {
    private final Jugador player1;
    private final Jugador player2;
    private final Random random = new Random();

    public Match(Jugador player1, Jugador player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public Jugador call() throws Exception {
        int winsP1 = 0, winsP2 = 0;

        System.out.println(player1 + " vs " + player2);

        for (int set = 1; set <= 3; set++) {
            long duration = 1500 + random.nextInt(501);
            Thread.sleep(duration);

            Jugador winner = random.nextBoolean() ? player1 : player2;
            System.out.println("Set " + set + ": " + winner);

            if (winner.equals(player1)) winsP1++;
            else winsP2++;

            if (winsP1 == 2 || winsP2 == 2) break;
        }

        Jugador matchWinner = winsP1 == 2 ? player1 : player2;
        System.out.println("Ganador del partido: " + matchWinner + "\n");

        return matchWinner;
    }
}
