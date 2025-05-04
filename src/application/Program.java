package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Department;
import model.entities.Seller;

public class Program 
{
     public static void main(String[] args) throws ParseException 
     {		 
           Locale.setDefault(Locale.US);
           Scanner scan = new Scanner(System.in);
           Date d = new Date();
           System.out.println(d);
           
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           
           Department dep = new Department(1, "cama");
           System.out.println(dep); 
           
           Seller sel = new Seller(1, "bob", "bob@gmail.com", sdf.parse("1980-05-12"), 3200., dep);    
           System.out.println(sel);
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           scan.close();
	 }
}
