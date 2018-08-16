/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventManager;

import Game.EnumResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maksim
 */
public class EventManager {
    private static volatile EventManager instance;
    List<IEventListener> listeners;
    

    private EventManager() {
        listeners = new ArrayList();
    }
    
    public void addListener(IEventListener listener){
        listeners.add(listener);
    }
    public boolean removeListener(IEventListener listener){
        return listeners.remove(listener);
    }
    
    public void notifyy(int[][] board){
        for(IEventListener listener:listeners){
            listener.update(board);
        }
    }
    public void notifyy(int countOfLifeCycles){
        for(IEventListener listener:listeners){
            listener.update(countOfLifeCycles);
        }
    }
    public void notifyy(EnumResult result){
        for(IEventListener listener:listeners){
            listener.update(result);
        }
    }
    
    
    
    public static EventManager getInstance(){
        if (instance==null){
            synchronized (EventManager.class){
                if (instance==null){
                    instance = new EventManager();
                }
            }
        }
        return instance;
    }
    
}
