package com.kivimango.blog.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kivimango.blog.domain.entity.Tag;
import com.kivimango.blog.repositories.TagRepository;

/** 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	private TagRepository repo;

	@Override
	public List<Tag> getFirstTenTags() {
		return repo.findTop10ByOrderByTagAsc();
	}

}
