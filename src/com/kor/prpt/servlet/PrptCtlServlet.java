package com.kor.prpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.kor.prpt.dao.DbActionProperty;
import com.kor.prpt.domain.Property;
import com.kor.prpt.domain.PropertyAgent;
import com.kor.prpt.vldt.PropertyVldt;
/**
 * Servlet implementation class PrptCtlServlet
 */
@WebServlet("/PrptCtlServlet")
@MultipartConfig
public class PrptCtlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrptCtlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("propertAction");	
		if(action != null && action.equals("submitted")){
			
			Property property1 = new Property();
			try {
				BeanUtils.populate(property1, request.getParameterMap());
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", "Invalid data");
				request.getRequestDispatcher("PropertyForm.jsp").forward(request, response);
			}
			
			HttpSession session = request.getSession();
			PropertyAgent propertyAgent2 = (PropertyAgent) session.getAttribute("propertyAgent2");
	 		PropertyVldt vldt =  new PropertyVldt(property1, propertyAgent2);
	 		
	 		DbActionProperty dbAct = null;
	 		Part part = request.getPart("image");
	 		if(part.getInputStream().available() > 0){
				property1.setImgFile(part);
				dbAct = new DbActionProperty(property1, propertyAgent2, part);
			}
	 		else{
	 			dbAct = new DbActionProperty(property1, propertyAgent2);
	 		}
			
			if( vldt.validateProperty() == false ){
				request.setAttribute("errorMsg", vldt.getVldtErr());
				request.getRequestDispatcher("PropertyForm.jsp").forward(request, response);
			}
			else if ( dbAct.insertDb() == false){
				request.setAttribute("errorMsg", dbAct.getDbError());
				request.getRequestDispatcher("PropertyForm.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("Good.jsp").forward(request, response);
			}
		}
		else{
			response.sendRedirect("PropertyForm.jsp");
		}
	}

}
