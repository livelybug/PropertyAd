package com.kor.prpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kor.prpt.dao.DbActionProperty;
import com.kor.prpt.domain.Customer;
import com.kor.prpt.domain.Property;
import com.kor.prpt.domain.PropertyAgent;
import com.kor.prpt.vldt.PropertyVldt;
/**
 * Servlet implementation class PrptSrchServlet
 */
@WebServlet("/PrptSrchServlet")
public class PrptSrchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrptSrchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter outP = response.getWriter();
		Property property1 = (Property) request.getAttribute("property1");
		
		PropertyVldt vldt =  new PropertyVldt(property1);
		DbActionProperty dbAct = new DbActionProperty(property1);
		
		if( vldt.vldPrptSch() == false ){
			outP.println(vldt.getVldtErr());
		}
		else {
			ResultSet rs  = dbAct.selectDb();
			
			if(rs == null)
				outP.println(dbAct.getDbError());
			else {
				HttpSession session = request.getSession();
				PropertyAgent pAgt = (PropertyAgent) session.getAttribute("propertyAgent2");
				Customer cstm = (Customer) session.getAttribute("customer2");
				
				if(pAgt != null && pAgt.getUsername() != null) {
					request.setAttribute("AgentID", pAgt.getAgentID());
					request.setAttribute("loggedInUserName", pAgt.getUsername());
				}
				else if(cstm != null && cstm.getUsername() != null){
					request.setAttribute("loggedInUserName", cstm.getUsername());
				}
					
				request.setAttribute("prptRs", rs);
				request.getRequestDispatcher("PrptList.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
