package com.agile.test;


import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class TestActiviti {

	public static void main(String args[]) {
		TestActiviti test = new TestActiviti();
		test.createTable();
		test.createProcessEngine();		
		test.findProcessDefinition();
		
		test.deploy();
		test.startProcess();
		try {
			test.queryMyTask();
			test.completeTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void createTable() {  
	    // 工作流引擎的全部配置  
	    ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration  
	            .createStandaloneProcessEngineConfiguration();  
	  
	    // 连接数据库的配置
	    processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");  
	    processEngineConfiguration  
	            .setJdbcUrl("jdbc:mysql://localhost:3306/jaguar?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8");  
	    processEngineConfiguration.setJdbcUsername("root");  
	    processEngineConfiguration.setJdbcPassword("root");  
	   
        /**
        public static final String DB_SCHEMA_UPDATE_FALSE = "false";不能自动创建表，需要表存在
        public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";先删除表再创建表
        public static final String DB_SCHEMA_UPDATE_TRUE = "true";如果表不存在，自动创建表
         */
	    
	    //如果表不存在，自动创建表  
	    processEngineConfiguration.setDatabaseSchemaUpdate(processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
	    
	    // 工作流的核心对象，ProcessEnginee对象  
	    ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();  
	    System.out.println(processEngine);  
	}  

    /**使用配置文件创建工作流需要的23张表*/
    public void createTable_2(){
//      ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
//      //工作流的核心对象，ProcessEnginee对象
//      ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")   //
                                    .buildProcessEngine();
        System.out.println("processEngine:"+processEngine);
    }
    
    /**
     * 建表并创建流程引擎对象（核心对象）
     */
    public void createProcessEngine() {
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
    /**
     * 部署每个新的流程定义都会在表中增加一条记录。 
     * 部署流程定义 影响的表： act_re_procdef ： 流程定义表 ： 该表的key属性 是bpmn 的 id决定 该表的name属性
     * 是bpmn 的name 属性决定 act_re_deployment：部署表 ：id是由act_ge_property的 next_dbid决定
     * act_ge_property ：通用属性表
     */
    public void deploy() {
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取仓库服务的实例
        Deployment deployment = processEngine.getRepositoryService()//
                .createDeployment()//
                .addClasspathResource("activiti/Process1.bpmn")//
                .addClasspathResource("activiti/Process1.png")//
                .name("报销单流程").deploy();
        System.out.println(deployment.getId() + "        " + deployment.getName());
    }
    
    /**部署流程定义zip文件*/
    public void deploymentProcessDefinition_zip(){
    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("zip/helloworld.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine.getRepositoryService() // 与流程定义和部署对象相关的Service
                        .createDeployment() // 创建一个部署对象
                        .name("zip流程定义") // 添加部署的名称
                        .addZipInputStream(zipInputStream) // 制定zip格式文件完成部署
                        .deploy(); // 完成部署
        System.out.println("部署Id："+deployment.getId()); // 部署Id:22501
        System.out.println("部署名称："+deployment.getName()); // 部署名称:zip流程定义
    }
    
    /* 查询流程定义 */
    public void findProcessDefinition() {
        // 获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    	
        List<ProcessDefinition> list = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
                .createProcessDefinitionQuery()// 创建一个流程定义的查询
                /** 指定查询条件,where条件 */
                // .deploymentId(deploymentId)//使用部署对象ID查询
                // .processDefinitionId(processDefinitionId)//使用流程定义ID查询
                // .processDefinitionKey(processDefinitionKey)//使用流程定义的key查询
                // .processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询

                /** 排序 */
                .orderByProcessDefinitionVersion().asc()// 按照版本的升序排列
                // .orderByProcessDefinitionName().desc()//按照流程定义的名称降序排列

                /** 返回的结果集 */
                .list();// 返回一个集合列表，封装流程定义
        // .singleResult();//返回惟一结果集
        // .count();//返回结果集数量
        // .listPage(firstResult, maxResults);//分页查询
        if (list != null && list.size() > 0) {
            for (ProcessDefinition pd : list) {
                System.out.println("流程定义ID:" + pd.getId());// 流程定义的key+版本+随机生成数
                System.out.println("流程定义的名称:" + pd.getName());// 对应helloworld.bpmn文件中的name属性值
                System.out.println("流程定义的key:" + pd.getKey());// 对应helloworld.bpmn文件中的id属性值
                System.out.println("流程定义的版本:" + pd.getVersion());// 当流程定义的key值相同的相同下，版本升级，默认1
                System.out.println("资源名称bpmn文件:" + pd.getResourceName());
                System.out.println("资源名称png文件:" + pd.getDiagramResourceName());
                System.out.println("部署对象ID：" + pd.getDeploymentId());
                System.out
                        .println("#########################################################");
            }
        }
    }    
    
    /**
     * 启动流程
     */
    public void startProcess() {
        // 获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 启动流程
        // 使用流程定义的key启动流程实例，默认会按照最新版本启动流程实例
        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("myProcess");
        System.out.println("pid:" + pi.getId() + ",activitiId:" + pi.getActivityId());
    }
    /**
     *  查看任务
     * @throws Exception
     */

    public void queryMyTask() throws Exception {
        // 指定任务办理者
        String assignee = "李四";
        // 获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 查询任务的列表
        List<Task> tasks = processEngine.getTaskService().createTaskQuery()// 创建任务查询对象
                .taskAssignee(assignee)// 指定个人任务办理人
                .list();
        // 遍历结合查看内容
        for (Task task : tasks) {
            System.out.println("taskId:" + task.getId() + ",taskName:" + task.getName());
            System.out.println("*************************");
        }
    }
     
    /**
     *  办理任务
     * @throws Exception
     */
    public void completeTask() throws Exception {
    String taskId = "404";
    // 获取流程引擎对象
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    // 完成任务
    processEngine.getTaskService()
        .complete(taskId);
    System.out.println("完成任务！");
    }
    
    /*删除流程定义（删除key相同的所有不同版本的流程定义） */
    public void deleteProcessDefinitionByKey() {
    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 流程定义的key
        String processDefinitionKey = "helloworld";
        // 先使用流程定义的key查询流程定义，查询出所有的版本
        List<ProcessDefinition> list = processEngine.getRepositoryService()//
                .createProcessDefinitionQuery()//
                .processDefinitionKey(processDefinitionKey).list();//
        // 遍历，获取每个流程定义的部署ID
        if (list != null && list.size() > 0) {
            for(ProcessDefinition pd:list){
                //获取部署ID
                String deploymentId = pd.getDeploymentId();
                processEngine.getRepositoryService()//
                            .deleteDeployment(deploymentId, true); 
            }
        }
    }  
    
    public void findMyPersonalTask(){
    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String assignee = "王五";
        List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
                        .createTaskQuery()//创建任务查询对象
                        /**查询条件（where部分）*/
                        .taskAssignee(assignee)//指定个人任务查询，指定办理人
//                      .taskCandidateUser(candidateUser)//组任务的办理人查询
//                      .processDefinitionId(processDefinitionId)//使用流程定义ID查询
//                      .processInstanceId(processInstanceId)//使用流程实例ID查询
//                      .executionId(executionId)//使用执行对象ID查询
                        /**排序*/
                        .orderByTaskCreateTime().asc()//使用创建时间的升序排列
                        /**返回结果集*/
//                      .singleResult()//返回惟一结果集
//                      .count()//返回结果集的数量
//                      .listPage(firstResult, maxResults);//分页查询
                        .list();//返回列表
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
    
    /* 查询流程状态（判断流程正在执行，还是结束） */
    public void isProcessEnd() {
    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String processInstanceId = "501";
        ProcessInstance pi = processEngine.getRuntimeService()// 表示正在执行的流程实例和执行对象
                .createProcessInstanceQuery()// 创建流程实例查询
                .processInstanceId(processInstanceId)// 使用流程实例ID查询
                .singleResult();
        if (pi == null) {
            System.out.println("流程已经结束");
        } else {
            System.out.println("流程没有结束");
        }
    }
    
    /*查询历史任务*/
    public void findHistoryTask(){
    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String taskAssignee = "张三";
        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据（历史表）相关的Service
                        .createHistoricTaskInstanceQuery()//创建历史任务实例查询
                        .taskAssignee(taskAssignee)//指定历史任务的办理人
                        .list();
        if(list!=null && list.size()>0){
            for(HistoricTaskInstance hti:list){
                System.out.println(hti.getId()+"    "+hti.getName()+"    "+hti.getProcessInstanceId()+"   "+hti.getStartTime()+"   "+hti.getEndTime()+"   "+hti.getDurationInMillis());
                System.out.println("################################");
            }
        }
    }    
}