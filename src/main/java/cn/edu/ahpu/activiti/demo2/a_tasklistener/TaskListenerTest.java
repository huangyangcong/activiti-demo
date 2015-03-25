package cn.edu.ahpu.activiti.demo2.a_tasklistener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

public class TaskListenerTest {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	RepositoryService repositoryService;
	RuntimeService runtimeService;
	TaskService taskService;
	IdentityService identityService;
	HistoryService historyService;
	FormService formService;

	/*
	 * @BeforeClass public static void beforeClassFunc(){
	 * System.out.println("@BeforeClass"); }
	 */

	@Before
	public void init() {
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		
		identityService = processEngine.getIdentityService();
		historyService = processEngine.getHistoryService();
		formService = processEngine.getFormService();
	}

	/** 部署流程定义 */
	@Test
	public void deploymentProcessDefinition() {
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream("listenerProcess.bpmn");
		InputStream inputStreamPng = this.getClass().getResourceAsStream("listenerProcess.png");
		Deployment deployment = repositoryService.createDeployment()// 创建一个部署对象
										.name("task listener 流程")// 添加部署的名称
										//.addClasspathResource("diagrams/helloworld.bpmn")
										.addInputStream("listenerProcess.bpmn", inputStreamBpmn)//
										.addInputStream("listenerProcess.png", inputStreamPng)//
										.deploy();// 完成部署
		System.out.println("部署ID：" + deployment.getId());// 1
		System.out.println("部署名称：" + deployment.getName());// helloworld入门程序
	}

	/** 启动流程实例 */
	@Test
	public void startProcessInstance() {
		// 流程定义的key
		String processDefinitionKey = "listenerProcess";
		identityService.setAuthenticatedUserId("蒋怀双");
		
		Map<String, Object> variables =new HashMap<String,Object>();
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey);// 使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:" + pi.getId());// 流程实例ID 101
		System.out.println("流程定义ID:" + pi.getProcessDefinitionId());// 流程定义ID
	}
	
	@Test
	public void findMyPersonalTask(){
		String assignee = "linxin";
		taskService.claim("3404", assignee);
		List<Task> list = taskService.createTaskQuery()//创建任务查询对象
						.taskAssignee(assignee)//指定个人任务查询，指定办理人
						.list();
		if(list!=null && list.size()>0){
			for(Task task:list){
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
	
	/**完成我的任务*/
	@Test
	public void completeMyPersonalTask(){
		//任务ID
		String taskId = "3404";
		Map<String, Object> variables =new HashMap<String,Object>();
		variables.put("bAgreePass", "1");
		taskService.complete(taskId,variables);//与正在执行的任务管理相关的Service
		System.out.println("完成任务：任务ID："+taskId);
	}
	
	

}
