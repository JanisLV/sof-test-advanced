public class Calculator {

    public double add(double a, double b){
        return a + b;
    }

    public double sub(double a, double b){
        return a - b;
    }

    public double multiply(double a, double b){
        return a * b;
    }

    public double divide(double a, double b) {
        if(b == 0){
            throw new IllegalArgumentException("Divide by 0");
        }
        return a / b;
    }

    public double reverseSign(double a){
        return -1 * a;
    }
}
