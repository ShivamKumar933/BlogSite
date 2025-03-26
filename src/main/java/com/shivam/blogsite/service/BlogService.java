package com.shivam.blogsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.blogsite.entity.Blog;
import com.shivam.blogsite.repository.BlogRepo;

@Service
public class BlogService {
	@Autowired
	private BlogRepo bp;

	public boolean createBlog(Blog b) {
		try {
			bp.save(b);
			return true;
		}catch (Exception e) {
			System.out.println("Exception : " + e);
		}
		return false;
	}
	
	public List<Blog> getAllBlogs(){
		return bp.findAll();
	}
	
	public Blog getBlogById(Long id) {
		try {
			Blog blog = bp.findById(id).get();
			return blog;
		}catch (Exception e) {
			return null;
		}
	}
	
	public boolean updateBlogById(Blog b, Long id) {
		Blog prev = getBlogById(id);
		if(prev!=null) {
			prev.setTitle(b.getTitle() != null && !b.getTitle().equals("") ? b.getTitle() : prev.getTitle());
			prev.setContent(b.getContent() != null && !b.getContent().equals("") ? b.getContent() : prev.getContent());
			return createBlog(prev);
		}
		return false;
	}
	
	
	public boolean deleteById(Long id) {
		if(getBlogById(id)!=null) {
			bp.deleteById(id);
			return true;
		}
		return false;
	}
}
