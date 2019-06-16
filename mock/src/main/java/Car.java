public class Car {
    String brand;
    String model;
    double engineCapacity;
    int amountSeats;
    String type;
    double costPerDay;

    public Car(String brand, String model, double engineCapacity, int amountSeats, String type, double costPerDay){
        this.brand = brand;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.amountSeats = amountSeats;
        this.type = type;
        this.costPerDay = costPerDay;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getAmountSeats() {
        return amountSeats;
    }

    public void setAmountSeats(int amountSeats) {
        this.amountSeats = amountSeats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }
}
