package conditionalstatements;

import java.util.Scanner;

public class NumberOfDaysInMonth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter first three letter of month : ");
		String month=sc.nextLine();
		switch(month) {
		case "jan":System.out.print("31 days");
		break;
		case "feb":System.out.print("28 or 29 days");
		break;
		case "mar":System.out.print("31 days");
		break;
		case "apr":System.out.print("30 days");
		break;
		case "may":System.out.print("31 days");
		break;
		case "jun":System.out.print("30 days");
		break;
		case "jul":System.out.print("31 days");
		break;
		case "aug":System.out.print("31 days");
		break;
		case "sep":System.out.print("30 days");
		break;
		case "oct":System.out.print("31 days");
		break;
		case "nov":System.out.print("30 days");
		break;
		case "dec":System.out.print("31 days");
		break;
		default:System.out.print("invalid ");
		}
sc.close();
	}

}
