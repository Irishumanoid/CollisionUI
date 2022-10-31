package UI.AsteroidsApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import groovyjarjarpicocli.CommandLine.ExecutionException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

//TO DO: fix game over button so when it runs again the animation isn't overwritten by the game over screen
// connect to face API

public class AsteroidsApplication extends Application {
    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    private Map<KeyCode, Boolean> keysPressed = new HashMap<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Asteroid> asteroids = new ArrayList<>();
    private Ship ship = new Ship(WIDTH/2, HEIGHT/2);

    public int getNumProjectiles() {
        return projectiles.size();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Text text = new Text(10, 20, "Points: 0");
        AtomicInteger points = new AtomicInteger();

        BorderPane goPane = new BorderPane();
        Button play = new Button("Play Again");
        

        pane.setPrefSize(WIDTH, HEIGHT);
        goPane.setPrefSize(WIDTH, HEIGHT);
        goPane.setCenter(play);
        goPane.setTop(new Label("Game Over!"));

        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            Asteroid ast = new Asteroid(rand.nextInt(WIDTH/3), rand.nextInt(HEIGHT/3));
            asteroids.add(ast);
        }

        pane.getChildren().addAll(text, ship.getCharacter());
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));
        
        Scene gameOver = new Scene(goPane);
        Scene scene = new Scene(pane);

        play.setOnAction((event) -> {
            try {
                for (Asteroid asteroid : asteroids) {
                    pane.getChildren().removeAll(asteroid.getCharacter());
                }
                for (Projectile projectile : projectiles) {
                    pane.getChildren().removeAll(projectile.getCharacter());
                }

                pane.getChildren().remove(ship.getCharacter());
                text.setText("Points: " + points.addAndGet(-points.get()));
                
                stage.setScene(scene);
                Ship newShip = new Ship(WIDTH/2, HEIGHT/2);
                pane.getChildren().add(newShip.getCharacter());
                
                
                new AnimationTimer() {

                    @Override
                    public void handle(long now) {
                        animation(pane, stage, gameOver, text, points);
                    }
                };
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        scene.setOnKeyPressed(event -> {
            keysPressed.put(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased((event) -> {
            keysPressed.put(event.getCode(), Boolean.FALSE);
        });
        

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                animation(pane, stage, gameOver, text, points);
            }
            
        }.start();


        stage.setTitle("Incomplete");
        stage.setScene(scene);
        stage.show();
    }

    public Task<Void> removeProjectile(Pane pane) {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    if (projectiles.size() >= 5) {
                        for (int i = 0; i < projectiles.size()-3; i++) {
                            pane.getChildren().remove(projectiles.get(i).getCharacter());
                        }
                    }
                });

                try {
                    future.get(100, TimeUnit.MILLISECONDS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();;
                } catch (ExecutionException | TimeoutException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    private boolean removeActiveKey(boolean wasPressed) {
        boolean isActive = keysPressed.get(KeyCode.SPACE);
        if (isActive) {
            keysPressed.put(KeyCode.SPACE, false);
            return true;
        }
        return false;
    }

    public void restart(Stage stage, Scene scene) throws Exception {
        stage.setScene(scene);
    }

    public void animation(Pane pane, Stage stage, Scene gameOver, Text text, AtomicInteger points) {
        if (keysPressed.getOrDefault(KeyCode.LEFT, false)) {
             ship.turnLeft();
        } else if (keysPressed.getOrDefault(KeyCode.RIGHT, false)) {
            ship.turnRight();
        } else if (keysPressed.getOrDefault(KeyCode.UP, false)) {
            ship.accelerate();
        } else if (keysPressed.getOrDefault(KeyCode.SPACE, false) && Available.numAvailable > 0) {
            if(removeActiveKey(keysPressed.get(KeyCode.SPACE))) {
                Projectile projectile = new Projectile((int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());
                projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                projectiles.add(projectile);
                
                projectile.accelerate();
                projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                pane.getChildren().add(projectile.getCharacter());
            }
        } else if (keysPressed.getOrDefault(KeyCode.DOWN, false)) {
            ship.deccelerate();
        }
                
        ship.move();
        asteroids.forEach(asteroid -> asteroid.move());
        projectiles.forEach(projectile -> projectile.move());

         projectiles.forEach(projectile -> {
            asteroids.forEach(asteroid -> {
                if (asteroid.checkCollision(projectile)) {
                     Available.numAvailable++;
                }
            });
        });

        asteroids.forEach(asteroid -> {
             if (ship.checkCollision(asteroid)) {
                stage.setScene(gameOver);
                 try {
                     stop();
                } catch (Exception e) {
                     e.printStackTrace();
                }
            }
         });
        
         List<Projectile> projectilesToRemove = projectiles.stream().filter(projectile -> { 
             List<Asteroid> collisions = asteroids.stream()
            .filter(asteroid -> asteroid.checkCollision(projectile))
            .collect(Collectors.toList());

             if (collisions.isEmpty()) {
                return false;
             }

            collisions.stream().forEach(collided -> {
                asteroids.remove(collided);
                text.setText("Points: " + points.addAndGet(1000));
                pane.getChildren().remove(collided.getCharacter());
            });
            return true;
        }).collect(Collectors.toList());


        projectilesToRemove.forEach(projectile -> {
             pane.getChildren().remove(projectile.getCharacter());
            projectiles.remove(projectile);
        });


        if (Math.random() < 0.005) {
            Asteroid asteroid;
            asteroid = new Asteroid(WIDTH, HEIGHT);
                    
            if (!asteroid.checkCollision(ship)) {
                 asteroids.add(asteroid);
                 pane.getChildren().add(asteroid.getCharacter());
            }

        }

        removeProjectile(pane);
    }
 

    public static void main(String[] args) {
        launch(args);
    }
    
}
