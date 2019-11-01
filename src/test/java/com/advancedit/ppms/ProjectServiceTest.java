package com.advancedit.ppms;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.advancedit.ppms.models.Goal;
import com.advancedit.ppms.models.GoalStatus;
import com.advancedit.ppms.models.Project;
import com.advancedit.ppms.models.ProjectStatus;
import com.advancedit.ppms.models.ProjectSummary;
import com.advancedit.ppms.models.Task;
import com.advancedit.ppms.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;
	
	 private List<String> ids;
	 
	@Before
	public void init() {
		ids = new ArrayList<>();
		projectService.deleteAll();
	}
	 
	@After
	public void finalize() {
		if (ids.isEmpty()){
			ids.forEach(id -> projectService.deleteProject(id));
		}
	    ids.clear();
	}
	
	
	
	@Test
	public void getPagedSummury() {
		addProjects();
		
		
		Page<ProjectSummary> projects = projectService.getPagedProjectSummary(0, 100, null, null);
		assertNotNull(projects);
		assertNotNull(projects.getContent());
		assertEquals(projects.getContent().size(), 1);
	
	//	assertEquals(p.getName(), projects.getContent().get(0).getName());
	//	assertEquals(p.getStatus(), projects.getContent().get(0).getStatus());
	//	assertNotNull(projects.getContent().get(0).getGoals());
	//	assertEquals(projects.getContent().get(0).getGoals().size(),  0);
		
		
	}
	
	
	
	@Test
	public void saveProjects() {
		
		Project p = new Project();
		p.setName("Project 1");
		p.setStatus(ProjectStatus.NEW);
		
		Goal goal = new Goal();
		goal.setGoalId(new ObjectId().toHexString());
		goal.setName("Goal 1");
		goal.setStatus(GoalStatus.NEW);
		goal.setDescription("desciption");
		
		p.getGoals().add(goal);
		
		Project saved = projectService.addProject(p);
		
		ids.add(p.getProjectId());
		assertNotNull(saved.getProjectId());
		assertEquals(p.getName(), saved.getName());
		assertEquals(p.getStatus(), saved.getStatus());
		assertEquals(p.getGoals().size(), 1);
		assertEquals(saved.getGoals().get(0).getName(), goal.getName());
		assertEquals(saved.getGoals().get(0).getStatus(), goal.getStatus());
		assertEquals(saved.getGoals().get(0).getName(), goal.getName());
		assertNotNull(saved.getGoals().get(0).getGoalId());
		
		
		Page<Project> projects = projectService.getPagedListProject(0, 100, null, null);
		assertNotNull(projects);
		assertNotNull(projects.getContent());
		assertEquals(projects.getContent().size(), 1);
	
		assertEquals(p.getName(), projects.getContent().get(0).getName());
		assertEquals(p.getStatus(), projects.getContent().get(0).getStatus());
		assertNotNull(projects.getContent().get(0).getGoals());
		
		assertEquals(projects.getContent().get(0).getGoals().size(),  0);
		
		
	}
	
	@Test
	public void addGoal() {
		
		Project p = new Project();
		p.setName("Project 1");
		p.setStatus(ProjectStatus.NEW);
		
		
		Project saved = projectService.addProject(p);
		
		ids.add(p.getProjectId());
		assertNotNull(saved.getProjectId());
		assertEquals(p.getName(), saved.getName());
		assertEquals(p.getStatus(), saved.getStatus());
		
		
		{
		
		Goal goal = new Goal();
	
		goal.setName("Goal 1");
		goal.setStatus(GoalStatus.NEW);
		goal.setDescription("desciption");
		
		
		
		String goalId = projectService.addGoal(saved.getProjectId(), goal);
		
		
		
		Goal savedGoal = projectService.getGoal(saved.getProjectId(), goalId);
		//assertEquals(p.getGoals().size(), 1);
		assertEquals(savedGoal.getName(), goal.getName());
		assertEquals(savedGoal.getStatus(), goal.getStatus());
		assertEquals(savedGoal.getName(), goal.getName());
		
		}
		
		{
		
		Goal goal2 = new Goal();
		
		goal2.setName("Goal 2");
		goal2.setStatus(GoalStatus.NEW);
		goal2.setDescription("desciption2");
		
		String goalId2 = projectService.addGoal(saved.getProjectId(), goal2);
		
		
	
		//assertNotNull(saved.getGoals().get(0).getGoalId());
		
		Goal savedGoal2 = projectService.getGoal(saved.getProjectId(), goalId2);
		//assertEquals(p.getGoals().size(), 1);
		assertEquals(savedGoal2.getName(), goal2.getName());
		assertEquals(savedGoal2.getStatus(), goal2.getStatus());
		assertEquals(savedGoal2.getName(), goal2.getName());
		}
		
	}
	

	@Test
	public void addTask() {
		
		Project p = new Project();
		p.setName("Project 1");
		p.setStatus(ProjectStatus.NEW);
		
		
		Project saved = projectService.addProject(p);
		
		ids.add(p.getProjectId());
		assertNotNull(saved.getProjectId());
		assertEquals(p.getName(), saved.getName());
		assertEquals(p.getStatus(), saved.getStatus());
		
		
		{
		
		Goal goal = new Goal();
	
		goal.setName("Goal 1");
		goal.setStatus(GoalStatus.NEW);
		goal.setDescription("desciption");
		
		
		
		String goalId = projectService.addGoal(saved.getProjectId(), goal);
		
		
		
		Goal savedGoal = projectService.getGoal(saved.getProjectId(), goalId);
		//assertEquals(p.getGoals().size(), 1);
		assertEquals(savedGoal.getName(), goal.getName());
		assertEquals(savedGoal.getStatus(), goal.getStatus());
		assertEquals(savedGoal.getName(), goal.getName());
		
		{
		Task task = new Task();
		task.setName("Task 1");
		task.setDescription("Desciption 1");
		
		String taskId = projectService.addNewTask(saved.getProjectId(), goalId, task);
		
		 savedGoal = projectService.getGoal(saved.getProjectId(), goalId);
			assertEquals(savedGoal.getTasks().size(), 1);
			assertEquals(savedGoal.getTasks().get(0).getName(), task.getName());
			assertEquals(savedGoal.getTasks().get(0).getDescription(), task.getDescription());
			
			
		Task  savedTask = projectService.getTask(saved.getProjectId(), goalId, taskId);
				//assertEquals(savedGoal.getTasks().size(), 1);
				assertEquals(savedTask.getName(), task.getName());
				assertEquals(savedTask.getDescription(), task.getDescription());
			
		}
		
		
		{
		Task task = new Task();
		task.setName("Task 2");
		task.setDescription("Desciption 2");
		
		String taskId = projectService.addNewTask(saved.getProjectId(), goalId, task);
				
			
		Task  savedTask = projectService.getTask(saved.getProjectId(), goalId, taskId);
				//assertEquals(savedGoal.getTasks().size(), 1);
				assertEquals(savedTask.getName(), task.getName());
				assertEquals(savedTask.getDescription(), task.getDescription());
			
		}
	}
		

		
	}
	
	
	public void addProjects() {
		
		for(int k = 0; k < 8; k++){
			Project p = new Project();
			p.setName("Project " + k);
			p.setStatus(ProjectStatus.NEW);
			Project saved = projectService.addProject(p);
			ids.add(p.getProjectId());
			for(int i = 0; i < 10; i++)
			{
				Goal goal = new Goal();
				goal.setName("Goal " + i);
				goal.setStatus(( i % 2 == 0 )? GoalStatus.NEW: GoalStatus.PROGRESS);
				goal.setDescription("desciption " + i);
				String goalId = projectService.addGoal(saved.getProjectId(), goal);
				for(int j = 0; j < 5; j++){
					Task task = new Task();
					task.setName("Task " + i + "-" + j);
					task.setDescription("Desciption " + i + "-" + j);
					projectService.addNewTask(saved.getProjectId(), goalId, task);			
				}
			}
		}

		

		
	}

}
