package filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Serialization_e implements Serializable{
	
	String name;
	 int id;
	 Serialization_e(){
		 
	 }
 Serialization_e(String name,int id){
	 this .name=name;
	 this.id=id;
		 
	 }
 

	public static void main(String[] args) throws Exception{
		
		Serialization_e s=new Serialization_e("abc",678);
		//data.ser if access is denied 
		File f=new File("D:/CAPGEMENI_JAVA_FULLSTACK/file/data.ser");
		try {
		f.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		//serialization
		FileOutputStream fouput=new FileOutputStream(f);
		ObjectOutputStream out=new ObjectOutputStream(fouput);
		out.writeObject(s);
		
		out.close();
		
		//deserialization
		
		FileInputStream finput=new FileInputStream(f);
		ObjectInputStream in=new ObjectInputStream(finput);
		Serialization_e s1=(Serialization_e)in.readObject();
		System.out.println(s1.name);
		System.out.println(s1.id);
		
		

	}

}
