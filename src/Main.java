import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();
//Вывод результата
        System.out.println("Сумма чисел:" + (firstNumber + secondNumber));
        System.out.println("Разность чисел:" + (firstNumber - secondNumber));
        System.out.println("Произведение чисел:" + (firstNumber * secondNumber));
        System.out.println("Частное чисел:" + ((double) firstNumber / secondNumber));
//Запрашивается путь к файлу
        int count = 1;
        while (true) {
            System.out.println("Укажите путь к файлу");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExist = file.exists();
            boolean pathToFile = file.isDirectory();
            if (!fileExist || pathToFile) {
                System.out.println("Файл по указаному пути отсутствует или Вы указали путь к папке");
                continue;
            } else {
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер " + count + "\n");
                count++;
            }
        }
    }
}