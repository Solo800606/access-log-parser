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
        // Работа с файлом
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    int totalLines = 0;
                    int googlebotCount = 0;
                    int yandexbotCount = 0;
                    while ((line = reader.readLine()) != null) {
                        if (line.length() > 1024) {
                            throw new LineTooLongException("ОБНАРУЖЕНА строка длиннее 1024 символов!!!");
                        }
                        totalLines++;
        // Разделение строки на составляющие
                        String[] parts = line.split("\"");
                        if (parts.length > 5) {
                            String userAgent = parts[5];
        // Обработка фрагмента User-Agent
                            int start = userAgent.indexOf('(');
                            int end = userAgent.indexOf(')');
                            if (start != -1 && end != -1) {
                                String firstBrackets = userAgent.substring(start + 1, end);
                                String[] uaParts = firstBrackets.split(";");
                                if (uaParts.length >= 2) {
                                    String fragment = uaParts[1].trim();
                                    String program = fragment.split("/")[0].trim();
                                    if (program.equals("Googlebot")) {
                                        googlebotCount++;
                                    } else if (program.equals("YandexBot")) {
                                        yandexbotCount++;
                                    }
                                }
                            }
                        }
                    }
        // Инфа по запросам от YandexBot и Googlebot + Общее количество строк
                    System.out.println("Общее количество строк в файле: " + totalLines);
                    double googlebotShare = (double) googlebotCount / totalLines * 100;
                    double yandexbotShare = (double) yandexbotCount / totalLines * 100;
                    System.out.println("Доля запросов от Googlebot: " + googlebotShare + "%");
                    System.out.println("Доля запросов от YandexBot: " + yandexbotShare + "%");

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