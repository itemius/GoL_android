package info.itemius.gol;

import info.itemius.gol.logic.SimpleAlgorithm;

/**
 * Created by ibad on 15/09/15.
 */
public class GameState {

    public static boolean started = false;

    public static boolean[] cells = new boolean[400];
    static {
        for (int i = 0; i<400; i++){
            cells[i] = false;
        }
    }

    private static GameState ourInstance = new GameState();

    public static GameState getInstance() {
        return ourInstance;
    }

    private GameState() {
    }

    public static void nextState(){
        cells = SimpleAlgorithm.getNextState(cells);
    }
}
