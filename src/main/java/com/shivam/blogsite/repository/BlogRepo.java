package com.shivam.blogsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.blogsite.entity.Blog;

public interface BlogRepo extends JpaRepository<Blog, Long>{

}
