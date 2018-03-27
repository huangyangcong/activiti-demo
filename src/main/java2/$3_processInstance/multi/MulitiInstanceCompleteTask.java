package $3_processInstance.multi;

import java.io.Serializable;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;



public class MulitiInstanceCompleteTask  implements Serializable {  
	
	private static final long serialVersionUID = 5754522101489239675L;  
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	RepositoryService repositoryService = processEngine.getRepositoryService();

	
    public boolean completeTask(DelegateExecution execution) {  
  
        int nrOfInstances = (Integer) execution.getVariable("nrOfInstances"); //�ܵĻ�ǩ��������
        int nrOfActiveInstances = (Integer) execution.getVariable("nrOfActiveInstances"); //��ǰ��ȡ�Ļ�ǩ�������� ---δִ�е�
        int nrOfCompletedInstances = (Integer) execution.getVariable("nrOfCompletedInstances"); //�ܵĻ�ǩ��������---��ִ�е�
        
        
        System.out.println(execution.getVariables());
        if(nrOfActiveInstances > 0) {
        	return false;
        }
        
        //TODO -- ͳ��Ʊ��
        String nodeId = execution.getCurrentActivityId();
        String actInstId = execution.getProcessInstanceId();  
        
        
        return true;  
    }  
  
}
