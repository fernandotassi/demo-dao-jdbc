package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao
{
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn){this.conn = conn;}

	@Override
	public void insert(Seller sel) 
	{
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try
		 {
			 ps = conn.prepareStatement("insert into seller "
					                    + "(Name, Email, BirthDate, BaseSalary, DepartmentID) "
					                    + "values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			 ps.setString(1, sel.getName());
			 ps.setString(2, sel.getEmail());
			 ps.setDate(3, new java.sql.Date(sel.getBirthDate().getTime()));
			 ps.setDouble(4, sel.getBaseSalary());
			 ps.setInt(5, sel.getDepartment().getId());
			 
			int rows = ps.executeUpdate();
			if(rows > 0)
			{	
				rs = ps.getGeneratedKeys();
				if(rs.next())
				{	
					int id = rs.getInt(1);
					sel.setId(id);
				}	
			}
			else
				throw new DbException("erro! nenhuma linha afetada!");
		 }
		 catch(SQLException e)
		 {throw new DbException(e.getMessage());}
		 finally
		 {DB.closeResultSet(rs); DB.closeStatement(ps);}
	}

	@Override
	public void update(Seller sel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) 
	{
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try
	    {
	    	ps = conn.prepareStatement("select seller.*, department.Name as DepName "
	    			+ "from seller inner join department on seller.DepartmentId = department.Id "
	    			+ "where seller.Id = ?");
	        ps.setInt(1, id);
	        rs = ps.executeQuery();
	        if(rs.next())
	        {
	        	Department dep = instantiateDepartment(rs);
	        	Seller sel = instantiateSeller(rs, dep);
	        	
	        	return sel;	        	
	        }	
	        return null;
	    }
	    catch(SQLException e)
	    {throw new DbException(e.getMessage());}
	    finally
	    {DB.closeStatement(ps); DB.closeResultSet(rs);}
	}
	
	@Override
	public List<Seller> findAll() 
	{
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try
	    {
	    	 ps = conn.prepareStatement("select seller.*, department.Name as DepName "
	    			                    + "from seller inner join department on "
	    			                    + "seller.DepartmentId = department.Id "
	    			                    + "order by Id");
	    	 rs = ps.executeQuery();
	    	 List<Seller> list = new ArrayList<>();
	    	 while(rs.next())
	    	 {
	    		 Department dep = instantiateDepartment(rs);
	    		 Seller sel = instantiateSeller(rs, dep);
	    		 list.add(sel);
	    	 }	 
	    	/* Map<Integer, Department> map = new HashMap<>();
	    	 while(rs.next())
	    	 {	 
	    		 Department dep = map.get(rs.getInt("DepartmentId"));	    		 
	    		 if(dep == null)
	    		 {
	    			 dep = instantiateDepartment(rs);
	    			 map.put(rs.getInt("DepartmentId"), dep);
	    		 }	 
		     	 Seller sel = instantiateSeller(rs, dep);
	    	     list.add(sel);
	    	 }
	    	 */
	  
	    	 return list;
	    }
	    catch(SQLException e)
	    {throw new DbException(e.getMessage());}
	    finally
	    {DB.closeResultSet(rs); DB.closeStatement(ps);}
		
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException
	{
		Department dep = new Department();
    	dep.setId(rs.getInt("DepartmentId"));
    	dep.setName(rs.getString("DepName"));
    	return dep;
	}
	
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException
	{
		Seller sel = new Seller();
    	sel.setId(rs.getInt("Id"));
    	sel.setName(rs.getString("Name"));
    	sel.setEmail(rs.getString("Email"));
    	sel.setBirthDate(rs.getDate("BirthDate"));
    	sel.setBaseSalary(rs.getDouble("BaseSalary"));
    	sel.setDepartment(dep);
    	return sel;
	}

	@Override
	public List<Seller> findByDepartment(Department department)
	{
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try
		 {  
			 ps = conn.prepareStatement("select seller.*, department.Name as DepName "
					                    + "from seller inner join department on " 
					                    + "seller.DepartmentId = department.Id "
					                    + "where departmentId = ? order by Name");
			 ps.setInt(1, department.getId());
			 rs = ps.executeQuery();
			 List<Seller> list = new ArrayList<>();
			 Map<Integer, Department> map = new HashMap<>();
					 
			 while(rs.next())
			 {
				 Department dep = map.get(rs.getInt("DepartmentId"));
				 if(dep == null)
				 {
					 dep = instantiateDepartment(rs);
					 map.put(rs.getInt("DepartmentId"), dep);
				 }
				 Seller sel = instantiateSeller(rs, dep);
				 list.add(sel);
			 }	 		 
	         return list;
		 }
		 catch(SQLException e)
		 {throw new DbException(e.getMessage());}
		 finally
		 {DB.closeStatement(ps); DB.closeResultSet(rs);}
	}		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
      
}
