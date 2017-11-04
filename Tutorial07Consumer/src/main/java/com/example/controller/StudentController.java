package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("page_title", "Home");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    	model.addAttribute("page_title", "Add Student");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa,
			Model model)
    {
    	model.addAttribute("page_title", "Success Add");
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
        	model.addAttribute("page-title", "View Student");
            model.addAttribute ("student", student);
            return "view";
        } else {
        	model.addAttribute("page_title", "Student Not Found");
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model, @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
        	model.addAttribute("page_title", "View Student");
            model.addAttribute ("student", student);
            return "view";
        } else {
        	model.addAttribute("page-title", "Student Not Found");
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }
    
    @RequestMapping("/course/view/{id}")
    public String viewCoursePath(Model model, @PathVariable(value = "id") String id)
    {
    	CourseModel course = studentDAO.selectCourse(id);
    	model.addAttribute("page_title", "View Course");
    	model.addAttribute("course", course);
    	return "viewCourse";
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute("page_title", "View All Student");
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (@PathVariable(value = "npm") String npm, Model model)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
        if (student != null) {
        	model.addAttribute("page_title", "Student Deleted");
        	studentDAO.deleteStudent (npm);
        	return "delete";
        }else {
        	return "not-found";
        } 
    }

    @RequestMapping("/student/update/{npm}")
    public String update (@PathVariable(value = "npm") String npm, Model model)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
        if (student != null) {
        	model.addAttribute("page_title", "Update Student");
        	model.addAttribute ("student", student);
        	return "form-update";
        }else {
        	return "not-found";
        }
       
    }
    
    @RequestMapping(value = "/view/produk/{id}", method = RequestMethod.POST)
    public String updateSubmit(StudentModel student, Model model)
    {
    	model.addAttribute("page_title", "Student Updated");
    	studentDAO.updateStudent(student);
    	return "viewproduk";
    }
}
