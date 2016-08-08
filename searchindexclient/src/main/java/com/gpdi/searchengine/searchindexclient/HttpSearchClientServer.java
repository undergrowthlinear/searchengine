package com.gpdi.searchengine.searchindexclient;


/**
 * 以Http的方式对外提供接口，对内封装以dubbo提供服务的生产者
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 11:21:06
 */
public class HttpSearchClientServer implements ClientServer {

	public HttpSearchClientServer(){

	}

	public boolean start(){
		return false;
	}

}