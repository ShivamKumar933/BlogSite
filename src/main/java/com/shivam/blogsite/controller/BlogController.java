package com.shivam.blogsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.blogsite.entity.Blog;
import com.shivam.blogsite.service.ApyhubService;
import com.shivam.blogsite.service.BlogService;
import com.shivam.blogsite.service.OpenAIService;

@RestController
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService bs;
	@Autowired
	private OpenAIService openAIService;
	
	@PostMapping("/create")
	public ResponseEntity<HttpStatus> createBlog(@RequestBody Blog b) {
		if(bs.createBlog(b)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> allBlogs(){
		List<Blog> blogList = bs.getAllBlogs();
		if(blogList==null || blogList.isEmpty()) {
			return new ResponseEntity<>("No blogs to display", HttpStatus.OK);
		}
		return new ResponseEntity<>(blogList,HttpStatus.OK);
				
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> findBlog(@RequestParam(name = "id") Long id) {
		Blog blog = bs.getBlogById(id);
		if(blog !=null) {
			return new ResponseEntity<>(blog, HttpStatus.OK);
		}
		return new ResponseEntity<>("Not present",HttpStatus.NOT_FOUND);
		 
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateBlog(@RequestBody Blog b, @RequestParam(name = "id") Long id) {
		if(bs.updateBlogById(b, id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteBlog(@RequestParam(name = "id") Long id){
		if(bs.deleteById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/summarize")
	public ResponseEntity<?> getSummarization(@RequestParam(name="id") Long id) {
		Blog blog= bs.getBlogById(id);
		if(blog !=null) {
			String result = openAIService.summarizeText(blog.getContent());
			if(result!=null) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<>("Api call limit exceeded!", HttpStatus.TOO_MANY_REQUESTS);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
