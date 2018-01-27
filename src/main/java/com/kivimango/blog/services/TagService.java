package com.kivimango.blog.services;

import java.util.List;
import com.kivimango.blog.domain.entity.Tag;

/** 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface TagService {
	
	List<Tag> findAll();

}
