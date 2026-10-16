import java.util.Scanner;

public class GradeClassifier {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your score: ");
        int score = sc.nextInt();
        if (score >=90){
            System.out.println("Excellent");
        }else if (score >=75 ){
            System.out.println("Good");
        }else if (score >=60 ){
            System.out.println("Pass");
        }else {
            System.out.println("Fail");
        }
        System.out.println(" SWITCH:");

        int gradeGroup = score >= 90 ? 4 :
                         score >= 75 ? 3 :
                                 score >= 60 ? 2 : 1;

        switch (gradeGroup) {
            case 4 -> System.out.println("Excellent");
            case 3 -> System.out.println("Good");
            case 2 -> System.out.println("Pass");
            default -> System.out.println("Fail");
        }
        sc.close();


    }
}