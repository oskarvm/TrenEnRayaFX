package data;

import control.JuegoPrincipalController;

import java.util.ArrayList;

public class Partida {

    JuegoPrincipalController juegoPrincipalController;

    public int puntosJug1 = 0;
    public int puntosJug2 = 0;
    public int puntosEmpate = 0;
    public int turno = 1;

    // Matriz para comprobar las posiciones ganadoras
    private int[][] wins = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {3, 6, 9}, {2, 5, 8}, {1, 5, 9}, {7, 5, 3}};

    // Listas para guardar las posiciones de los jugadores
    ArrayList<Integer> posicionesJug1 = new ArrayList<>();
    ArrayList<Integer> posicionesJug2 = new ArrayList<>();

    public Partida(JuegoPrincipalController juegoPrincipalController) {
        this.juegoPrincipalController = juegoPrincipalController;
    }

    // Añadimos la posición a la lista correspondiente y comprobamos si ha ganado la partida.
    // Tambnién pasamos un número para controlar quien es el juagdordel turno jugado. (Jugador 1 = 1, Jugador 2 = 2)
    public void jugarTurno(int pos) {

        // turnos impares
        if (turno % 2 == 1) {
            posicionesJug1.add(pos);
            comprobarGanador(posicionesJug1, 1);
            turno++;
        } else {
            posicionesJug2.add(pos);
            comprobarGanador(posicionesJug2, 2);
            turno++;
        }


    }

    public int getTurno() {
        return turno;
    }


    // Comprueba si el jugador ha ganado la partida,
    // Le pasamos la lista con las posiciones del jugador a comprobar, y el numero del jugador
    void comprobarGanador(ArrayList jugador, int numJugador) {

        // booleano para comprobar si algún jugador ha ganado.
        boolean ganador = false;

        for (int i = 0; i < wins.length; i++) {

            // Contador con el cual sabemos si ganamos. Para ganar tiene que llegar a 3
            int contador = 0;

            for (int j = 0; j < wins[i].length; j++) {

                for (int k = 0; k < jugador.size(); k++) {
                    if (jugador.get(k).equals(wins[i][j])) {
                        contador++;
                    }
                }


            }
            if (contador == 3) {
                ganador = true;
                ganar(numJugador);
                break;
            }
        }
        // Comprueba si no quedan más jugadas y no hay ganador y llama al metodo empatar
        if (posicionesJug1.size() == 5 && posicionesJug2.size() == 4 && !ganador) {
            empate();
        }

    }

    // Suma un punto al jugador ganador y reinicia la partida
    private void ganar(int jugador) {

        if (jugador == 1) {
            puntosJug1++;
        } else {
            puntosJug2++;
        }

        juegoPrincipalController.reinicio();

    }

    // Suma 1 al contador de empate y reinicia la partida
    private void empate() {

        puntosEmpate++;
        juegoPrincipalController.reinicio();

    }
}
