package com.kivimango.blog.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	private BlogPostConverter converter = new BlogPostConverter();

	@Autowired
	public SearchServiceImpl(BlogPostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<BlogPostView> search(String query) {
		List<BlogPost> result = new ArrayList<BlogPost>();
		result.addAll(postRepository.findByTitleOrSlugOrContentOrTags(query));
		//focusContent(query, result);
		return converter.convert(result);
	}
	
	private void focusContent(String query, List<BlogPost> result) {
		for(int i=0; i<result.size(); i++) {
			String originalContent = result.get(i).getContent();
			String focusedcontent = focusOnKeyword(originalContent, query);
			BlogPost bp = result.get(i);
			//bp.setContent(focusedcontent);
			result.set(i, bp);
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
