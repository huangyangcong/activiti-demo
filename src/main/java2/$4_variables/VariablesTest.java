package $4_variables;

import java.util.*;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.*;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class VariablesTest {


	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	RepositoryService repositoryService = processEngine.getRepositoryService();
	TaskService taskService = processEngine.getTaskService();
	FormService formService = processEngine.getFormService();
	
	private static String PROCESS_KEY="variablesProcess";
	
	@Test
	public void a_deployClasspath(){
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("diagrams/variablesProcess.bpmn").deploy();
		System.out.println("deploymentid:"+deployment.getId());
	}
	
	String businessKey= "jhs_00011";
	@Test
	public void b_startProcess(){
		Map variables = new HashMap();
		variables.put("applicant", "jhs");
		variables.put("staffType","new");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(PROCESS_KEY, businessKey, variables);
		System.out.println("流程实例Id:"+pi.getId());
	}
	
	@Test
	public void c_辅导员(){
		String taskId = "302507";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		System.out.println(task);
		
		System.out.println("getTaskLocalVariables:"+task.getTaskLocalVariables());
		System.out.println("getProcessVariables:"+task.getProcessVariables());
		
		Map variables = new HashMap();
		variables.put("fudaoyuan", "fdy_001");
		variables.put("option", "fdy_001同意啦");
		taskService.complete(taskId,variables,true);
	}
	
	@Test
	public void d_项目经理(){
		String taskId = "305004";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		System.out.println(task);
		System.out.println("getTaskLocalVariables:"+task.getTaskLocalVariables());
		System.out.println("getProcessVariables:"+task.getProcessVariables());
		
		Map<String, Object>  executionVariables = runtimeService.getVariables(task.getExecutionId());
		System.out.println("executionVariables:"+executionVariables);
		
		Map variables = new HashMap();
		variables.put("xiangmujili", "xmjl_001");
		variables.put("option", "xmjl_001同意啦");
		
		taskService.complete(taskId,variables);
	}
	
	
	@Test
	public void e_部门经理(){
		String taskId = "307504";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		System.out.println(task);
		System.out.println("getTaskLocalVariables:"+task.getTaskLocalVariables());
		System.out.println("getProcessVariables:"+task.getProcessVariables());
		
		Map<String, Object>  executionVariables = runtimeService.getVariables(task.getExecutionId());
		System.out.println("executionVariables:"+executionVariables);
		
		Map variables = new HashMap();
		variables.put("bumenjingli", "bmjl_001");
		variables.put("option", "bmjl_001同意啦");
		variables.put("days", 4);
		variables.put("fzList", Arrays.asList("副总1","副总2","副总3"));
		taskService.complete(taskId,variables,false);
	}
	
	@Test
	public void f_副总会签1(){
		String taskId = "310017";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		Map<String, Object>  executionVariables = runtimeService.getVariables(task.getExecutionId());
		System.out.println("executionVariables:"+executionVariables);
		Map variables = new HashMap();
		String assignee = task.getAssignee();
		variables.put("option",assignee+"同意了");
		taskService.complete(taskId,variables);
		
		
		
	}
	
	
	@Test
	public void f_副总会签2(){
		String taskId = "310022";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> executionVariables = runtimeService.getVariables(task.getExecutionId());
		Map variables = new HashMap();
		String assignee = task.getAssignee();
		variables.put("option",assignee+"同意了");
		variables.put(assignee+"option", assignee+"同意了_self");
		System.out.println("executionVariables:"+executionVariables);
		taskService.complete(taskId,variables);
		

	}
	
	@Test
	public void f_副总会签3(){
		String taskId = "310027";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> executionVariables = runtimeService.getVariables(task.getExecutionId());
		Map variables = new HashMap();
		String assignee = task.getAssignee();
		variables.put("option",assignee+"同意了");
		variables.put(assignee+"option", assignee+"同意了_self");
		System.out.println("executionVariables:"+executionVariables);
		taskService.complete(taskId,variables,true);
	}
	
	@Test
	public void g_hr(){
		String taskId = "315007";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> executionVariables = runtimeService.getVariables(task.getExecutionId());
		System.out.println("executionVariables:"+executionVariables);

	}
	
}
