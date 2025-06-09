import java.time.ZonedDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MensturalApp {
    public static void main(String[] args) {
	
	System.out.println("Hello,You are Welcome to semi colon's Mensturation App");
	System.out.print("we would love to know you, create profile with us") 

        Scanner input = new Scanner(System.in);

        System.out.print("so, what your name: ");
        String name = input.nextLine();

        System.out.print("Your age please: ");
        int age = Integer.parseInt(input.nextLine());

        System.out.print("What your blood group,dont worry everything is fine: ");
        String bloodGroup = input.nextLine();

        System.out.print("What your genotype: ");
        String genotype = input.nextLine();

        CreateaProfile profile = new CreateaProfile(name, age, bloodGroup, genotype);
        profile.displayProfile();
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
	

	System.out.print("what date was your first day of your menstrural period");
	 int periodLength = input.nextInt();

	System


    }


}























	

