package com.clarabridge.elasticsearch.index.migrate;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;

import org.elasticsearch.index.mapper.internal.RoutingFieldMapper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.percolator.PercolatorService;

import org.elasticsearch.search.SearchHit;

import org.elasticsearch.common.unit.TimeValue;


import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;

public class ScanTest extends ESTestBase {
    private static final Logger log = LoggerFactory.getLogger(ScanTest.class);

    private final static long projectId = 1428;
    private final static long generation = 0;

    private void doSleep(long millis) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
    }

    private void addType(Map<String, Long> indexTypes, String type) {
        Long cnt = indexTypes.get(type);
        if (cnt == null) {
            cnt = new Long(0);
        }
        indexTypes.put(type, cnt + 1);
    }

    private void doScanIndex(String indexName, String [] typesToScan) {
        Map<String, Long> indexTypes = new HashMap<String, Long>();

        SearchRequestBuilder srb = client.prepareSearch(indexName)
            .setTypes(typesToScan)
            .setSearchType(SearchType.SCAN)
            .setScroll(TimeValue.timeValueMinutes(30))
            .setQuery(matchAllQuery())
            .setSize(10000)
        ;
        //srb.addFieldDataField(RoutingFieldMapper.NAME);

        SearchResponse resp = srb.get();
        do {
            for (SearchHit hit : resp.getHits()) {
                String hitType = hit.getType();
                addType(indexTypes, hitType);
            }
            //doSleep(200);
            resp = client.prepareSearchScroll(resp.getScrollId())
                //.setScroll(TimeValue.timeValueHours(240))
                .setScroll(TimeValue.timeValueMillis(100))
                .get();
        }
        while (resp.getHits().hits().length > 0);

        for (Map.Entry<String, Long> type : indexTypes.entrySet()) {
            log.info("type: {}, cnt: {}", type.getKey(), type.getValue());
        }
    }

    @Test
    public void testScanIndex() throws Exception {
        String indexName = Long.toString(projectId) + "_" + Long.toString(generation);
        String [] typesToScan = new String [] {PercolatorService.TYPE_NAME, "document", "sentence", "verbatim"};

        doScanIndex(indexName, typesToScan);
    }
}
