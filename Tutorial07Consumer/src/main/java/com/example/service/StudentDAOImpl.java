package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dao.StudentDAO;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentDAOImpl implements StudentDAO
{
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
	
	@Override
	public StudentModel selectStudent (String npm)
	{
		StudentModel student = restTemplate.getForObject(
				"http://localhost:8080/rest/student/view/"+npm,
				StudentModel.class);
		return student;
	}

	@Override
	public List<StudentModel> selectAllStudents() 
	{
		List<StudentModel> students = restTemplate.getForObject(
				"http://localhost:8080/rest/student/viewall", List.class);

		return students;
	}
}
