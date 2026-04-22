package regularExpression_basics;

public class RegEXample {
	public static void main(String[] args) {
		String a="1";
		System.out.println(a.matches("\\d"));
		System.out.println("........................");
		
		String b="123";
		System.out.println(b.matches("\\d"));
		System.out.println(b.matches("\\d+"));
		System.out.println("........................");
		
		String c="";
		System.out.println(c.matches("\\d*"));
		System.out.println("........................");
		
		String d="12345";
		System.out.println(d.matches("^\\d+$"));
		System.out.println(d.matches("\\d+"));
		System.out.println(d.matches("\\d+$"));
		System.out.println("........................");
		
		
		String e="12345abc";
		System.out.println(e.matches("^\\d+$"));
		System.out.println(e.matches("\\d+"));
		System.out.println(e.matches("\\d+$"));
		System.out.println("........................");
		
		String f="123";
		System.out.println(f.matches("\\d{3}"));
		System.out.println(f.matches("\\d{4}"));
		
		System.out.println(f.matches("\\d{1,3}"));
		
		System.out.println("........................");
		String g="10.5";
		System.out.println(g.matches("^\\d+\\.\\d$"));
		System.out.println("........................");
		
		String h="10.5897";
		System.out.println(h.matches("^\\d+\\.\\d+$"));
		
		String i="10.56";
		System.out.println(i.matches("^\\d{1,3}\\.\\d{1,3}$"));
		
		
		
		
		
		
		
	}

}
