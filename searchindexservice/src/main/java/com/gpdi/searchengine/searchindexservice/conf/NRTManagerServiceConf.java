package com.gpdi.searchengine.searchindexservice.conf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @description: TODO(这里用一句话描述这个类的作用)
 * @author zhangwu
 * @date 2016年8月8日
 * @version 1.0.0
 */
@Component("nrtConf")
public class NRTManagerServiceConf {

	// 存放区域节点的代码
	@Value("${searchindex.areaList}")
	private String[] areaList;
	// 存放不同节点的索引和搜索目录
	private Map<String, String> areaInndexStorePath = new ConcurrentHashMap<String, String>();
	@Value("${searchindex.indexStorePath}")
	private String indexBaseStorePath;
	@Value("${searchindex.isRamDirectory}")
	private boolean isRamDirectory;
	@Value("${indexReaderOpened.targetMaxScaleSec}")
	private double targetMaxScaleSec;
	@Value("${indexReaderOpened.targetMinStaleSec}")
	private double targetMinStaleSec;

	public NRTManagerServiceConf() {
	}

	public String getIndexStorePath(String areaCode) {
		return areaInndexStorePath.get(areaCode);
	}

	public void setIndexBaseStorePath(String indexBaseStorePath) {
		this.indexBaseStorePath = indexBaseStorePath;
	}

	public boolean isRamDirectory() {
		return isRamDirectory;
	}

	public void setRamDirectory(boolean isRamDirectory) {
		this.isRamDirectory = isRamDirectory;
	}

	public double getTargetMaxScaleSec() {
		return targetMaxScaleSec;
	}

	public void setTargetMaxScaleSec(double targetMaxScaleSec) {
		this.targetMaxScaleSec = targetMaxScaleSec;
	}

	public double getTargetMinStaleSec() {
		return targetMinStaleSec;
	}

	public void setTargetMinStaleSec(double targetMinStaleSec) {
		this.targetMinStaleSec = targetMinStaleSec;
	}

	public String[] getAreaList() {
		return areaList;
	}

	public void setAreaList(String areaList) {
		this.areaList = areaList.split(",");
		initAreaInndexStorePath();
	}

	/**
	 * 初始化各节点存储目录
	 */
	private void initAreaInndexStorePath() {
		// TODO Auto-generated method stub
		for (String areaCode : areaList) {
			areaInndexStorePath.put(areaCode,
					indexBaseStorePath + System.getProperty("file.separator")
							+ areaCode);
		}
	}

}