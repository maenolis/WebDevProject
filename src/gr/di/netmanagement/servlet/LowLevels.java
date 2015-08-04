package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.BatteryDataProcessor;
import gr.di.netmanagement.processdata.DataProcessor;
import gr.di.netmanagement.processdata.JsArgsProcessor;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BarDiagram.
 */
@WebServlet("/LowLevels")
public class LowLevels extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new low battery levels.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public LowLevels() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

//		HttpSession session = request.getSession();
//		DataProcessor dataProcessor = DataProcessor.getInstance(session);
//
//		/* map with dates as keys and lowlevels as value */
//		TreeMap<String, Float> lowLevels = BatteryDataProcessor
//				.getLowLevels(dataProcessor.getBatteryMap());
//		/* String[] dates as string(js argument) */
//		String dates = JsArgsProcessor.getArrayString(lowLevels
//				.keySet().toArray());
//		request.getSession().setAttribute("dates", dates);
//		/* Float[] user percentages found under 15% */
//		Float[] percentages = BatteryDataProcessor.convertToPercentages(
//				lowLevels, dataProcessor.getUsersSet().size());
//		request.getSession().setAttribute("percentages", percentages);
//		/* redirect to jsp with canvas presentation */
//		response.sendRedirect("LowLevels.jsp");
		
		HttpSession session = request.getSession();
		DataProcessor dataProcessor = DataProcessor.getInstance(session);
		System.out.println(BatteryDataProcessor.getLowLevels2(dataProcessor.getBatteryMap()));
	}

	/**
	 * Do post.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

	}
}
