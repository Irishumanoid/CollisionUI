package UI.AsteroidsApplication;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Character {
    protected Polygon character;
    protected Point2D movement;

    public Character(Polygon polygon, int x, int y) {
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);

        this.movement = new Point2D(0, 0);
    }
    
    public Polygon getCharacter() {
        return character;
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D mvmnt) {
        this.movement = this.movement.add(mvmnt);
    }

    public void turnLeft() {
        this.character.setRotate(this.character.getRotate() - 5);
    }

    public void turnRight() {
        this.character.setRotate(this.character.getRotate() + 5);
    }

    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());

        if (this.character.getTranslateX() < 0) {
            this.character.setTranslateX(this.character.getTranslateX() +AsteroidsApplication.WIDTH);
        } else if (this.character.getTranslateX() > AsteroidsApplication.WIDTH) {
            this.character.setTranslateX(this.character.getTranslateX() % AsteroidsApplication.WIDTH);
        }

        if (this.character.getTranslateY() < 0) {
            this.character.setTranslateY(this.character.getTranslateY() + AsteroidsApplication.HEIGHT);
        }  else if (this.character.getTranslateX() > AsteroidsApplication.HEIGHT) {
            this.character.setTranslateY(this.character.getTranslateY() % AsteroidsApplication.HEIGHT);
        }
    }


    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(character.getRotate()));
        double changeY = Math.sin(Math.toRadians(character.getRotate()));
        
        changeX *= 0.05;
        changeY *= 0.05;

        this.movement = this.movement.add(changeX, changeY);
    }

    public boolean checkCollision(Character chr) {
        Shape collisionArea = Shape.intersect(chr.getCharacter(), this.character);
        return collisionArea.getBoundsInLocal().getWidth() != -1; //return true if an intersection exists
    }
    
}