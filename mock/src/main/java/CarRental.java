import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CarRental {
    private final Agency[] agencies;

    public CarRental(Agency... agencies) {
        this.agencies = agencies;
    }

    public Car findCheapestCar(int amoutnSeats, String type) {
        Optional<Car> cheapestCar = Arrays.stream(agencies)
                .map(a -> {
                        return a.findCar(amoutnSeats, type);
                })
                .flatMap(List::stream)
                .min(Comparator.comparing(Car::getCostPerDay));

        return cheapestCar.orElse(null);
    }
}
