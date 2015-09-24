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
import com.kor.prpt.domain.PropertyAgent;
import com.kor.prpt.vldt.UserLgVldt;

/**
 * Servlet implementation class PrptAgLgServlet
 */
@WebServlet("/PrptAgLgServlet")
public class PrptAgLgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrptAgLgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PropertyAgent propertyAgent1 = (PropertyAgent) session.getAttribute("propertyAgent1");

		UserLgVldt vldt =  new UserLgVldt(propertyAgent1);

		if(vldt.vldt() == false) {
			request.setAttribute("errorMsg", vldt.getVldtError());
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}
		else {
			BeanUtils bu = new BeanUtils();

			PropertyAgent propertyAgent2;
			try {
				propertyAgent2 = (PropertyAgent) bu.cloneBean(propertyAgent1);
			} catch (IllegalAccessException | InstantiationException
					| InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", "fail to put agent to session");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
				return;
			}
			
			session.setAttribute("propertyAgent2", propertyAgent2);
			
			session.removeAttribute("customer2");
			session.removeAttribute("propertyAgent1");
			
			response.sendRedirect("UserAction.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
