import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarRentalTest {

    @Mock
    Agency agencyMock;

    @Test
    void verifyExample1() {
        agencyMock.findCar(5, "sedan");

        verify(agencyMock).findCar(5, "sedan");
    }

    @Test
    void verifyExample2() {
        agencyMock.findCar(5, "sedan");

        verify(agencyMock, atMost(10)).findCar(any(Integer.class), anyString());
    }

    @Test
    void verifyExample3() {
        agencyMock.findCar(5, "sedan");

        verify(agencyMock, atLeastOnce()).findCar(eq(5), startsWith("sed"));
    }

    @Test
    void whenExample1() {
        List<Car> cars = agencyMock.findCar(5, "sedan");

        assertNotNull(cars);
        assertTrue(cars.isEmpty());
    }

    @Test
    void whenExample2() {
        Car someCar = new Car("Ford", "Focus", 2.0, 5, "sedan", 100.0);
        when(agencyMock.findCar(5, "sedan"))
                .thenReturn(Collections.singletonList(someCar));

        List<Car> flight = agencyMock.findCar(5, "sedan");

        assertEquals(1, flight.size());
        assertEquals(someCar, flight.get(0));
    }

}
