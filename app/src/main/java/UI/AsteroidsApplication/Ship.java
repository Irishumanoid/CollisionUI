package UI.AsteroidsApplication;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javafx.scene.shape.Polygon;

public class Ship extends Character {

    public Ship(int x, int y) {
        super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
    }

    @Override
    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(character.getRotate()));
        double changeY = Math.sin(Math.toRadians(character.getRotate()));

        changeX *= 0.05;
        changeY *= 0.05;

        this.movement = this.movement.add(changeX, changeY);

        //could use a future instead to stop movement after 2 secs
        Executor executor = Executors.newSingleThreadExecutor();
        
        Future<Long> future = ((ExecutorService) executor).submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                //shouldn't pause every character
                getCharacter().setTranslateX(0); //get movement instead
                getCharacter().setTranslateY(0);
                return null;
            }
            //executor.shutdown();
        });

        try {
            Long returner = future.get(2, TimeUnit.SECONDS);
            returner.notify(); //the values should be updated now?
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}