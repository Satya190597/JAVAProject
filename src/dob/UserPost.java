package dob;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UserPost")
@MultipartConfig
public class UserPost extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		pw.println("Please Wait....");
		Post Post_obj=new Post();
		UserPost UserPost_obj= new UserPost();
		String userId=request.getSession(false).getAttribute("userId").toString();
		Post_obj.setUserId(Integer.parseInt(userId));
		Post_obj.setName(request.getParameter("postName"));
		Post_obj.setBody(request.getParameter("postBody"));
		Post_obj.setCategory(Integer.parseInt(request.getParameter("postCategory")));
		Post_obj.setAboutWork(request.getParameter("postAboutYourWork"));
		Post_obj.setImage1(UserPost_obj.getInputStream(request.getPart("postImage1")));
		Post_obj.setImage2(UserPost_obj.getInputStream(request.getPart("postImage2")));
		Post_obj.setVideo1(UserPost_obj.getInputStream(request.getPart("postVideo1")));
		InsertPost InsertPsot_obj=new InsertPost();
		int flag=InsertPsot_obj.Post(Post_obj);
		if(flag>0)
		{
			pw.println("Data Saved !");
		}
	}
	protected InputStream getInputStream(Part content)
	{
		InputStream fileContent=null;
		try
		{
			if(content!=null)
			{
				fileContent=content.getInputStream();
			}
		}
		catch(Exception e)
		{
			
		}
		return fileContent;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
