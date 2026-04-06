import java.util.*;

class Prac{

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter : ");
        int n=sc.nextInt();
        String s="";
        for(int i=0;i<=n;i++){
         s=sc.nextLine();
        System.out.println("s is "+ s);
    }
        

    }
}
abc@gmail.com" → ("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$")
"abc@123.com" → ("^[a-zA-Z0-9._%+-]+@[0-9]+\\.[a-zA-Z]{2,}$")
"abc@gmail.c" → ("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{1,}$")
"abc@gmail.co" → ("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$")