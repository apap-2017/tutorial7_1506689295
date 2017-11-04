package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dao.CourseDAO;
import com.example.model.CourseModel;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseDAOImpl implements CourseDAO
{
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
	{
	   return builder.build();
	}
	
	@Override
	public CourseModel selectCourse (String id_course)
	{
		CourseModel course = restTemplate.getForObject(
				"http://localhost:8080/rest/course/view/"+ id_course,
				CourseModel.class);
		return course;
	}

	@Override
	public List<CourseModel> selectAllCourses() 
	{
		List<CourseModel> courses = restTemplate.getForObject(
				"http://localhost:8080/rest/course/viewall", List.class);

		return courses;
	}
}
