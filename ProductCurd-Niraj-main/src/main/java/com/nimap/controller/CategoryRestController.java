package com.nimap.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.entity.Category;
import com.nimap.repository.ICategoryRepository;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

	@Autowired
	private ICategoryRepository categoryRepository;

//	@GetMapping("/findAllCategories")
//	public Page<Category> getAllCategories(Pageable pageable) {
//		return categoryRepository.findAll(pageable);
//	}
	
	@GetMapping("/findAllCategories")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@PostMapping("/create")
	public String createCategory(@RequestBody Category category) {
		return categoryRepository.save(category) + " Category created Successfully";
	}

	@GetMapping("/getOne/{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@PutMapping("/update/{id}")
	public Category UpdateCategory(@PathVariable Long id, @RequestBody Category category) {
		Category category2 = categoryRepository.findById(id).orElse(null);
		if (category2 != null) {
			category2.setName(category.getName());
			return categoryRepository.save(category);
		} else {
			return null;
		}
	}

	@DeleteMapping("/delete/{id}")
	public void deleteCategory(@PathVariable Long id) {
		categoryRepository.deleteById(id);
	}

}
