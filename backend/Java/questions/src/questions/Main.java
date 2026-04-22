package questions;

import java.util.Scanner;

public class Main {

    // first(3).1234last(3)@depart(hr,finance,it,admin).company.com
    public static String emailValidation(String e) {
        if(e.matches("^[a-z]{3,}\\.[0-9]{4,}[a-z]{3,}@(hr|finance|admin|it)\\.company\\.com$")) {
            return "valid";
        }
        return "invalid";
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); 

        for(int i = 0; i < n; i++) {
            String e = sc.nextLine();
            System.out.println(emailValidation(e));
        }

        sc.close();
    }
}