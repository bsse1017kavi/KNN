package mainPackage;

import dataPackage.Tester;
import dataPackage.Trainer;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Trainer trainer = new Trainer();

        trainer.initialize();
        trainer.train();

        Tester tester = new Tester();

        System.out.println("Enter value of k: ");

        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();

        tester.test(k);
    }
}
