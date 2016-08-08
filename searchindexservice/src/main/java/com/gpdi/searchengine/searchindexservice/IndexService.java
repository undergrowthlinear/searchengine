package com.gpdi.searchengine.searchindexservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;

import com.gpdi.searchengine.searchindexservice.conf.NRTManagerServiceConf;

/**
 * 索引服务，对索引进行管理，管理NRTManagerService，负责索引的创建、索引的更新、索引的删除
 * 
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 11:21:06
 */
public class IndexService {

	public List<NRTManagerService> nrtManagerServices;
	private NRTManagerServiceConf nrtConf;
	// 分析器
	private Analyzer analyzer;

	public List<NRTManagerService> getNrtManagerServices() {
		return nrtManagerServices;
	}

	public void setNrtManagerServices(List<NRTManagerService> nrtManagerServices) {
		this.nrtManagerServices = nrtManagerServices;
	}


	
	
	public NRTManagerServiceConf getNrtConf() {
		return nrtConf;
	}

	public void setNrtConf(NRTManagerServiceConf nrtConf) {
		this.nrtConf = nrtConf;
	}

	/**
	 * 初始化的时候 根据区域列表 打开特定目录的索引器和搜索器
	 * 
	 * @return
	 */
	public boolean initIndexSearcher() {
		for (String areaCode : nrtConf.getAreaList()) {
			NRTManagerService nrtManagerService = new NRTManagerService(
					nrtConf, areaCode);
			nrtManagerService.setAnalyzer(getAnalyzer());
			nrtManagerServices.add(nrtManagerService);
		}
		return true;
	}

	/**
	 * 当索引创建后 使用nrtManager创建对应的SearchManager 提供近实时功能
	 * 
	 * @return
	 */
	public List<SearcherManagerService> getSearcherManagerServices() {
		List<SearcherManagerService> searcherManagerServices = new ArrayList<SearcherManagerService>();
		for (NRTManagerService nrtManagerService : nrtManagerServices) {
			searcherManagerServices.add(new SearcherManagerService(
					nrtManagerService.getSearcherManager(), nrtManagerService
							.getAnalyzer()));
		}
		return searcherManagerServices;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

}