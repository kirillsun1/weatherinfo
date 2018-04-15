package io;

import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readValueFromConsole(String message) {
        System.out.printf("%s ", message);
        return scanner.nextLine();
    }
}
