package com.gpdi.searchengine.searchindexservice;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NRTManager;
import org.apache.lucene.search.NRTManagerReopenThread;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.SearcherWarmer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gpdi.searchengine.searchindexservice.conf.NRTManagerServiceConf;

/**
 * 索引管理服务，对索引进行管理，管理NRTManager/<b>IndexWriter</b>，负责索引的创建、索引的更新、索引的删除，提供<b>
 * Searcher Manager进行近实时搜索</b> 此类中不进行参数等检测
 * 
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 11:21:06
 */
public class NRTManagerService {

	private Logger logger = LoggerFactory.getLogger(NRTManagerService.class);
	private IndexWriter indexWriter;
	private NRTManagerServiceConf confInfos;
	private String areaCode;
	// 分析器
	private Analyzer analyzer;
	private NRTManager nrtManagert;
	private SearcherManager searcherManager;

	public NRTManagerService(NRTManagerServiceConf confInfos, String areaCode) {
		try {
			this.confInfos = confInfos;
			this.areaCode = areaCode;
			nrtManagert = new NRTManager(getIndexWriter(getDirectory()),
					new SearcherWarmer() {

						@Override
						public void warm(IndexSearcher s) throws IOException {
							// TODO Auto-generated method stub
							logger.info("Reopen IndexSearcher");
						}
					});
			searcherManager = nrtManagert.getSearcherManager(true);
			NRTManagerReopenThread nrtManagerReopenThread = new NRTManagerReopenThread(
					nrtManagert, this.confInfos.getTargetMaxScaleSec(),
					this.confInfos.getTargetMinStaleSec());
			nrtManagerReopenThread.setDaemon(true);
			nrtManagerReopenThread.setName("nRTManagerReopenThread");
			nrtManagerReopenThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("create NRTManagerService error:" + e.getMessage());
		}
	}

	/**
	 * 添加索引
	 * 
	 * @param document
	 * @return
	 */
	public boolean index(Document document) {
		try {
			// 3.创建Document
			writeDocument(document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("writeDocument  error:" + e.getMessage());
			return false;
		}
		return true;

	}

	/**
	 * 根据查询 删除特索引
	 * 
	 * @param query
	 * @return
	 */
	public boolean delete(Query query) {
		try {
			logger.info("query:" + query);
			nrtManagert.deleteDocuments(query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("deleteDocuments  error:" + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 更新特定索引
	 * 
	 * @param term
	 * @param document
	 * @return
	 */
	public boolean update(Term term, Document document) {
		try {
			nrtManagert.updateDocument(term, document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("updateDocument  error:" + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 定时的刷新内存索引到磁盘文件索引
	 * 
	 * @return
	 */
	public boolean commitIndex() {
		boolean resu = true;
		try {
			if (indexWriter != null)
				indexWriter.commit();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			logger.error("commitIndex  error:" + e.getMessage());
			resu = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("commitIndex  error:" + e.getMessage());
			resu = false;
		}
		return resu;
	}

	/**
	 * 创建目录
	 * 
	 * @return
	 * @throws IOException
	 */
	private Directory getDirectory() throws IOException {
		Directory directory = null;
		if (this.confInfos.isRamDirectory()) {
			directory = new RAMDirectory();
		} else {
			directory = FSDirectory.open(new File(this.confInfos
					.getIndexStorePath(this.areaCode)));
		}
		return directory;
	}

	/**
	 * 单例保存indexWriter
	 * 
	 * @param directory
	 * @return indexWriter
	 * @throws IOException
	 */
	private IndexWriter getIndexWriter(Directory directory) throws IOException {
		if (indexWriter == null) {
			synchronized (NRTManagerService.class) {
				if (indexWriter == null) {
					IndexWriterConfig conf = new IndexWriterConfig(
							Version.LUCENE_35, getAnalyzer());
					indexWriter = new IndexWriter(directory, conf);
				}
			}
		}
		return indexWriter;
	}

	private void writeDocument(Document document) throws IOException {
		// 使用nrtManagert将Document写入索引
		nrtManagert.addDocument(document);
	}

	public SearcherManager getSearcherManager() {
		return searcherManager;
	}

	public Analyzer getAnalyzer() {
		if (analyzer == null) {
			analyzer = new StandardAnalyzer(Version.LUCENE_35);
		}
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

}