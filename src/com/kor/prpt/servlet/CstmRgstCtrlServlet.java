package com.kor.prpt.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.kor.prpt.dao.DbActionCustomer;
import com.kor.prpt.domain.Customer;
import com.kor.prpt.vldt.CustomerVldt;
import com.kor.prpt.vldt.UserVldt;
/**
 * Servlet implementation class CstmRgstCtrlServlet
 */
@WebServlet("/CstmRgstCtrlServlet")
public class CstmRgstCtrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CstmRgstCtrlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
/*		Customer customer1 = (Customer) session.getAttribute("customer1");
*/
		Customer customer1 = new Customer();
		customer1.setUsername(request.getParameter("username"));
		customer1.setPassword(request.getParameter("password"));
		customer1.setMobile(request.getParameter("mobile"));
		customer1.setEmail(request.getParameter("email"));
		customer1.setAddress(request.getParameter("address"));
		
		//PrintWriter outP = response.getWriter();
		UserVldt vldt =  new CustomerVldt(customer1);
		DbActionCustomer dbAct = new DbActionCustomer(customer1);
		
		if( vldt.validateUser() == false ){
			request.setAttribute("errorMsg", vldt.getVldtErr());
			request.getRequestDispatcher("CustomerRegisterForm.jsp").forward(request, response);			
		}
		else if ( dbAct.insertDb() == false){
			request.setAttribute("errorMsg", dbAct.getDbError());
			request.getRequestDispatcher("CustomerRegisterForm.jsp").forward(request, response);			
		}
		else {
			
			//BeanUtils bu = new BeanUtils();
			try {
				Customer customer2 = (Customer) BeanUtils.cloneBean(customer1);
				session.setAttribute("customer2", customer2);
			} catch (IllegalAccessException | InstantiationException
					| InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", "Fail to copy costomer info");
				request.getRequestDispatcher("CustomerRegisterForm.jsp").forward(request, response);
				return; //?? needed? no effect
			}
			//customer2 = customer1;
			
			session.removeAttribute("propertyAgent2");
			//session.removeAttribute("customer1");
			
			response.sendRedirect("UserAction.jsp");
		}
		
	}

}
