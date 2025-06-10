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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Hello,You are Welcome to semi colon's Mensturation App");
        System.out.println("we would love to know you, create profile with us");

        System.out.print("so, what your name?: ");
        String name = input.nextLine();

        System.out.print("Your age please: ");
        int age = Integer.parseInt(input.nextLine());

        System.out.print("What your blood group?: ");
        String bloodGroup = input.nextLine();

        System.out.print("What your genotype?: ");
        String genotype = input.nextLine();

        CreateaProfile profile = new CreateaProfile(name, age, bloodGroup, genotype);
        profile.displayProfile();

        System.out.print("what date was your first day of your last  menstrural period (yyyy-mm-dd): ");
        String lastPeriod = input.nextLine();

        String[] dateParts = lastPeriod.split("-");

        int year = Integer.parseInt(dateParts[0]);

        int month = Integer.parseInt(dateParts[1]);

        int day = Integer.parseInt(dateParts[2]);

        System.out.print("how many days of period do you experience on a average: ");

        int periodDuration = input.nextInt();

        System.out.print("what is your average cycle length in days: ");

        int cycleLength = input.nextInt();

        String nextPeriodStart = addDays(day, month, year, cycleLength);

        String nextPeriodEnd = addDays(day, month, year, cycleLength + periodDuration - 1);

        String ovulationDay = addDays(day, month, year, cycleLength - 14);

        String fertileStart = addDays(day, month, year, cycleLength - 14 - 5);

        String fertileEnd = addDays(day, month, year, cycleLength - 14 + 1);

        System.out.println("Next Period Start: " + nextPeriodStart);

        System.out.println("Expected to End: " + nextPeriodEnd);

        System.out.println("Ovulation Day: " + ovulationDay);

        System.out.println("Fertile Window: " + fertileStart + " to " + fertileEnd);

        System.out.println("Safe Sex Days: Before " + fertileStart + " and After " + fertileEnd);

        saveCycleToHistory(lastPeriod, cycleLength, periodDuration, nextPeriodStart);

        System.out.println("Your Cycle History:");
        showHistory();
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
            System.out.println("kindly create a profile for yourself");

            System.out.println("Name: " + name);

            System.out.println("Age: " + age);

            System.out.println("Blood Group: " + bloodGroup);

            System.out.println("Genotype: " + genotype);

            ZonedDateTime nigeriaTime = ZonedDateTime.now(ZoneId.of("Africa/Lagos"));

            String localTime = nigeriaTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            System.out.println("Current time in Nigeria: " + localTime);
        }
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
            for (String line : lines) {
                System.out.println("- " + line);
            }
        } catch (IOException e) {
            System.out.println("No cycle history found yet.");
        }
    }
}
























	

