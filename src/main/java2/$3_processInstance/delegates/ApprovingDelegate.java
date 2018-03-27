package $3_processInstance.delegates;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ApprovingDelegate implements JavaDelegate {

	  	  
	 
	@Override
	public void execute(DelegateExecution execution) throws Exception{
		System.out.println("ApprovingDelegate ´¥·¢À²£¡----------------------------------");
		System.out.println(execution);
		System.out.println("ApprovingDelegate ´¥·¢À²£¡£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤£¤");
	}
	    
}
