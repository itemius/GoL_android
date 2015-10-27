package info.itemius.gol.logic;

import android.util.Log;


public class SimpleAlgorithm {

    public static boolean[] getNextState(boolean[] cells){

        int[] neighbors = new int[400];
        for (int i = 0; i < 400; i++){
            neighbors[i] = countNeighbors(i, cells);
        }

        for (int i = 0; i < 400; i++){
            if (neighbors[i] < 2 || neighbors[i] > 3){
                cells[i] = false;
            } else if (neighbors[i] == 3){
                cells[i] = true;
            }
        }

        return cells;
    }

    static int countNeighbors(int position, boolean[] cells){

        int neighbors = 0;

        //top left corner
        if (position == 0){
            if (cells[position + 1])
                neighbors++;
            if (cells[position + 20])
                neighbors++;
            if (cells[position + 21])
                neighbors++;
            return neighbors;
        }

        //bottom left corner
        if (position == 380){
            if (cells[position + 1])
                neighbors++;
            if (cells[position - 20])
                neighbors++;
            if (cells[position - 19])
                neighbors++;
            return neighbors;
        }

        //top right corner
        if (position == 19){
            if (cells[position - 1])
                neighbors++;
            if (cells[position + 19])
                neighbors++;
            if (cells[position + 20])
                neighbors++;
            return neighbors;
        }

        //bottom right corner
        if (position == 399){
            if (cells[position - 1])
                neighbors++;
            if (cells[position - 20])
                neighbors++;
            if (cells[position - 21])
                neighbors++;
            return neighbors;
        }

        //left edge
        if ((position % 20 == 0) && position < 380){
            if (cells[position + 1])
                neighbors++;
            if (cells[position + 20])
                neighbors++;
            if (cells[position - 20])
                neighbors++;
            if (cells[position + 21])
                neighbors++;
            if (cells[position - 19])
                neighbors++;
            return neighbors;
        }

        //top edge
        if (position < 19){
            if (cells[position + 1])
                neighbors++;
            if (cells[position + 20])
                neighbors++;
            if (cells[position - 1])
                neighbors++;
            if (cells[position + 19])
                neighbors++;
            if (cells[position + 21])
                neighbors++;
            return neighbors;
        }

        //right edge
        if ((position % 20 == 19) && position < 380){
            if (cells[position - 1])
                neighbors++;
            if (cells[position + 20])
                neighbors++;
            if (cells[position - 20])
                neighbors++;
            if (cells[position + 19])
                neighbors++;
            if (cells[position - 21])
                neighbors++;
            return neighbors;
        }

        //bottom edge
        if (position > 380 && position < 399){
            if (cells[position + 1])
                neighbors++;
            if (cells[position - 1])
                neighbors++;
            if (cells[position - 20])
                neighbors++;
            if (cells[position - 19])
                neighbors++;
            if (cells[position - 21])
                neighbors++;
            return neighbors;
        }

        //all other cells
        if (cells[position - 1])
            neighbors++;
        if (cells[position + 1])
            neighbors++;
        if (cells[position - 20])
            neighbors++;
        if (cells[position + 20])
            neighbors++;
        if (cells[position + 19])
            neighbors++;
        if (cells[position + 21])
            neighbors++;
        if (cells[position - 19])
            neighbors++;
        if (cells[position - 21])
            neighbors++;

        return neighbors;
    }
}
