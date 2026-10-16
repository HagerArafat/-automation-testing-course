public class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("BothTools");
            } else if (i % 3 == 0) {
                System.out.println("Selenium");
            } else if (i % 5 == 0) {
                System.out.println("Playwright");
            } else {
                System.out.println(i);
            }
        }
    }
}