package com.gpdi.searchengine.searchindexservice.index;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @description: TODO(这里用一句话描述这个类的作用) 用于添加的docObject，添加索引时转换为Document
 * @author zhangwu
 * @date 2016年8月8日
 * @version 1.0.0
 */
public class DocObject {

	private List<DocField> docFields = new ArrayList<DocField>();

	private float boost = 1.0f;

	public void setBoost(float boost) {
		this.boost = boost;
	}

	@Override
	public String toString() {
		return "DocObject [docFields=" + docFields + ",docFields.size="
				+ docFields.size() + ", boost=" + boost + "]";
	}

}
