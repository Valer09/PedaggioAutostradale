package Model.Interfaces;

/**
 * The interface IVehicla defines a pattern for vehicles modeling even for different  vehicle classes types
 *  as for light and heavy vehicles.
 */

public interface IVehicle {


    /**
     * Get Vehicle License Plate
     * @return String
     */
    String getVLI();
    // return VLI (Vehicle License Plate: targa)

    /**
     * Get Vehicle Model
     * @return String
     */
    String getModel();

    /**
     * Get Vehicle Brand
     * @return String
     */
    String getBrand();

    /**
     * Get Vehicle Class
     * @return
     */
    char   getVclass();
    // return Vehicle class

    /**
     * Get Vehicle Year
     * @return int
     */
    int    getYear();

    /**
     * Get Vehicle axes
     * @return int
     */
    int    getAxes();
    // return number of axes

    /**
     * Get Vehicle Weight
     * @return double
     */
    double getWeight();
    //meters

    /**
     * Get Vehicle Height
     * @return double
     */
    double getHeight();
    //meters

    /**
     * Get EURO class of vehicle
     * @return int
     */
    int getEURO();

    /**
     * Set Vehicle Height
     * @param height
     */
    void setHeight(double height);

    /**
     * Set Vehicle Weight
     * @param weight
     */
    void setWeight(double weight);

    /**
     * Set Vehicle Year
     * @param year
     */
    void setYear(int year);

    /**
     * Set Vehicle VLI
     * @param VLI
     */
    void setVLI(String VLI);

    /**
     * Set Vehicle  Brand
     * @param brand
     */
    void setBrand(String brand);

    /**
     * Set Vehicle  Model
     * @param model
     */
    void setModel(String model);


}
