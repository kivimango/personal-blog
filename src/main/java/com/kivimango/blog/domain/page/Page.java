package com.kivimango.blog.domain.page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class Page<E> {

    private int pageNumber;
    private int pagesAvailable;
    private List<E> content = new ArrayList<E>();

	public Page(int pageNumber, int pagesAvailable, List<E> pageItems) {
		this.pageNumber = pageNumber;
		this.pagesAvailable = pagesAvailable;
		this.content = pageItems;
	}

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPagesAvailable() {
        return pagesAvailable;
    }

    public List<E> getContent() {
        return content;
    }
}
