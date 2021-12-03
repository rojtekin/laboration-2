/**
 * A class with this interface should be able to load loadable vehicles
 */
public interface Load<T extends Car> {
    /**
     * loadCar should load the loadable vehicle
     * @param car The car that gets loaded
     */
    void loadCar(T car);

    /**
     * unloadCar should unload a vehicle that has been loaded
     */
    void unloadCar();

    /**
     * isCloseForLoad should check if the vehicle is close enough to be loaded
     * @param car The vehicle you want to load
     * @return returns a boolean which is true if car in range
     */
    boolean isCloseForLoad(Car car);
}
