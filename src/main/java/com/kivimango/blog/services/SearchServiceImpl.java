package com.kivimango.blog.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kivimango.blog.domain.AuthorConverter;
import com.kivimango.blog.domain.BlogPostConverter;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.repositories.BlogPostRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Service
public class SearchServiceImpl implements SearchService {
	
	private BlogPostRepository postRepository;
	private BlogPostConverter converter = new BlogPostConverter(new AuthorConverter());

	@Autowired
	public SearchServiceImpl(BlogPostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<BlogPostView> search(String query) {
		List<BlogPost> result = new ArrayList<BlogPost>();
		result.addAll(postRepository.findByTitleIgnoreCaseContaining(query));
		
		// Search results may contains duplicate elements, checking for uniqueness
		
		List<BlogPost> foundBySlug = postRepository.findBySlugIgnoreCaseContaining(query);
		addUnique(result, foundBySlug);
		
		List<BlogPost> foundByContent = postRepository.findByContentIgnoreCaseContaining(query);
		addUnique(result, foundByContent);
		
		// TODO: add search by tags
		// TODO: merge the 3 search method into one excluding duplicates and hidden posts
		
		List<BlogPostView> convertedResult = converter.convert(result);
		focusContent(query, result);
		return convertedResult;
	}
	
	/**
	 * Adding the unique elements of a list to the appending list
	 */
	
	private void addUnique(List<BlogPost> result, List<BlogPost> part) {
		for(int i=0; i<part.size(); i++) {
			if(!result.contains(part.get(i))) result.add(part.get(i));
		}
	}
	
	private void focusContent(String query, List<BlogPost> result) {
		for(int i=0; i<result.size(); i++) {
			String originalContent = result.get(i).getContent();
			String focusedcontent = focusOnKeyword(originalContent, query);
			result.get(i).setContent(focusedcontent);
		}
	}
	
	private String focusOnKeyword(String content, String query) {
		int spacer = 5;
		int start = content.indexOf(query);
		if(start != -1 && content.length() > (spacer + query.length())) {
			return "..." + content.substring(start, start + query.length() + spacer) + "...";
		}
		return content;
	}
	
}
