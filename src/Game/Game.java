/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import EventManager.EventManager;

/**
 *
 * @author maksim
 */
public class Game extends Thread {

    private final int boardWidth;
    private final int boardHeight;
    private int countOfLifeCycles;
    private int[][] board;
    private int[][] mainBoard;

    public Game(int[][] board) {
        this.boardWidth = board.length;
        this.boardHeight = board[0].length;
        this.board = board;
        mainBoard = new int[boardWidth][boardHeight];
    }
    
    public Game(int size){
        this.boardWidth = size;
        this.boardHeight = size;
        this.board = new int[size][size];
        mainBoard = new int[size][size];
    }
    
    public void generateBoard(){
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                board[i][j]=(int) (Math.random()*2);
            }
        }
    }

    private int getCountOfLiveBoundsNear(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && y == j) {
                    continue;
                }
                int tmpX = i;
                int tmpY = j;
                tmpX = tmpX < 0 ? boardWidth : tmpX;
                tmpX = tmpX >= boardWidth ? 0 : tmpX;
                tmpY = tmpY < 0 ? boardHeight : tmpY;
                tmpY = tmpY >= boardHeight ? 0 : tmpY;
                if (board[tmpX][tmpY] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void run() {
        countOfLifeCycles = 0;
        while (!Thread.currentThread().isInterrupted()) {
            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardHeight; j++) {
                    synchronized (this) {
                        int count = getCountOfLiveBoundsNear(i, j);
                        if (board[i][j] == 0 && count == 3) {
                            mainBoard[i][j] = 1;
                            continue;
                        }
                        if (board[i][j] == 1 && !(count == 2 || count == 3)) {
                            mainBoard[i][j] = 0;
                            continue;
                        }
                    }
                }
            }
            boolean isSimilar = true;
            boolean allDied = true;
            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardHeight; j++) {
                    if (mainBoard[i][j] == 1){
                        allDied = false;
                    }
                    if (mainBoard[i][j] != board[i][j]) {
                        isSimilar = false;
                        break;
                    }
                }

            }
            if (allDied){
                EventManager.getInstance().notifyy(EnumResult.ALL_DIED);
                Thread.currentThread().interrupt();
            }
            if (isSimilar) {
                EventManager.getInstance().notifyy(EnumResult.ALL_WILL_LIVE);
                Thread.currentThread().interrupt();
            }
            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardHeight; j++) {
                    board[i][j] = mainBoard[i][j];
                }

            }
            countOfLifeCycles++;
            EventManager.getInstance().notifyy(board);
            EventManager.getInstance().notifyy(countOfLifeCycles);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
