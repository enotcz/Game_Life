/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import EventManager.EventManager;
import EventManager.IEventListener;
import Game.EnumResult;


/**
 *
 * @author maksim
 */
public class Console implements IEventListener{

    public Console() {
        EventManager.getInstance().addListener(this);
    }
    

    @Override
    public void update(int[][] board) {
        String s = "";
        for (int i = 0; i < board.length+2; i++) {
            s+="_";
        }
        s+="\n";
        for (int i = 0; i < board.length; i++) {
            s+="[";
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j]==1){
                    s+="0";
                }
                else {
                    s+=" ";
                }
            }
            s+="]\n";
            
        }
        for (int i = 0; i < board.length+2; i++) {
            s+="_";
        }
        System.out.println(s);
    }

    @Override
    public void update(int countOfLifeCycles) {
    }

    @Override
    public void update(EnumResult result) {
        System.out.println(result);
    }
    
}
