package xhj.wdc.service.impl;

import java.util.List;

import xhj.wdc.dao.JoinactivityDao;
import xhj.wdc.dao.jdbc.impl.JoinactivityDaoImpl;
import xhj.wdc.domain.Joinactivity;
import xhj.wdc.service.JoinActivityService;

public class JoinActivityServiceImpl implements JoinActivityService {
	private JoinactivityDao jad = new JoinactivityDaoImpl();

	// 添加参加活动的信息
	public String addJoinActivity(Joinactivity ja) {
		String message="";
		// 判断该用户是否已经报名了该活动
		if(jad.findByPhoneActivityid(ja.getCphone(), ja.getCactivityid())==null)
			jad.addJoinActivity(ja);
		else
			message="您已报名该活动，请勿重复操作！";
		return message;
	}

	// 退出活动
	public void quitActivity(String phone, String activityid) {
		jad.updateJoinStatus(1, phone, activityid);
	}

	// 查看最新参加的两个活动
	public List<Joinactivity> lookJoinedActivity(String phone) {
		return jad.getJoinActivityByPhone(phone);
	}

}
