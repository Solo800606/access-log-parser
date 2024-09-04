import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();
        // Вывод результата
        System.out.println("Сумма чисел: " + (firstNumber + secondNumber));
        System.out.println("Разность чисел: " + (firstNumber - secondNumber));
        System.out.println("Произведение чисел: " + (firstNumber * secondNumber));
        System.out.println("Частное чисел: " + ((double) firstNumber / secondNumber));
        // Запрашивается путь к файлу
        int count = 1;
        while (true) {
            System.out.println("Укажите путь к файлу");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExist = file.exists();
            boolean pathToFile = file.isDirectory();
            if (!fileExist || pathToFile) {
                System.out.println("Файл по указанному пути отсутствует или Вы указали путь к папке");
                continue;
            } else {
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер " + count + "\n");
                count++;
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    int totalLines = 0;
                    int maxLength = 0;
                    int minLength = Integer.MAX_VALUE;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (length > 1024) {
                            throw new LineTooLongException("ОБНАРУЖЕНА строка длиннее 1024 символов!!!");
                        }
                        totalLines++;
                        if (length > maxLength) {
                            maxLength = length;
                        }
                        if (length < minLength) {
                            minLength = length;
                        }
                    }
                    System.out.println("Общее количество строк в файле: " + totalLines);
                    System.out.println("Длина самой длинной строки в файле: " + maxLength);
                    System.out.println("Длина самой короткой строки в файле: " + minLength);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (LineTooLongException ex) {
                    System.out.println(ex.getMessage());
                    break;
                }
            }
        }
    }
}