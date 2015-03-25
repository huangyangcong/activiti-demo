package cn.edu.ahpu.activiti.demo2.a_tasklistener;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import cn.edu.ahpu.activiti.demo2.org.OrgAndStrudentData;
import cn.edu.ahpu.activiti.demo2.org.Role;
import cn.edu.ahpu.activiti.demo2.org.Student;

/**
 * 员工经理任务分配
 *
 */
@SuppressWarnings("serial")
public class ManagerTaskListener implements TaskListener {

	public void notify(DelegateTask delegateTask) {
		String eventName = delegateTask.getName();
		Role role = null;
		if(eventName.equals("寝室长审批")){
			role = Role.B;
		}else if(eventName.equals("班长审批")){
			role = Role.C;
		}
		List<Student> retList  = OrgAndStrudentData.getInstance().getRoleStudent("蒋怀双", role);
		List<String> candidateUsers = new ArrayList<String>();
		for(Student temp :retList){
			candidateUsers.add(temp.getName());
		}
		delegateTask.addCandidateUsers(candidateUsers);
	}
	
}
