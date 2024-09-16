import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите 2 числа через запятую: ");
        String input = scanner.nextLine();
        String[] numbers = input.split(", ");
        int num1 = Integer.parseInt(numbers[0].trim());
        int num2 = Integer.parseInt(numbers[1].trim());

        System.out.println(sum(num1, num2));
    }

    public static int sum(int x1, int x2) {
        return x1 + x2;
    }
}
