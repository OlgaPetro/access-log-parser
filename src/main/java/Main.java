import java.io.File;
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
        }
    }
}