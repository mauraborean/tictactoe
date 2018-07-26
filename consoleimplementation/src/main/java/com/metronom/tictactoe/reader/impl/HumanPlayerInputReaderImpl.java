package com.metronom.tictactoe.reader.impl;

import java.util.Scanner;

import com.metronom.tictactoe.reader.InputReader;

public class HumanPlayerInputReaderImpl implements InputReader {

    private Scanner scanner;

    public HumanPlayerInputReaderImpl() {
	scanner = new Scanner(System.in);
    }

    @Override
    public String getInput(char playerId) {
	return scanner.nextLine();
    }

}
