import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class MensturalApp {
    static int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
    static CreateaProfile profile;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Hello, You are Welcome to Semicolon’s Menstruation App");
        System.out.println("We would love to know you, create a profile with us");

        System.out.print("So, what is your name?: ");
        String name = input.nextLine();

        System.out.print("Your age please: ");
        int age = Integer.parseInt(input.nextLine());

        System.out.print("What is your blood group?: ");
        String bloodGroup = input.nextLine();

        System.out.print("What is your genotype?: ");
        String genotype = input.nextLine();

        profile = new CreateaProfile(name, age, bloodGroup, genotype);

        boolean running = true;
        while (running) {
            System.out.println("Semicolon’s Menstruation Menu");
            System.out.println("1. View Profile");
            System.out.println("2. Track a New Cycle");
            System.out.println("3. View Cycle History");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    profile.displayProfile();
                    break;

                case "2":
                    trackCycle(input);
                    break;

                case "3":
                    showHistory();
                    break;

                case "4":
                    running = false;
                    System.out.println("Thank you for using Semicolon’s Menstrual App.");
                    break;

                default:
                    System.out.println("Invalid option.only number 1-4 are valid options.");
            }
        }
    }

    private static class CreateaProfile {
        private String name;
        private int age;
        private String bloodGroup;
        private String genotype;

        public CreateaProfile(String name, int age, String bloodGroup, String genotype) {
            this.name = name;
            this.age = age;
            this.bloodGroup = bloodGroup;
            this.genotype = genotype;
        }

        public void displayProfile() {
            System.out.println("Profile:");
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Blood Group: " + bloodGroup);
            System.out.println("Genotype: " + genotype);

            ZonedDateTime nigeriaTime = ZonedDateTime.now(ZoneId.of("Africa/Lagos"));
            String localTime = nigeriaTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            System.out.println("Current time in Nigeria: " + localTime);
        }
    }

    static void trackCycle(Scanner input) {
        System.out.print("Enter the first day of your last menstrual period (yyyy-mm-dd): ");
        String lastPeriod = input.nextLine();
        String[] dateParts = lastPeriod.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        System.out.print("How many days of period do you experience on average?: ");
        int periodDuration = Integer.parseInt(input.nextLine());

        System.out.print("What is your average cycle length in days?: ");
        int cycleLength = Integer.parseInt(input.nextLine());

        String yourNextPeriodStart = addDays(day, month, year, cycleLength);
        String yourNextPeriodEnd = addDays(day, month, year, cycleLength + periodDuration - 1);
        String ovulationDays = addDays(day, month, year, cycleLength - 14);
        String fertileDaysStart = addDays(day, month, year, cycleLength - 14 - 5);
        String fertileDaysEnd = addDays(day, month, year, cycleLength - 14 + 1);

        System.out.println("Next Period Start: " + yourNextPeriodStart);
        System.out.println("Should End: " + yourNextPeriodEnd);
        System.out.println("Ovulation Day: " + ovulationDays);
        System.out.println("Fertile Days: " + fertileDaysStart + " to " + fertileDaysEnd);
        System.out.println("Safe Sex Days: Before " + fertileDaysStart + " and After " + fertileDaysEnd);

        saveCycleToHistory(lastPeriod, cycleLength, periodDuration, yourNextPeriodStart);
    }

    static String addDays(int day, int month, int year, int daysToAdd) {
        daysInMonth[1] = 28;
        while (daysToAdd > 0) {
            int daysLeftInMonth = daysInMonth[month - 1] - day;
            if (daysToAdd <= daysLeftInMonth) {
                day += daysToAdd;
                daysToAdd = 0;
            } else {
                daysToAdd -= (daysLeftInMonth + 1);
                day = 1;
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
        }
        return year + "-" + format(month) + "-" + format(day);
    }

    static String format(int num) {
        return num < 10 ? "0" + num : String.valueOf(num);
    }

    static void saveCycleToHistory(String lastPeriod, int cycleLength, int periodDuration, String nextPeriodStart) {
        try (FileWriter writer = new FileWriter("cycle_history.txt", true)) {
            writer.write("Last Period: " + lastPeriod + ", Cycle Length: " + cycleLength + ", Period Duration: " + periodDuration + ", Next Period Start: " + nextPeriodStart + "\r\n");
        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }

    static void showHistory() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("cycle_history.txt"));
            if (lines.isEmpty()) {
                System.out.println("No cycle history found yet.");
                return;
            }
            for (String line : lines) {
                System.out.println("- " + line);
            }
        } catch (IOException e) {
            System.out.println("No cycle history found yet.");
        }
    }
}























	

