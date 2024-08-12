package com.example.demo.Controller;

import com.example.demo.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/solr")
public class Controller {

    @Autowired
    private SolrService solrService;

    // Apache Solr 단독 실행 = insert
    @PostMapping("/insert")
    public ResponseEntity<?> addDocument(@RequestBody Map<String, Object> documentData) {
        try {
            String id = documentData.get("id").toString();
            String name = documentData.get("name").toString();
//            int age = Integer.parseInt(documentData.get("age").toString());
            solrService.addOrUpdateDocument(id, name);
            return new ResponseEntity<>("Solr 코어 필드 값 추가", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("예외발생", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
