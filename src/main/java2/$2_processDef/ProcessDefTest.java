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
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("diagrams/test.bpmn").name("��������2").deploy();
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
				System.out.println("���̶���ID:"+pd.getId());//���̶����key+�汾+���������
				System.out.println("���̶��������:"+pd.getName());//��Ӧhelloworld.bpmn�ļ��е�name����ֵ
				System.out.println("���̶����key:"+pd.getKey());//��Ӧhelloworld.bpmn�ļ��е�id����ֵ
				System.out.println("���̶���İ汾:"+pd.getVersion());//�����̶����keyֵ��ͬ����ͬ�£��汾������Ĭ��1
				System.out.println("��Դ����bpmn�ļ�:"+pd.getResourceName());
				System.out.println("��Դ����png�ļ�:"+pd.getDiagramResourceName());
				System.out.println("�������ID��"+pd.getDeploymentId());
				System.out.println("#########################################################");
			}
		}
	}
}
