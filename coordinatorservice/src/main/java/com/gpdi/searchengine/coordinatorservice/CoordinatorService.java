package com.gpdi.searchengine.coordinatorservice;

import java.lang.String;
import org.apache.zookeeper.data.Stat;

/**
 * 协调服务，负责节点的创建、节点的删除、节点数据的获取、节点数据的更新
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 10:31:44
 */
public class CoordinatorService {

	public CoordinatorService(){

	}

	/**
	 * 
	 * @param path
	 * @param data[]
	 */
	public String create(String path, byte data[]){
		return "";
	}

	/**
	 * 
	 * @param path
	 * @param version
	 */
	public int delete(String path, int version){
		return 0;
	}

	/**
	 * 
	 * @param path
	 * @param watch
	 * @param stat
	 */
	public String getData(String path, boolean watch, Stat stat){
		return "";
	}

	/**
	 * 
	 * @param path
	 * @param data[]
	 * @param version
	 */
	public Stat setData(String path, byte data[], int version){
		return null;
	}

}