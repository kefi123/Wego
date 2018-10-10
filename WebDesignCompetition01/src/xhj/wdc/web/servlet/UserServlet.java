package xhj.wdc.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import xhj.wdc.domain.User;
import xhj.wdc.service.UserService;
import xhj.wdc.service.impl.UserServiceImpl;
import xhj.wdc.util.GetPropertiesUtil;
import xhj.wdc.util.ServiceUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/servlet/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("method");
		if (method.equals("login"))
			login(req, resp);
		else if (method.equals("logoff")) {
			logoff(req, resp);
		} else if (method.equals("register")) {
			register(req, resp);
		}
	}

	// 注册
	public void register(HttpServletRequest req, HttpServletResponse resp) {
		// User user = new User();
		// user.setUsername(req.getParameter("username"));
		// user.setNickname(req.getParameter("nickname"));
		// user.setPhone(req.getParameter("phone"));
		// user.setSex(req.getParameter("sex"));
		// user.setPassword(req.getParameter("password"));
		// user.setRealname(req.getParameter("realname"));
		// user.setProfilepic(req.getParameter("profilepic"));

		// 创建一个实体类，用于之后存储表单元素的值
		User user = new User();
		// 得到文件的上传目录，
		// 在这里需要普及一下，因为我们这里上传的是图片，以后还需要用，
		// 但是如果我们上传的是文件，那么就应该放在web-inf下，不允许外界访问，保护数据的安全

		// getServletContext()方法用于获得上下文，getRealPath()用于得到上下文的真是路径
		String savePath = this.getServletContext().getRealPath("/ProfilePic");
		// 上传时生成的临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// 创建临目录
			tmpFile.mkdir();
		}
		// 消息提示
		String message = "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤
			// 1.创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置工厂的缓冲区大小，使得程序运行更快，默认缓冲区10kb,这里设为100kb
			factory.setSizeThreshold(1024 * 100);
			// 设置临时文件的保存目录
			factory.setRepository(tmpFile);
			// 2.创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决中文乱码问题
			upload.setHeaderEncoding("UTF-8");
			// 3.判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(req)) {
				// 按照传统方式获取数据
				return;
			}
			// 设置上传单个文件的大小的最大值
			upload.setFileSizeMax(1024 * 1024);
			// 设置上传文件总量的最大值
			upload.setSizeMax(1024 * 1024 * 10);
			// 4.使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(req);
			for (FileItem item : list) {
				// 如果item中是普通输入项的数据
				if (item.isFormField()) {
					// 调用处理普通数据项的方法
					processFormField(item, user);
				} else {
					// 调用处理文件的方法；
					processUploadFile(item, user, savePath, req, resp);
				}
			}
		} catch (Exception e) {
			message = "上传失败！";
			e.printStackTrace();
		}

		// 转向某个页面
		// System.out.println(image.getName());
		// System.out.println(image.getPath());

		UserService userService = new UserServiceImpl();
		message = userService.register(user);

		if (message.equals("注册成功！")) {
			try {
				req.getSession().setAttribute("user", user);
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				req.getSession().setAttribute("message", message);
				resp.sendRedirect(req.getContextPath() + "/jsp/register.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 处理上传的文件
	private void processUploadFile(FileItem item, User user, String savePath, HttpServletRequest req,
			HttpServletResponse resp) {
		// 得到文件的名字
		String filename = item.getName();
		// 找到文件类型前面的.的位置
		int index = filename.lastIndexOf(".");
		String filetype = filename.substring(index + 1, filename.length());
		// 获取文件的长度
		long fileSize = item.getSize();
		// 如果文件名为空，文件长度为空，那么直接返回
		if (filename.equals("") && fileSize == 0) {
			return;
		}

		// 补充一点关于File类的知识
		// File类的参数是抽象路径，可以存在，也可以不存在
		// mkdirs()用于创建目录，如果有不存在的父目录，那么也会创建父目录
		// mkdir()用于创建目录

		// 新建用来存放文件的文件夹 ，文件夹的名字用日期来表示，防止一个文件夹内有过多的文件；文件名用UUID来命名，防止文件名的重复
		// 先得到日期
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date1 = sdf.format(date);

		File file = new File(savePath + "/" + date1);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 新建文件用来接收上传上来的文件
		// 先创建一个UUID
		String uuid = UUID.randomUUID().toString();
		File uploadFile = new File(savePath + "/" + date1 + "/" + uuid + "." + filetype);
		try {
			item.write(uploadFile);
			
			// 设置存到数据库里的图片路径
			user.setProfilepic("http://"+GetPropertiesUtil.getUrl()+ req.getContextPath() + "/ProfilePic/" + date1 + "/" + uuid + "."
					+ filetype);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 处理普通表单的内容
	private void processFormField(FileItem item, User user) {
		// 获取该控件的name
		String name = item.getFieldName();
		int name1 = 0;
		if (name.equals("username"))
			name1 = 1;
		else if (name.equals("nickname"))
			name1 = 2;
		else if (name.equals("phone"))
			name1 = 3;
		else if (name.equals("sex"))
			name1 = 4;
		else if (name.equals("password"))
			name1 = 5;
		else if (name.equals("realname"))
			name1 = 6;
		else if (name.equals("profilepic"))
			name1 = 7;

		// 获取该控件的值
		try {
			String value = new String(item.getString("UTF-8"));
			switch (name1) {
			case 1:
				user.setUsername(value);
				break;
			case 2:
				user.setNickname(value);
				break;
			case 3:
				user.setPhone(value);
				break;
			case 4:
				user.setSex(value);
				break;
			case 5:
				user.setPassword(ServiceUtil.md5(value));
				break;
			case 6:
				user.setRealname(value);
				break;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 注销
	public void logoff(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("user");
		try {
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 登录
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		UserService us = new UserServiceImpl();

		User user = us.login(username, password);

		String message = "";
		if (user != null) {
			if (user.getPassword().equals(ServiceUtil.md5(password))) {
				req.getSession().setAttribute("user", user);
				try {
					resp.sendRedirect(req.getContextPath() + "/index.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				message = "密码错误！";
				// req.setAttribute("message", message);
				req.getSession().setAttribute("message", message);
				try {
					resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "不存在该用户！";
			// req.setAttribute("message", message);
			req.getSession().setAttribute("message", message);
			try {
				resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
