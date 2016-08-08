package com.gpdi.searchengine.searchindexservice.search;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @description: TODO(这里用一句话描述这个类的作用) 搜索结果----
 * @author zhangwu
 * @date 2016年8月8日
 * @version 1.0.0
 */
public class SearchResult {

	// 以docnum-fieldName-fieldValue[]存储返回结果集
	Map<String, Map<String, String[]>> searchResult = new HashMap<String, Map<String, String[]>>();

	public Map<String, Map<String, String[]>> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(Map<String, Map<String, String[]>> searchResult) {
		this.searchResult = searchResult;
	}

	
	
}
