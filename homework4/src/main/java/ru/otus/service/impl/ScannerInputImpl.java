package ru.otus.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.service.ScannerInput;

import java.util.Scanner;

@Component
public class ScannerInputImpl implements ScannerInput {

    @Override
    public String scannerResult() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
