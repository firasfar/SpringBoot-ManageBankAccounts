package tn.iit.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tn.iit.dto.StudentDto;

@Controller
@RequestMapping("/students")
public class HelloController {
	private List<StudentDto> students = new ArrayList<>();

	public HelloController() {
		students.add(new StudentDto(1L, "Madame Chaabane", 'F'));
		students.add(new StudentDto(2L, "Abdelkader", 'M'));
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name,
			@RequestParam(name = "gender") char gender) {
		students.add(new StudentDto(id, name, gender));

		return "redirect:/students/all";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		StudentDto student = null;
		for (StudentDto dto : students) {
			if (dto.getId().equals(id)) {
				student = dto;
				break;
			}
		}
		modelAndView.addObject("student", student);

		modelAndView.setViewName("edit-student");// go to hello.html (sans extension)

		return modelAndView;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		Iterator<StudentDto> iterator = students.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId().equals(id)) {
				iterator.remove();
				break;
			}
		}
		return "redirect:/students/all";
	}

	@PostMapping("/delete-ajax")
	@ResponseBody
	public void deleteAjax(@RequestParam(name = "id") Long id) {
		Iterator<StudentDto> iterator = students.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId().equals(id)) {
				iterator.remove();
				break;
			}
		}
	}

	@PostMapping(value = "/update")
	public String update(@ModelAttribute StudentDto student) {
		for (int i = 0; i < students.size(); i++) {

			if (students.get(i).getId().equals(student.getId())) {
				students.set(i, student);
				break;
			}
		}
		return "redirect:/students/all";
	}

	@GetMapping("/all-json")
	@ResponseBody // le retour est json (api jackson)
	public List<StudentDto> getAllJson() {
		return students;
	}

	@GetMapping("/all")
	public ModelAndView getAll() {
		ModelAndView modelAndView = new ModelAndView();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
		String serverTime = date.format(formatter);
		modelAndView.addObject("serverTime", serverTime);

		modelAndView.addObject("students", students);

		modelAndView.setViewName("students");// go to hello.html (sans extension)

		return modelAndView;
	}

}
