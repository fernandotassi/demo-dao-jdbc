package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
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
           
           SellerDao sellerDao = DaoFactory.createSellerDao();
           System.out.println("=====test findById=====");
           Seller selo = sellerDao.findById(scan.nextInt());
           System.out.println();
           System.out.println(selo);
           
           System.out.println("=====test findByDepartment=====");
           Department depo = new Department();
           depo.setId(scan.nextInt());
           List<Seller> list = sellerDao.findByDepartment(depo);
           System.out.println();
           for(Seller s: list) 
           {
        	   System.out.println(s);
        	   System.out.println();
           }
           System.out.println("=====findAll=====");
           list = sellerDao.findAll();
           for(Seller l: list)
        	   System.out.println(l);
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           scan.close();
	 }
}
