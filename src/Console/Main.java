/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Game.Game;

/**
 *
 * @author maksim
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game(10);
        game.generateBoard();
        Console cs = new Console();
        Thread th = new Thread(game);
        
        th.start();
    }
            
}
