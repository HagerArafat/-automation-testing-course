
public class Calculator {

    public static double add(int a, int b) {
        return a + b;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double add(int a, double b) {
        return a + b;
    }

    public static double add(double a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(add(15,20));
        System.out.println(add(15, 20));
        System.out.println(add(15.5, 20.5));
        System.out.println(add(15, 20.5));
        System.out.println(add(15.5, 20));

    }
}
