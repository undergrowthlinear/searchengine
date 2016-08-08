package com.gpdi.searchengine.relayservice;

import com.gpdi.searchengine.coordinatorservice.CoordinatorService;
import com.gpdi.searchengine.dataservice.DataService;


/**
 * 负责将增量数据放置在zookeeper节点上，放置在redis中
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 10:31:45
 */
public class RelayService {

	private CoordinatorService coordinatorService;
	private DataService dataService;

	public RelayService(){

	}

	/**
	 * 
	 * @param updateData
	 */
	public boolean insertCacheData(String updateData){
		return false;
	}

	/**
	 * 
	 * @param updateData
	 */
	public boolean updateCoordinatorData(String updateData){
		return false;
	}

}