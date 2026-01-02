package hasARelationship_lpu_multipledept;

public class Main {
	public static void main(String[] args) {
		Lpu l=new Lpu();
		for(int i=0;i<l.deptName.length;i++) {
			System.out.println(l.deptName[i].getDept());
		}
	}
}
