package com.kivimango.blog.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class BlogPostConverter {
	
	private AuthorConverter authorConverter;
	
	private TextProcessor bbCodeConverter = BBProcessorFactory
			.getInstance().create();

	public BlogPostConverter(AuthorConverter authorConverter) {
		this.authorConverter = authorConverter;
	}
	
	/**
	 * Converts a paginated BlogPost list into a paginated BlogPostView DTO
	 * @param page
	 * @param pageable
	 * @return
	 */
	
	public Page<BlogPostView> convert(Page<BlogPost> page, Pageable pageable) {
		List<BlogPost> list = page.getContent();
		List<BlogPostView> convertedList = new ArrayList<BlogPostView>(page.getSize());
		int size = list.size();
		for(int i = 0; i < size; i++) {
			convertedList.add(convert(list.get(i)));
		}
		return new PageImpl<BlogPostView>(convertedList, pageable, page.getTotalElements());
	}
	
	/**
	 * Converts a BlogPost entity to a BlogPostView DTO.
	 * The point is avoid leakage the database entities into 
	 * the presentation layer due to security reasons.
	 * 
	 * @param post The blog post
	 * @return The converted DTO
	 */
	
	public BlogPostView convert(final BlogPost post) {
		BlogPostView converted = new BlogPostView(post.getTitle(), post.getSlug(), authorConverter.convert(post.getAuthor()),
				bbCodeConverter.process(post.getContent()), post.getUploaded(), post.getEdited(), convertTags(post.getTags()), post.isHidden());
		return converted;
	}

	private List<TagView> convertTags(final List<Tag> tags) {
		int count = tags.size();
		List<TagView> convertedTags = new ArrayList<TagView>(0);
		if(count > 0) {
			for(int i = 0; i < count; i++) {
				convertedTags.add(new TagView(tags.get(i).getTag()));
			}
		}
		return Collections.unmodifiableList(convertedTags);
	}

}
