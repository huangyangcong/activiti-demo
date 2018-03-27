package $3_processInstance.listeners;

import java.util.Arrays;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 当前任务完成之后，设置下一步流程办理人
 * @author Administrator
 *
 */
public class SetCurrentOperatorsListener implements TaskListener{
	private List<String> citySpList = Arrays.asList(new String[]{"city001","city002","city003"});
	private List<String> proviSpList = Arrays.asList(new String[]{"provi001","provi002","provi003","provi004","provi005"});
	private String APPLY_JHS="jhs";

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("ApplyTaskListener 触发啦！----------------------------------");
		System.out.println(delegateTask.getVariables());
		System.out.println(delegateTask.getVariablesLocal());
		
		
		String assignee = delegateTask.getAssignee();
		System.out.println("当前任务办理人是："+assignee);

		String taskDefKey = delegateTask.getTaskDefinitionKey();
		if("apply".equals(taskDefKey)) {
			delegateTask.setAssignee(APPLY_JHS);
		}else if("city".equals(taskDefKey)) {
			delegateTask.addCandidateUsers(citySpList);
		}else if("choose".equals(taskDefKey)) {
			delegateTask.setAssignee("CHOOSEN"); //设置
		}else if("provinceReview".equals(taskDefKey)) {
			delegateTask.setAssignee("master"); //设置
		}
		
		System.out.println("ApplyTaskListener 触发啦！￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
	}
}
