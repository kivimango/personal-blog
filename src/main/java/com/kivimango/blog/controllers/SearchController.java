package com.kivimango.blog.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.services.SearchService;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Controller
public class SearchController {
	
	private SearchService searchService;
	
	@Value("${blog.title}")
	private String title;
	
	@Value("${blog.description}")
	private String description;
	
	private static final String SEARCH_PAGE= "search";

	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping("/keres")
	public String searchForm(Model model) {
		model.addAttribute("blogTitle", title);
		model.addAttribute("title", title + " - " + description);
		model.addAttribute("query", "");
		return SEARCH_PAGE;
	}
	
	@PostMapping("/keres")
	public String searchResults(Model model, @RequestParam(name="q",required=true) String query) {
		model.addAttribute("blogTitle", title);
		model.addAttribute("title", title + " - " + description);
		model.addAttribute("query", query);
		List<BlogPostView> results = new ArrayList<BlogPostView>();
		if(query != null && query.length() >= 3) results = searchService.search(query);
		model.addAttribute("results", results);
		return SEARCH_PAGE;
	}
	
}
