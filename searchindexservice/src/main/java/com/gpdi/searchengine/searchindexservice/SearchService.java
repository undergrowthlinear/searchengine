package com.gpdi.searchengine.searchindexservice;

import java.util.List;


/**
 * 负责搜索服务，管理<b>SearcherManagerService服务，提供IndexSearcher的获取与释放，同时SearcherManagerServ
 * ice中的SearcherManager来源于NRTManagerService</b>
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 11:21:06
 */
public class SearchService {

	private List<SearcherManagerService> searcherManagerServices;


	public List<SearcherManagerService> getSearcherManagerServices() {
		return searcherManagerServices;
	}

	public void setSearcherManagerServices(
			List<SearcherManagerService> searcherManagerServices) {
		this.searcherManagerServices = searcherManagerServices;
	}

	
	
}