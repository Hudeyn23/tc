import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TransportCompany company = new TransportCompany();
        company.startCompany();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        company.stopCompany();
    }
}
