import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        int count = 0;

        while (true) {
            System.out.print("Введите путь к файлу: ");
            String path = new Scanner(System.in).nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (isDirectory) {
                System.out.println("Введенный путь является папкой.");
                continue;
            }

            if (!fileExists) {
                System.out.println("Введенный файл не существует.");
                continue;
            }
            count++;

            System.out.println("Введенный путь к файлу указан верно.");
            System.out.println("Это название файла номер " + count);


            int totalLines = 0;
            int maxLength = 0;
            int minLength = Integer.MAX_VALUE;

            FileReader fileReader;
            try {
                fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    totalLines++;
                    int length = line.length();
                    if (length > 1024) {
                        throw new OperationAttemptException("Длина текста в строке " + totalLines + " более 1024 символов");
                    }
                    if (length > maxLength) {
                        maxLength = length;
                    }
                    if (length < minLength) {
                        minLength = length;
                    }
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new OperationAttemptException("Файл не найден");
            } catch (IOException e) {
                throw new OperationAttemptException("Ошибка ввода или вывода");
            } catch (OperationAttemptException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Количество строк в файле: " + totalLines);
            System.out.println("Длина самой длинной строки в файле: " + maxLength + " символов");
            System.out.println("Длина самой короткой строки в файле: " + minLength + " символов");

        }
    }

}
