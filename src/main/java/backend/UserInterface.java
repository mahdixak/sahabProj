package backend;

import java.util.Scanner;

public class UserInterface extends Thread{
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void run() {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.matches(Commands.show_errors.command))
                showErrors();
            else if (command.matches(Commands.show_warnings.command))
                showWarnings();
            else if (command.matches(Commands.last_10_logs.command))
                last10Commands();
            else if (command.matches(Commands.last_50_logs.command))
                last50Commands();
            else if (command.matches(Commands.last_100_logs.command))
                last100Commands();
        }
    }

    private void last100Commands() {
    }

    private void last10Commands() {
    }

    private void last50Commands() {
    }

    private void showWarnings() {

    }

    private void showErrors() {
    }

}
