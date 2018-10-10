package xhj.wdc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import xhj.wdc.domain.Joinactivity;
import xhj.wdc.service.JoinActivityService;
import xhj.wdc.service.impl.JoinActivityServiceImpl;

/**
 * Servlet implementation class JoinActivityServlet
 */
@WebServlet("/servlet/joinActivityServlet")
public class JoinActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinActivityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("method");
		try {
			if (method.equals("add_joinActivity"))
				add_joinActivity(req, resp);
			else if (method.equals("quit_activity"))
				quit_activity(req, resp);
			else if (method.equals("display_joinActivity"))
				display_joinActivity(req, resp);

		} catch (NullPointerException e) {
			System.out.println("确实空了");
		}
	}

	private void display_joinActivity(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		JoinActivityService jas = new JoinActivityServiceImpl();

		String phone = req.getParameter("phone");
		List<Joinactivity> list =jas.lookJoinedActivity(phone);
		// System.out.println(list);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(JSON.toJSONString(list)); // 转换成JSON字符串然后用print输出
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(JSON.toJSONString(list));
	}

	private void quit_activity(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String activityid = req.getParameter("activityid");
		String phone=req.getParameter("phone");
		JoinActivityService jas = new JoinActivityServiceImpl();

		jas.quitActivity(phone, activityid);
		try {
			resp.sendRedirect(req.getContextPath() + "/jsp/user_center.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void add_joinActivity(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String activityid = req.getParameter("activityid");
		String phone = req.getParameter("phone");
		
		Joinactivity ja=new Joinactivity();
		ja.setCactivityid(activityid);
		ja.setCphone(phone);
		
		JoinActivityService jas=new JoinActivityServiceImpl();
		
		String message=jas.addJoinActivity(ja);
		if(message.equals(""))
			message="报名成功！";
		
		
		try {
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
