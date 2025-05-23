package model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable
{

	private static final long serialVersionUID = 1L;
    
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private Integer id;
	private String name;
	private String email;
	private Date birthDate;
	private Double baseSalary;
	
	private Department department;
	
	public Seller(){}
	public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, 
			      Department department)
	{this.id = id; this.name = name; this.email = email; this.birthDate = birthDate; 
	 this.baseSalary = baseSalary; this.department = department;}
	
	public void setId(Integer id){this.id = id;}
	public void setName(String name){this.name = name;}
	public void setEmail(String email){this.email = email;}
	public void setBirthDate(Date birthDate){this.birthDate = birthDate;}
	public void setBaseSalary(Double baseSalary){this.baseSalary = baseSalary;}
	public void setDepartment(Department department){this.department = department;}
	
	public Integer getId(){return id;}
	public String getName(){return name;}
	public String getEmail(){return email;}
	public Date getBirthDate(){return birthDate;}
	public Double getBaseSalary(){return baseSalary;}
	public Department getDepartment(){return department;}
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString()
	{return "id: " + id + " - name: " + name + " - email: " + email + " - birthDate: " +
	         sdf.format(birthDate) + " \nsalary: " + baseSalary + " - department: " +
			 department;}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
