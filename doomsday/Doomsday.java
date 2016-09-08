import java.util.Scanner;

public class Doomsday {

    public static void main(String[] args) {

        Scanner userinput = new Scanner(System.in);
        int anchorday = 3; //anchorday for 1900s is Wednesday

        System.out.println("Welcome to the Doomsday Calculator!");

        System.out.print("What year are you looking for? ");
        int year = userinput.nextInt();

        System.out.print("What month (1-12)? ");
        int month = userinput.nextInt();

        System.out.print("What day? ");
        int day = userinput.nextInt();

        int twodigyear = year % 100;

        //Math to get Doomsady for year
        int div12 = twodigyear / 12;
        int mod12 = twodigyear % 12;
        int div2mod12 = mod12 / 4;
        int sum = div12 + mod12 + div2mod12 + anchorday;
        int doom = sum % 7;

        int finalday = 0;

        int [] arraymonths = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int [] leapyeardooms = {4, 29};
        int [] doomdayofmonth = {3, 28, 7, 4, 9, 6, 11, 8, 5, 10, 7, 12};

        for (int i = 0; i <= 11; i++) {
            if (year % 4 == 0 && (month == 1 || month == 2)) {     //Leap Year
                if (arraymonths[i] == month) {
                    finalday = ((day - leapyeardooms[i]) + doom + 35) % 7;
                }
            } else {
                if (arraymonths[i] == month) {
                    finalday = ((day - doomdayofmonth[i]) + doom + 35) % 7;
                }

            }
        }

        String [] weekday = {"Sunday", "Monday", "Tuesday", "Wednesday"
                            , "Thursday", "Friday", "Saturday"};
        String answer = weekday[finalday]; //converts int for weekday to String

        System.out.println(month + "/" + day + "/" + year
                           + " was on a " + answer);

    }
}