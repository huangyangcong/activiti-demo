package $3_processInstance.multi;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * @author: jhs
 * @desc: ��ǩ-����ͶƱ
 * @date: Create in 2018/3/1  11:04
 */
@Service("calculateTicketDelegate")
public class CalculateTicketDelegate implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("总投票人数："+execution.getVariable("voterList"));
		System.out.println("投票结果是："+execution.getVariable("voteUserTask_Countor_"));
	}
}
