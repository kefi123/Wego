package xhj.wdc.domain;

public class User {
	private String phone;
	private String username;
	private String nickname;
	private String realname;
	private String password;
	private String sex;
	private String profilepic;
	
	@Override
	public String toString() {
		return "User [phone=" + phone + ", username=" + username + ", nickname=" + nickname + ", realname=" + realname
				+ ", password=" + password + ", sex=" + sex + ", profilepic=" + profilepic + "]";
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}
	
	
}
