import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void classSetup(){
        System.out.println("Started test at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
    }

    @AfterAll
    static  void classTeardown(){
        System.out.println("Finished test at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
    }

    @BeforeEach
    void setup(){
        calculator = new Calculator();
    }

    @Test
    void shouldReturnAdd() {
        //given
        Calculator calc = new Calculator();
        //when
        double result = calc.add(15, 8);
        //then
        assertEquals(23, result);
    }

    @RepeatedTest(3)
    public void shouldReturnAddRepeat(){
        //given
        Calculator calc = new Calculator();

        //when
        double result = calc.add(15, 8);

        //then
        assertEquals(23, result);
    }

    @Test
    public void shouldAcceptDivideByZero(){
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> calculator.divide(10,0));

        assertEquals("Divide by 0", exception.getMessage());
    }

    @Test
    public void shouldReturnMultiplicationOperation(){
        assertAll(
                () -> assertEquals(4, calculator.multiply(2,2)),
                () -> assertEquals(81, calculator.multiply(9,9)),
                () -> assertEquals(30, calculator.multiply(5,6))
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = { 10.0, -23.0, 12.0, -2.0 })
    public void shouldReturnReverseSign(double a){
        assertEquals(-1 * a, calculator.reverseSign(a));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void shouldReturnReverseSing2(double a){
        assertEquals(-1 * a, calculator.reverseSign(a));
    }

    static Stream<Arguments> getParameters(){
        return Stream.of(Arguments.of(1.0, -231.0, 26.0, -98.0, 100.0));
    }

    @ParameterizedTest
    @ArgumentsSource(Parameters.class)
    public void shouldReturnReverseSing3(double a){
        assertEquals(-1 * a, calculator.reverseSign(a));
    }

    static class Parameters implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) {
            return Stream.of(Arguments.of(23.0, -56.0, 64.92, -0.32));
        }
    }

    @ParameterizedTest
    @CsvSource({"10.0, 10.0, 20.0", "13.4, 2.9, 16.3", "123.2, 5.3, 128.5"})
    public void shouldReturnAdditionOperation(double a, double b, double sum){
        assertEquals(sum, calculator.add(a, b));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataSource.csv")
    public void shouldReturnSubtractionOperation(double a, double b, double sub){
        assertEquals(sub, calculator.sub(a, b));
    }

    @ParameterizedTest
    @ValueSource(strings = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"}) // Pssing strings
    void someMonths_Are30DaysLong(Month month) {
        final boolean isALeapYear = false;
        assertEquals(30, month.length(isALeapYear));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "D", "16"})
    public void shouldReturnIntToHex(@ConvertWith(HexToInt.class) int a){
        assertEquals(-1 * a, calculator.reverseSign(a));
    }

    static class HexToInt extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object o, Class<?> targetType){
            assertEquals(int.class, targetType, "Can only convert to int");
            return Integer.decode("0x" + o.toString());
        }
    }

}
