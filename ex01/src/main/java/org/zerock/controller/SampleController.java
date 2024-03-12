package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
		
	@GetMapping("/basic")  // localhost:8080/sample/basic  로 들어오는 모든 메서드 형태의 요청을 다 받음 
	public void basic() {
		log.info("basic....................");
	}
	
	//localhost:8080/sample/basic로 들어온느 요청 중 http메서드가 get인 형태만 받음
//	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST}) 
//	public void basicGet() {
//		log.info("basic get....................");
//	}
//	
	//표현만 다르지 위와 같음
//	@GetMapping("/basicOnlyGet")
//	public void basicGet2() {
//		log.info("basic get only get....................");
//	}
	
	
	
	//--------------------------------------------------------------------------------------------------
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		log.info("name : " + name);
		log.info("age : " + age);
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids : " + ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids")String[] ids) {
		log.info("array ids : " + Arrays.toString(ids));
		return "ex02Array";
	}
	
	//http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos : " + list);
		return "ex02Bean";
	}
	
	//http://localhost:8080/sample/ex03?title=test&dueDate=2024-03-30
		//1번															//2번 --> TodoDTO
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo : " + todo);
		return "ex03";
	}
	
	//http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		return "/sample/ex04";
	}
		
	
	//localhost:8080/sample/exRedirect?data=abc
	@GetMapping("/exRedirect")
	public String exRedirect(String data, RedirectAttributes rttr) {
//		model.addAttribute(data);
		rttr.addFlashAttribute("data", data);
		return "redirect:/sample/exRedirect2";
	}
	@GetMapping("/exRedirect2")
	public String exRedirect2() {
		return "/sample/exRedirect";
	}
	
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05............");
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06..........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07................");
		String msg = "{메세지}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		header.add("testheader", "testContent");
		return new ResponseEntity<String>(msg, header, HttpStatus.CREATED);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {  //void => sample/exUpload
		log.info("/exUpload..................");
	}
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("-------------------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});
	}
	
}
