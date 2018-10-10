package xhj.wdc.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSON;

import xhj.wdc.domain.Activity;
import xhj.wdc.service.ActivityService;
import xhj.wdc.service.impl.ActivityServiceImpl;
import xhj.wdc.util.GetPropertiesUtil;

@WebServlet("/servlet/activityServlet")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("method");
		try {
			if (method.equals("release_activity"))
				release_activity(req, resp);
			else if (method.equals("randomActivity"))
				randomActivity(req, resp);
			else if (method.equals("detailActivity"))
				detailActivity(req, resp);
			else if (method.equals("userReleased"))
				userReleased(req, resp);
			else if (method.equals("display_activity"))
				display_activity(req, resp);
			else if (method.equals("cancelActivity"))
				cancelActivity(req, resp);

		} catch (NullPointerException e) {
			System.out.println("确实空了");
		}

	}

	private void cancelActivity(HttpServletRequest req, HttpServletResponse resp) {

		Activity activity = new Activity();
		activity.setActivityid(req.getParameter("activityid"));
		activity.setStarttime(req.getParameter("startTime"));

		ActivityService activityService = new ActivityServiceImpl();

		boolean f = activityService.cancelActivity(activity);
		String message = "";
		try {
			if (f == false) {
				message = "距离活动开始已不足两天，无法取消";
				req.getSession().setAttribute("message", message);
				resp.sendRedirect(req.getContextPath() + "/jsp/user_center.jsp");
			}
			else{
				message = "取消成功";
				req.getSession().setAttribute("message", message);
				resp.sendRedirect(req.getContextPath() + "/jsp/user_center.jsp");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void display_activity(HttpServletRequest req, HttpServletResponse resp) {

		ActivityService activityService = new ActivityServiceImpl();

		int type = Integer.parseInt(req.getParameter("type"));

		List<Activity> list = activityService.display_activity(type);
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

	private void userReleased(HttpServletRequest req, HttpServletResponse resp) {

		ActivityService activityService = new ActivityServiceImpl();

		String phone = req.getParameter("phone");
		List<Activity> list = activityService.lookReleasedActivity(phone);
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

	private void detailActivity(HttpServletRequest req, HttpServletResponse resp) {

		ActivityService activityService = new ActivityServiceImpl();

		String activityid = req.getParameter("activityid");
		Activity activity = activityService.activityDetail(activityid);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(JSON.toJSONString(activity)); // 转换成JSON字符串然后用print输出
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(JSON.toJSONString(activity));
	}

	public void randomActivity(HttpServletRequest req, HttpServletResponse resp) {
		ActivityService activityService = new ActivityServiceImpl();

		Activity activity = activityService.randomActivity();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(JSON.toJSONString(activity)); // 转换成JSON字符串然后用print输出
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(JSON.toJSONString(activity));

	}

	// 发布活动
	public void release_activity(HttpServletRequest req, HttpServletResponse resp) {

		// 创建一个实体类，用于之后存储表单元素的值
		Activity activity = new Activity();
		// 得到文件的上传目录，
		// 在这里需要普及一下，因为我们这里上传的是图片，以后还需要用，
		// 但是如果我们上传的是文件，那么就应该放在web-inf下，不允许外界访问，保护数据的安全

		// getServletContext()方法用于获得上下文，getRealPath()用于得到上下文的真是路径
		String savePath = this.getServletContext().getRealPath("/ActivityPic");
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
					processFormField(item, activity);
				} else {
					// 调用处理文件的方法；
					processUploadFile(item, activity, savePath, req, resp);
				}
			}
		} catch (Exception e) {
			message = "上传失败！";
			e.printStackTrace();
		}

		// 转向某个页面
		// System.out.println(image.getName());
		// System.out.println(image.getPath());

		ActivityService activityService = new ActivityServiceImpl();

		activityService.release_activity(activity);
		try {
			message = "发布成功！";
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(req.getContextPath() + "/jsp/release_activity.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 处理上传的文件
	private void processUploadFile(FileItem item, Activity activity, String savePath, HttpServletRequest req,
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
		activity.setActivityid(uuid);
		File uploadFile = new File(savePath + "/" + date1 + "/" + uuid + "." + filetype);
		try {
			item.write(uploadFile);
			// 改成相应的域名或ip
			activity.setPicpath("http://" + GetPropertiesUtil.getUrl() + req.getContextPath() + "/ActivityPic/" + date1
					+ "/" + uuid + "." + filetype);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 处理普通表单的内容
	private void processFormField(FileItem item, Activity activity) {
		// 获取该控件的name
		String name = item.getFieldName();
		int name1 = 0;
		if (name.equals("title"))
			name1 = 1;
		else if (name.equals("place"))
			name1 = 2;
		else if (name.equals("phone"))
			name1 = 3;
		else if (name.equals("limitsize"))
			name1 = 4;
		else if (name.equals("startdate"))
			name1 = 5;
		else if (name.equals("starttime"))
			name1 = 6;
		else if (name.equals("enddate"))
			name1 = 7;
		else if (name.equals("endtime"))
			name1 = 8;
		else if (name.equals("type"))
			name1 = 9;
		else if (name.equals("ispay"))
			name1 = 10;
		else if (name.equals("money"))
			name1 = 11;
		else if (name.equals("description"))
			name1 = 12;

		// 获取该控件的值
		try {
			String value = new String(item.getString("UTF-8"));
			switch (name1) {
			case 1:
				activity.setTitle(value);
				break;
			case 2:
				activity.setPlace(value);
				break;
			case 3:
				activity.setPhone(value);
				break;
			case 4:
				activity.setLimitsize(Integer.parseInt(value));
				break;
			case 5:
				activity.setStarttime(value);
				break;
			case 6:
				activity.setStarttime(activity.getStarttime() + " " + value);
				break;
			case 7:
				activity.setEndtime(value);
				break;
			case 8:
				activity.setEndtime(activity.getEndtime() + " " + value);
				break;
			case 9:
				activity.setType(Integer.parseInt(value));
				break;
			case 10:
				activity.setIspay(Integer.parseInt(value));
				break;
			case 11: {
				if (activity.getIspay() == 1)
					activity.setMoney(Float.parseFloat(value));
				else
					activity.setMoney(0);
				break;
			}

			case 12:
				activity.setDescription(value);
				break;

			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
