package $3_processInstance;

import java.text.SimpleDateFormat;
import java.util.*;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import $3_processInstance.multi.*;

public class ProcessInstanceTest {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	RepositoryService repositoryService = processEngine.getRepositoryService();
	TaskService taskService = processEngine.getTaskService();
	FormService formService = processEngine.getFormService();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private String PROCESS_DEFINITION_KEY ="test";
	private String BUSINESS_PRIMARY_KEY_PREFIX="ELEC-";
	
	private String APPLY_JHS="jhs";
	private List citySpList = Arrays.asList(new String[]{"city001","city002","city003"});
	private List proviSpList = Arrays.asList(new String[]{"provi001","provi002","provi003","provi004","provi005"});
	@Test
	public void a_deployClasspath(){
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("diagrams/test.bpmn").name("部署名称-电子审批").deploy();
		System.out.println(deployment.getId()+":"+deployment.getName());
	}
	
	@Test
	public void b_startInstance(){
		String businessPrimayKey = BUSINESS_PRIMARY_KEY_PREFIX + sdf.format(new Date());
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("businessPrimayKey", businessPrimayKey);
		variables.put("applyAssignee", APPLY_JHS);
		
		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY,businessPrimayKey, variables);
		
		System.out.println("流程实例ID:"+pi.getId());//流程实例ID    101
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   helloworld:1:4
	}
	
	@Test
	public void b2_getInstance(){
		HistoricProcessInstance hi = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId("85001").singleResult();
		System.out.println(runtimeService.getVariables(hi.getId())); //流程变量-获取

	}
	
	@Test
	public void c_getTaskList(){
		List<Task> taskList =processEngine.getTaskService().createTaskQuery().taskAssignee(APPLY_JHS).orderByTaskCreateTime().desc().list();
		if(taskList != null ){
			for(Task task : taskList){
				System.out.println("任务ID:"+task.getId());
				System.out.println("任务名称:"+task.getName());
				System.out.println("任务的创建时间:"+task.getCreateTime());
				System.out.println("任务的办理人:"+task.getAssignee());
				System.out.println("流程实例ID："+task.getProcessInstanceId());
				System.out.println("执行对象ID:"+task.getExecutionId());
				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
				System.out.println("########################################################");
			}
		}
	}
	

	@Test
	public void c2_getTaskByProcessInst(){
		Task task = processEngine.getTaskService().createTaskQuery().taskAssignee(APPLY_JHS).processInstanceId("250001").singleResult();
		
		System.out.println("taskId:"+task.getId());

		System.out.println("ProcessVariables:"+task.getProcessVariables());
		System.out.println("TaskLocalVariables:"+task.getTaskLocalVariables());
		
		System.out.println(taskService.getVariables(task.getId()));//任务变量获取
		
	}
	
	@Test
	public void c3_getCandidateUserTask(){
		List<Task> taskList =processEngine.getTaskService().createTaskQuery().taskCandidateUser("city001").orderByTaskCreateTime().desc().list();
		if(taskList != null ){
			for(Task task : taskList){
				System.out.println("任务ID:"+task.getId());
				System.out.println("任务名称:"+task.getName());
				System.out.println("任务的创建时间:"+task.getCreateTime());
				System.out.println("任务的办理人:"+task.getAssignee());
				System.out.println("流程实例ID："+task.getProcessInstanceId());
				System.out.println("执行对象ID:"+task.getExecutionId());
				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
				System.out.println("########################################################");
			}
		}
	}
	
	@Test
	public void d_startApplyTask(){
		Task task = processEngine.getTaskService().createTaskQuery().taskAssignee(APPLY_JHS).processInstanceId("312501").singleResult();
		String taskId = task.getId();

		System.out.println("taskId="+taskId);
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("businessPrimayKey", "11");
		variables.put("applyAssignee", "22");
		variables.put("name", "33");
		
	
		taskService.complete(taskId, variables,true);

		
	}
	
	@Test
	public void d2_approveCityTaskOpenPage(){
		String taskId="140008";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskFormData taskFormData = formService.getTaskFormData(taskId);
	
		FormProperty lineVaries = taskFormData.getFormProperties().get(0);  //formpropeties
		System.out.println(lineVaries.getId()+":"+lineVaries.getName());
	
		System.out.println(lineVaries.getType().getInformation("values")); 
		System.out.println(taskFormData.getFormKey());
		
		
		Map<String,Object> detailMap = new HashMap<String,Object>();
		detailMap.put("lineVarCode", lineVaries.getId());
		detailMap.put("lineVarName", lineVaries.getName());
		detailMap.put("lineVarOptions", lineVaries.getType().getInformation("values")); //formpropeties --- formtypelist
		detailMap.put("formKey", taskFormData.getFormKey());
		detailMap.put("processKey", task.getProcessDefinitionId().split(":")[0]);
		detailMap.put("processInstanceId", task.getProcessInstanceId());
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).active().singleResult();
	    String businessKey = processInstance.getBusinessKey();
	    detailMap.put("businessKey", businessKey);
		System.out.println(detailMap);

	}
	
	@Test
	public void d3_approveCityTaskOpenPage(){
		String taskId="302505";
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("cityApprovePass","10");
		taskService.complete(taskId, variables);//不能为本地变量

	}
	

	
	
	@Test
	public void d5_calim(){
		String taskId="252505";
		taskService.claim(taskId, "city003");
	}
	
	@Test
	public void d5_executeCity(){
		String taskId="315005";
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("cityApprovePass","10");
		
		
		
		//如果使用spring，则用el表达式使用
//		variables.put("mulitiInstance", new MulitiInstanceCompleteTask());
		 
		taskService.complete(taskId, variables);//不能为本地变量
		
		
		

	}
	
	@Test
	public void d6_executeChoose(){
		String taskId="317504";
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("proviSpList",proviSpList);
		
		//如果使用spring，则用el表达式使用
		variables.put("mulitiInstance", new MulitiInstanceCompleteTask());
		 
		taskService.complete(taskId, variables);//不能为本地变量
		
		
		

	}
	
	
	@Test
	public void d4_approveProvinceTask(){
		String taskId="280041";
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("provinceApprovePass","90");
		
		
		taskService.complete(taskId, variables);//不能为本地变量

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void z1_deploy(){
		a_deployClasspath();
	}
	
	@Test
	public void z2_start(){
		b_startInstance();
	}
	
	@Test
	public void z3_execApply(){
		String taskId="2506";
		taskService.complete(taskId);//不能为本地变量
	}
	
	@Test
	public void z4_calimCity(){
		String taskId="5002";
		taskService.claim(taskId, "city002");
	}
	
	@Test
	public void z5_execCity(){
		String taskId="5002";
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("cityApprovePass","10");
		taskService.complete(taskId, variables);//不能为本地变量
	}
	
	
	@Test
	public void z6_execChoose(){
		String taskId="7504";
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("proviSpList", proviSpList);
		//如果使用spring，则用el表达式使用
//		variables.put("mulitiInstance", new MulitiInstanceCompleteTask());
		taskService.complete(taskId, variables);//不能为本地变量
	}
	
	@Test
	public void z7_execHuiQian(){
		String taskId="10016";
		Map<String,Object> variables = new HashMap<String,Object>();
		//如果使用spring，则用el表达式使用
		variables.put("options", "建议"+taskId);
		variables.put("mulitiInstance", new MulitiInstanceCompleteTask());
		taskService.complete(taskId, variables);//不能为本地变量
	}
	
	@Test
	public void z8_execReview(){
		String taskId="315005";
		Map<String,Object> variables = new HashMap<String,Object>();
		//如果使用spring，则用el表达式使用
		variables.put("options", "最终决议："+taskId);
		variables.put("provinceApprovePass","10");
		taskService.complete(taskId, variables);//不能为本地变量
	}
	
}
