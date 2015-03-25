package cn.edu.ahpu.activiti.demo2.c_mail;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class JavaMailCheckDelegate implements JavaDelegate{

	public void execute(DelegateExecution execution) throws Exception {
		String taskid = execution.getId();
		String processInstanceId = execution.getProcessInstanceId();
		Map variables = execution.getVariables();
	/*	Iterator it = variables.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}*/
		String registerUserName = variables.get("registerUserName").toString();
		String registerUserAge = variables.get("registerUserAge").toString();
		String registerEmail = variables.get("registerEmail").toString();
		String checkResultSubject = registerUserName+"\"申请注册\"";
		String checkResultHtml ="流程实例id("+processInstanceId+"),任务id("+taskid+")";
		
		if(Integer.parseInt(registerUserAge) > 20){
			checkResultSubject += "【成功】";
			checkResultHtml += registerUserName+"你申请注册 安徽工程大学OA系统 成功";
		}else{
			checkResultSubject += "【失败】";
			checkResultHtml += registerUserName+"由于你年龄("+registerUserAge+")小于20岁,你申请注册 安徽工程大学OA系统 失败";
		}
		
		
		execution.setVariable("registerEmail",registerEmail);
		execution.setVariable("checkResultSubject",checkResultSubject);
		execution.setVariable("checkResultHtml",checkResultHtml);
		
	}

}
