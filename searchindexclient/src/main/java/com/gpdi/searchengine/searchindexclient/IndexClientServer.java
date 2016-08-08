package com.gpdi.searchengine.searchindexclient;


/**
 * 通过curator监听zookeeper的数据节点，获取增量数据，将增量数据转发到dubbo的索引搜索服务
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 11:21:06
 */
public class IndexClientServer implements ClientServer {

	public IndexClientServer(){

	}

	public boolean start(){
		return false;
	}

}