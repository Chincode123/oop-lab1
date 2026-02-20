package cars.trucks;

import cars.*;

import java.awt.*;

public class scania extends Car {

    // load in kg
    private final int maxLoad;
    private int currentLoad;

    // bed angle in degrees
    private double bedAngle;
    private final double minBedAngle, stableBedAngle, maxBedAngle;

    public scania(int maxLoad) {
        super("Scania", Color.blue, 100, 2, Math.PI / 6);
        this.maxLoad = maxLoad;
        this.minBedAngle = 0;
        this.maxBedAngle =  70;
        this.stableBedAngle =  0;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public double getMinBedAngle() {
        return minBedAngle;
    }

    public double getMaxBedAngle() {
        return maxBedAngle;
    }

    public double getStableBedAngle() {
        return stableBedAngle;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public double getBedAngle() {
        return bedAngle;
    }

    public void liftBed(double angle) {
        bedAngle += angle;
        if (getBedAngle() > getMaxBedAngle()) bedAngle = getMaxBedAngle();
    }

    public void lowerBed(double angle) {
        bedAngle -= angle;
        if (getBedAngle() < getMinBedAngle()) bedAngle = getMinBedAngle();
    }

    /**
     * Adds load, in kg, to truck if <em>load</em> is positive. If the new total load exceeds the max load, no operation is performed. If <em>load</em> is negative, load is removed using {@link #removeLoad(int)} and its result is returned.
     *
     * @see #getMaxLoad()
     * @see #getCurrentLoad()
     *
     * @param load load in kg
     * @return <em>true</em> if the operation is successful, otherwise <em>false</em>
     */
    public boolean addLoad(int load) {
        if (load < 0) return removeLoad(-load);
        if (currentLoad + load > maxLoad) return false;

        currentLoad += load;
        return true;
    }

    /**
     * Removes load, in kg, from truck if <em>load</em> is positive. If the new total load is less than <em>zero</em>, no operation is performed. If <em>load</em> is negative, load is added using {@link #addLoad(int)} and its result is returned.
     *
     * @see #getCurrentLoad()
     *
     * @param load load in kg
     * @return <em>true</em> if the operation is successful, otherwise <em>false</em>
     */
    public boolean removeLoad(int load) {
        if (load < 0) return addLoad(-load);
        if (currentLoad - load < 0) return false;

        currentLoad -= load;
        return true;
    }

    private double speedFactor() {
        return (getEnginePower() * getCurrentLoad()) / (getCurrentSpeed() * getMaxLoad());
    }

    @Override
    protected void incrementSpeed(double amount) {
        if (getBedAngle() != getStableBedAngle()) return;
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    @Override
    protected void decrementSpeed(double amount) {
        if (getBedAngle() != getStableBedAngle()) return;
        setCurrentSpeed(getCurrentSpeed() - (((double)getCurrentLoad() / (double)getMaxLoad()) * getCurrentSpeed() + speedFactor()) * amount);
    }
}
