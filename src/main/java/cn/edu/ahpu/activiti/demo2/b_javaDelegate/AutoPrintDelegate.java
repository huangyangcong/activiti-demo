package cn.edu.ahpu.activiti.demo2.b_javaDelegate;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class AutoPrintDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		String taskid = execution.getId();
		String name = execution.getProcessInstanceId();
		Map variables = execution.getVariables();
		Iterator it = variables.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}

}
