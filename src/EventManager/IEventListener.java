/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventManager;

import Game.EnumResult;

/**
 *
 * @author maksim
 */
public interface IEventListener {
    public void update(int[][] board);
    public void update(int countOfLifeCycles);
    public void update(EnumResult result);
}
