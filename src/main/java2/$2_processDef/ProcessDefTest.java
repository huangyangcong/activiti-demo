package $2_processDef;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

public class ProcessDefTest {
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void a_deployClasspath(){
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("diagrams/test.bpmn").name("部署名称2").deploy();
		System.out.println(deployment.getId()+":"+deployment.getName());
	}
	
	@Test
	public void b_deployZip(){
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("diagrams/helloworld.zip");
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addZipInputStream(zipInputStream).name("test-Zip").deploy();
		System.out.println(deployment.getId()+":"+deployment.getName());
	}
	
	@Test
	public void c_findDefList(){
		List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().list();
		if(list!=null && list.size()>0){
			for(ProcessDefinition pd:list){
				System.out.println("流程定义ID:"+pd.getId());//流程定义的key+版本+随机生成数
				System.out.println("流程定义的名称:"+pd.getName());//对应helloworld.bpmn文件中的name属性值
				System.out.println("流程定义的key:"+pd.getKey());//对应helloworld.bpmn文件中的id属性值
				System.out.println("流程定义的版本:"+pd.getVersion());//当流程定义的key值相同的相同下，版本升级，默认1
				System.out.println("资源名称bpmn文件:"+pd.getResourceName());
				System.out.println("资源名称png文件:"+pd.getDiagramResourceName());
				System.out.println("部署对象ID："+pd.getDeploymentId());
				System.out.println("#########################################################");
			}
		}
	}
}
