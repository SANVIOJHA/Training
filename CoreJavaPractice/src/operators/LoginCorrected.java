package operators;

class LoginCorrected {
    public static void main(String[] args) {
        boolean correctUser = false;
        boolean correctPass = false;
        boolean locked = false;
        int attempts = 2;

        boolean login = correctUser && correctPass && !locked;
        attempts += login ? 0 : 1;
        locked = attempts >= 3 ? true : locked;

        System.out.println(login ? "Login Successful" : "Login Failed");
        System.out.println("Account Locked: " + locked);
    }
}
