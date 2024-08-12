package com.example.demo.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SolrService {

    @Autowired
    private SolrClient solrClient;

    @Value("${spring.data.solr.core}")
    private String solrCore;

    public SolrService(@Value("${spring.data.solr.host}") String solrHost) {
        this.solrClient = new HttpSolrClient.Builder(solrHost).build();
    }

    // Apache Solr 단독 실행 = insert
    public void addOrUpdateDocument(String id, String name) throws Exception {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", id);
        document.addField("name", name);
//        document.addField("age", age);
//        document.addField("email", name);
        solrClient.add("test", document);
        solrClient.commit("test");
    }

}