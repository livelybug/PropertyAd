package com.kor.prpt.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp.DelegatingConnection;
import org.postgresql.PGConnection;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import com.kor.prpt.dao.DbConn;
import com.kor.prpt.dao.DbConnPropertyAgent;

/**
 * Servlet implementation class PicDispServlet
 */
@WebServlet("/PicDispServlet")
public class PicDispServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicDispServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       LargeObject obj = null;
       Connection conn = null;
       String imgID = null;
       long lobjID = 0;
       
       if ( request.getParameter("imgID") != null ){
       	imgID = request.getParameter("imgID");
       }
       else
       	return;

       lobjID = Long.parseLong(imgID, 10);
		try {
			DbConn dbObj = new DbConnPropertyAgent();
			dbObj.dbInit();
			conn = dbObj.dbConnect();
			conn.setAutoCommit(false);
			PGConnection pgCon = (PGConnection)((DelegatingConnection)conn).getInnermostDelegate();
	        LargeObjectManager lobj = pgCon.getLargeObjectAPI();
        	obj = lobj.open(lobjID, LargeObjectManager.READ);
			byte buf[] = new byte[obj.size()];
		    obj.read(buf, 0, obj.size());

			ImageInputStream myInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(buf));
			Iterator<ImageReader> readers = ImageIO.getImageReaders(myInputStream);
			ImageReader reader;
			String formatName;
		    if (readers.hasNext()) {
		    	reader = readers.next();
				formatName = reader.getFormatName();
					
		        response.setContentType(formatName);
	            response.setContentLength(buf.length);
	            response.getOutputStream().write(buf);
		    }	        
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
