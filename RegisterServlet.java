

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/RegisterServlet")


public class RegisterServlet extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res)
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String marks = req.getParameter("marks");
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			String url = "jdbc:mysql://localhost:3306/demodb";
			String username = "root";
			String password = "1234";
			
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "insert into student values (?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setString(3, marks);
			
			String msg = "";
			int rows = pst.executeUpdate();
			if(rows > 0)
				msg = "Data Saved Success";
			else
				msg = "Failed to Save data";
			
			PrintWriter pw= res.getWriter();
			pw.println(msg);
			
			pst.close();
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
}
