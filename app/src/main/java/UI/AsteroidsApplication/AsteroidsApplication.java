package UI.AsteroidsApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AsteroidsApplication extends Application {
    public static int WIDTH = 600;
    public static int HEIGHT = 400;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Text text = new Text(10, 20, "Points: 0");
        AtomicInteger points = new AtomicInteger();
        pane.setPrefSize(WIDTH, HEIGHT);

        Ship ship = new Ship(WIDTH/2, HEIGHT/2);
        List<Asteroid> asteroids = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            Asteroid ast = new Asteroid(rand.nextInt(WIDTH/3), rand.nextInt(HEIGHT/3));
            asteroids.add(ast);
        }

        pane.getChildren().addAll(text, ship.getCharacter());
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));

        Scene scene = new Scene(pane);


        Map<KeyCode, Boolean> keysPressed = new HashMap<>();
        
        scene.setOnKeyPressed(event -> {
            keysPressed.put(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased((event) -> {
            keysPressed.put(event.getCode(), Boolean.FALSE);
        });

        
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (keysPressed.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                } else if (keysPressed.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                } else if (keysPressed.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                } else if (keysPressed.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 4) {
                    Projectile projectile = new Projectile((int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectiles.add(projectile);

                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    pane.getChildren().add(projectile.getCharacter());
                } else if (keysPressed.getOrDefault(KeyCode.DOWN, false)) {
                    ship.deccelerate();
                }


                ship.move();
                asteroids.forEach(asteroid -> asteroid.move());
                projectiles.forEach(projectile -> projectile.move());

                asteroids.forEach(asteroid -> {
                    if (ship.checkCollision(asteroid)) {
                        stop();
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
            }
            
        }.start();


        stage.setTitle("Asteroid Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
