package cn.edu.ahpu.activiti.demo2.a_tasklistener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class TaskEndListener implements ExecutionListener  {

	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String businessKey = execution.getBusinessKey();
		String processInstanceId = execution.getProcessInstanceId();
		
		System.out.printf("processInstanceId(%s)结束,businessKey(%s)\n",processInstanceId,businessKey);
		
		/*
		 * 
		 * 流程结束时,可以更新业务表中的状态;
		 * 
		 */
		
	}

}
