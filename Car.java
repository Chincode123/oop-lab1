import java.awt.*;

public abstract class Car implements Movable {
    int nrDoors;
    double enginePower;
    double currentSpeed;
    Color color;
    String modelName;
    Point position;
    double angle;

    public void move() {
        position.translate(
                (int)(currentSpeed * Math.cos(angle)),
                (int)(currentSpeed * Math.sin(angle)));
    }

    public void turnLeft() {
        angle += Math.PI / 8;
        angle %= Math.PI * 2;
    }

    public void turnRight() {
        angle -= Math.PI / 8;
        angle %= Math.PI * 2;
    }

    public void gas(double amount) {
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        decrementSpeed(amount);
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public String getModelName() {
        return modelName;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    abstract void incrementSpeed(double amount);
    abstract void decrementSpeed(double amount);
}