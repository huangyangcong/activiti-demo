package cn.edu.ahpu.activiti.demo2.org;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.edu.ahpu.tpc.framework.core.util.Main;

public class OrgAndStrudentData {
	private static OrgAndStrudentData instance;
	private List<Student> students ;

	private OrgAndStrudentData() {
	}

	public static synchronized OrgAndStrudentData getInstance() {
		if (instance == null) {
			instance = new OrgAndStrudentData();
		}
		return instance;
	}
	
	public List<Student> initStudents(){
		if(students == null){
			students = new ArrayList<Student>();
			students.add(new Student("蒋怀双","130",Arrays.asList(Role.B)));
			students.add(new Student("陈号","130",Arrays.asList(Role.A,Role.B)));
			students.add(new Student("马盛","130",Arrays.asList(Role.A,Role.B)));
			students.add(new Student("方涛","130",Arrays.asList(Role.A)));
			students.add(new Student("刘广","130",Arrays.asList(Role.A)));
			
			students.add(new Student("林鑫","107",Arrays.asList(Role.A,Role.B,Role.C)));
			students.add(new Student("杨建言","107",Arrays.asList(Role.A)));
			students.add(new Student("许明","107",Arrays.asList(Role.A)));
			students.add(new Student("庄扬州","107",Arrays.asList(Role.A,Role.B)));
		}
		return students;
	}
	
	
	public List<Student> getRoleStudent(String name,Role role){
		if(students == null){
			this.initStudents();
		}
		
		List<Student> retList = new ArrayList<Student>();
		
		//获取寝室班干
		if(role.equals(Role.B)){
			//找到该学生
			Student student = null;
			for(Student temp : students){
				if(temp.getName().equals(name)){
					student = temp;
					break;
				}
			}
			
			//获取室友
			String roomNum = student.getRoomNum();
			for(Student temp : students){
				if(temp.getRoomNum().equals(roomNum)){
					List<Role> roles = temp.getRoles();
					for(Role r : roles){
						if(r.equals(role)){
							retList.add(temp);
							break;
						}
					}
				}
			}
			
		}else if(role.equals(Role.C)){
			for(Student temp : students){
				List<Role> roles = temp.getRoles();
				for(Role r : roles){
					if(r.equals(role)){
						retList.add(temp);
						break;
					}
				}
			}
		}
		
		
		return retList;
	}
	
}
