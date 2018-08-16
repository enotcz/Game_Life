/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.Game;
import EventManager.EventManager;
import EventManager.IEventListener;
import Game.EnumResult;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author maksim
 */
public class FXController implements Initializable, IEventListener {

    private static final int SIZE = 10;

    private Bound[][] bounds;
    private Thread runningThread;
    private Game game;

    @FXML
    private Pane pane;
    @FXML
    private Button bStartID;
    @FXML
    private Label count;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bounds = new Bound[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                bounds[i][j] = new Bound(10, 10, 10);
                bounds[i][j].hide();
                bounds[i][j].setOnMouseClicked(e -> {
                    stopGame();
                    if (((Bound) e.getSource()).isShown()) {
                        ((Bound) e.getSource()).hide();
                    } else {
                        ((Bound) e.getSource()).show();
                    }
                });

                pane.getChildren().add(bounds[i][j]);
            }
        }
        pane.widthProperty().addListener(e -> redrawPane());
        pane.heightProperty().addListener(e -> redrawPane());

        EventManager.getInstance().addListener(this);

    }

    public void redrawPane() {
        double widthStep = pane.getWidth() / SIZE;
        double heightStep = pane.getHeight() / SIZE;
        double newRadius = Math.min(widthStep, heightStep);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                bounds[i][j].setSize(newRadius);
                bounds[i][j].setX(i * widthStep);
                bounds[i][j].setY(j * heightStep);
            }
        }
    }

    public void updatePane(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 1) {
                    bounds[i][j].show();
                } else {
                    bounds[i][j].hide();
                }
                bounds[i][j].blink();
            }
        }

    }


    @FXML
    private void bStart(ActionEvent event) {
        stopGame();
        int[][] board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (bounds[i][j].isShown()) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
        game = new Game(board);
        runningThread = new Thread(game);
        runningThread.setDaemon(true);
        runningThread.start();
    }

    @FXML
    private void bRandom(ActionEvent event) {
        stopGame();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (((int) (Math.random() * 2)) == 1) {
                    bounds[i][j].show();
                } else {
                    bounds[i][j].hide();
                }
            }
        }
    }

    private void stopGame() {
        if (runningThread != null) {
            runningThread.interrupt();
        }
    }

    @FXML
    private void bClear(ActionEvent event) {
        stopGame();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                bounds[i][j].hide();
            }
        }
    }

    @Override
    public void update(int[][] board) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updatePane(board);
            }
        });

    }

    @Override
    public void update(int countOfLifeCycles) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                count.setText(countOfLifeCycles + "");
            }
        });

    }

    @Override
    public void update(EnumResult result) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                    Alert al = new Alert(AlertType.INFORMATION);
                    al.setHeaderText(result.toString());
                    al.showAndWait();
            }
        });

    }

}
