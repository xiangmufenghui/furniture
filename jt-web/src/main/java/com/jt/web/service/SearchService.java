package com.jt.web.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.web.pojo.Item;
@Service
public class SearchService {

	@Autowired
	HttpSolrClient httpSolrClient;
	
	public List<Item> queryItemList(String q,Integer page,Integer rows) throws Exception, IOException {
		if (null==page) {page=1;}
		Integer startPos = Math.max(page, 1)*rows;
		SolrQuery query = new SolrQuery();
		query.setQuery("title:"+q);
		query.setStart(startPos);
		query.setRows(rows);
		QueryResponse response =httpSolrClient.query(query);
		List<Item> itemList = response.getBeans(Item.class);
		return itemList;
	}
}
