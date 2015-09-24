package com.kor.prpt.servlet;

import java.io.IOException;
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
import com.kor.prpt.vldt.UserLgVldt;

/**
 * Servlet implementation class CstmLgServlet
 */
@WebServlet("/CstmLgServlet")
public class CstmLgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CstmLgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer1 = (Customer) session.getAttribute("customer1");
		if(customer1 == null) {
			request.setAttribute("errorMsg", "maybe cookies disabled");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;			
		}
		
		UserLgVldt vldt =  new UserLgVldt(customer1);

		if(vldt.vldt() == false) {
			request.setAttribute("errorMsg", vldt.getVldtError());
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}
		else {
			BeanUtils bu = new BeanUtils();

			Customer customer2;
			try {
				customer2 = (Customer) bu.cloneBean(customer1);
			} catch (IllegalAccessException | InstantiationException
					| InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", "fail to put customer to session");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
				return;
			}
			
			session.setAttribute("customer2", customer2);
			
			session.removeAttribute("propertyAgent2");//?? after a user is regieter, remove the session of previous user, is this the correct way?
			session.removeAttribute("customer1");
			
			response.sendRedirect("UserAction.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
