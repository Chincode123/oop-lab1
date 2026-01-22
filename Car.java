import java.awt.*;

public abstract class Car implements Movable {
    public static class Position {
        public double x, y;

        public Position() {
            x = 0;
            y = 0;
        }
    }

    int nrDoors;
    double enginePower;
    double currentSpeed;
    Color color;
    String modelName;
    Position position;
    double angle;

    public Car(String modelName, Color color, double enginePower, int nrDoors) {
        this.modelName = modelName;
        this.color = color;
        this.enginePower = enginePower;
        this.nrDoors = nrDoors;
        this.position = new Position();
    }

    public void move() {
        position.x += currentSpeed * Math.cos(angle);
        position.y += currentSpeed * Math.sin(angle);
    }

    private double normalizeAngle(double angle) {
        while (angle >= 2 * Math.PI) angle -= 2 * Math.PI;
        while (angle < 0) angle += 2 * Math.PI;
        if (angle >= 2 * Math.PI - 0.01) angle = 0;
        return angle;
    }

    public void turnLeft() {
        angle += Math.PI / 8;
        angle = normalizeAngle(angle);
    }

    public void turnRight() {
        angle -= Math.PI / 8;
        angle = normalizeAngle(angle);
    }

    public double getAngle() {
        return angle;
    }

    public Position getPosition() {
        return position;
    }

    public void gas(double amount) {
        double old_speed = currentSpeed;
        incrementSpeed(Math.clamp(amount, 0, 1));
        currentSpeed = Math.clamp(currentSpeed, old_speed, enginePower);
    }

    public void brake(double amount) {
        double old_speed = currentSpeed;
        decrementSpeed(Math.clamp(amount, 0, 1));
        currentSpeed = Math.clamp(currentSpeed, 0, old_speed);
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