package cn.edu.ahpu.activiti.demo2.org;

import java.util.List;

public class Student {
	private String name;
	private String roomNum; // 寝室号
	private List<Role> roles;

	public Student(String name, String roomNum, List<Role> roles) {
		super();
		this.name = name;
		this.roomNum = roomNum;
		this.roles = roles;
	}

	public Student() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
