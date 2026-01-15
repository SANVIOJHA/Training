package filehandling;
import java.io.*;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Exa {
    public static void main(String[] args) throws IOException {
    	//create folder
		File folder = new File("D://CAPGEMENI_JAVA_FULLSTACK//file");
		folder.mkdir();
		
		//create file
		File f1 = new File(folder,"Student.txt");
		try {
			f1.createNewFile();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		File f2 = new File(folder,"Emp.txt");
		try {
			f2.createNewFile();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		if(folder.exists()) {
			File[] list = folder.listFiles();
			for (int i = 0; i < list.length; i++) {
				System.out.println(list[i].getName());
			}
		}
		
		//write data in file
		FileWriter writer = new FileWriter(f1);
		writer.write("Hello Student");
		writer.close();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f1));
		bw.write("Student count : "+40);
		bw.newLine();
		bw.write("Good Morning\n"
				+ "Have A Nice Day");
		bw.close();
		
		BufferedReader br = new BufferedReader(new FileReader(f1));
		String line;
		
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
		
		
		
		
	}
}