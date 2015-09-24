package com.kor.prpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.kor.prpt.dao.DbActionPropertyAgent;
import com.kor.prpt.domain.PropertyAgent;
import com.kor.prpt.vldt.PropertyAgentVldt;
import com.kor.prpt.vldt.UserVldt;

/**
 * Servlet implementation class PropertyAgtCtrlServlet
 */
@WebServlet("/PropertyAgtCtrlServlet")
public class PropertyAgtCtrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyAgtCtrlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PropertyAgent propertyAgent1 = (PropertyAgent) session.getAttribute("propertyAgent1");

		PrintWriter outP = response.getWriter();
		UserVldt vldt =  new PropertyAgentVldt(propertyAgent1);
		DbActionPropertyAgent dbAct = new DbActionPropertyAgent(propertyAgent1);
		
		if( vldt.validateUser() == false ){
			//outP.println(vldt.getVldtErr());
			request.setAttribute("errorMsg", vldt.getVldtErr());
			request.getRequestDispatcher("PropertyAgentRegisterForm.jsp").forward(request, response);
			//response.sendRedirect("");
		}
		else if ( dbAct.insertDb() == false){
			request.setAttribute("errorMsg", dbAct.getDbError());
			request.getRequestDispatcher("PropertyAgentRegisterForm.jsp").forward(request, response);

		}
		else {
			
			BeanUtils bu = new BeanUtils();

			try {
				PropertyAgent propertyAgent2 = (PropertyAgent) bu.cloneBean(propertyAgent1);
				session.setAttribute("propertyAgent2", propertyAgent2);				
			} catch (IllegalAccessException | InstantiationException
					| InvocationTargetException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errorMsg", "Fail to copy costomer info");
				request.getRequestDispatcher("CustomerRegisterForm.jsp").forward(request, response);
				return; 				
			}
						
			session.removeAttribute("propertyAgent1");
			session.removeAttribute("customer2");
			response.sendRedirect("UserAction.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		PropertyAgent propertyAgent1 = (PropertyAgent) session.getAttribute("propertyAgent1");

		PrintWriter outP = response.getWriter();
		UserVldt vldt =  new PropertyAgentVldt(propertyAgent1);
		DbActionPropertyAgent dbAct = new DbActionPropertyAgent(propertyAgent1);
		
		if( vldt.validateUser() == false ){
			//outP.println(vldt.getVldtErr());
			request.setAttribute("errorMsg", vldt.getVldtErr());
			request.getRequestDispatcher("PropertyAgentRegisterForm.jsp").forward(request, response);
			//response.sendRedirect("");
		}
		else if ( dbAct.insertDb() == false){
			outP.println(dbAct.getDbError());
		}
		else {
			session.removeAttribute("propertyAgent1");
			session.removeAttribute("customer2");
			response.sendRedirect("UserAction.jsp");
		}

	}

}
