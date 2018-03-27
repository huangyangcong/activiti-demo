package $3_processInstance.multi;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

/**
 * @author: jhs
 * @desc: ��ǩ��������-�������
 * @date: Create in 2018/3/1  11:00
 */
@Service("voteTaskCompleteListener")
public class VoteTaskCompleteListener implements TaskListener {
	
	@Override
	public void notify(DelegateTask delegateTask) {
		long counter = (Long) delegateTask.getExecution().getVariable("voteUserTask_Countor_");
		String pass = (String) delegateTask.getVariable("pass");
		if("10".equals(pass)){
			//同意
			counter++;
			delegateTask.getExecution().setVariable("voteUserTask_Countor_", counter);
		}
	}
}
