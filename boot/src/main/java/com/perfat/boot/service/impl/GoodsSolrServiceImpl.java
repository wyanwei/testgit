/*
 * Chsi
 * Created on 2019-04-12
 */
package com.perfat.boot.service.impl;

import com.perfat.boot.entity.GoodsData;
import com.perfat.boot.service.GoodsSolrService;
import com.perfat.boot.support.bean.GoodBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: GoodsSolrServiceImpl.java 16 2019-08-07 08:28:05Z 二进制 $
 */
@Service
public class GoodsSolrServiceImpl implements GoodsSolrService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    //private SolrClient solrClient;

    @Override
    public void saveGoodsData(GoodsData goodsData) {
        /*try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", goodsData.getId());
            doc.setField("number", goodsData.getNumber());
            doc.setField("name", goodsData.getName());
            doc.setField("updateTime", goodsData.getUpdateTime().getTime());
            solrClient.add(doc);
            solrClient.commit();
        } catch ( Exception e ) {
            log.info("保存商品信息到solr异常：{}", e);
        } finally {
            try {
                solrClient.close();
            } catch ( IOException e ) {
                log.info("关闭solrClient异常：{}", e);
            }
        }*/
    }

    @Override
    public void saveGoodsDataByBean(GoodsData goodsData) {
        GoodBean goodBean = new GoodBean();
        goodBean.setId(goodsData.getId());
        goodBean.setName(goodsData.getName());
        goodBean.setNumber(goodsData.getNumber());
        goodBean.setUpdateTime(goodsData.getUpdateTime().getTime());
        goodBean.setBrand(goodsData.getBrand());
        goodBean.setDesc(goodsData.getDesc());
        goodBean.setPrice(goodsData.getPrice());
        try {
            //solrClient.addBean(goodBean);
            //solrClient.commit();
        } catch ( Exception e ) {
            log.info("保存商品信息到solr异常：{}", e);
        } finally {
            /*try {
                solrClient.close();
            } catch ( IOException e ) {
                log.info("关闭solrClient异常：{}", e);
            }*/
        }
    }

    @Override
    public void saveGoodsDataByBeanList(List<GoodsData> goodsDataList) {
        if ( null == goodsDataList || goodsDataList.isEmpty() ) {
            return;
        }
        List<GoodBean> beanList = new ArrayList<>(goodsDataList.size());
        for ( GoodsData goodsData : goodsDataList ) {
            GoodBean goodBean = new GoodBean();
            goodBean.setId(goodsData.getId());
            goodBean.setName(goodsData.getName());
            goodBean.setNumber(goodsData.getNumber());
            goodBean.setUpdateTime(goodsData.getUpdateTime().getTime());
            goodBean.setBrand(goodsData.getBrand());
            goodBean.setDesc(goodsData.getDesc());
            goodBean.setPrice(goodsData.getPrice());
            beanList.add(goodBean);
        }
        try {
            //solrClient.addBeans(beanList);
            //solrClient.commit();
        } catch ( Exception e ) {
            log.info("批量保存商品信息到solr异常：{}", e);
        } finally {
            /*try {
                solrClient.close();
            } catch ( IOException e ) {
                log.info("关闭solrClient异常：{}", e);
            }*/
        }
    }

    @Override
    public List<GoodBean> queryGoodBean(String keywords) {
       /* try {
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery(StringUtils.isBlank(keywords) ? "*" : keywords + "*");
            solrQuery.set("df", "name");
            solrQuery.setRows(Integer.MAX_VALUE);
            solrQuery.setHighlight(true);
            solrQuery.setHighlightSimplePre("<font color='red'>");
            solrQuery.setHighlightSimplePost("</font>");
            solrQuery.addHighlightField("name");
            QueryResponse queryResponse = solrClient.query(solrQuery);
            if ( solrQuery.getHighlight() ) {
                convertHighLightResult(queryResponse);
            }
            return queryResponse.getBeans(GoodBean.class);
        } catch ( SolrServerException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }*/
        return null;
    }

    /*private void convertHighLightResult(QueryResponse queryResponse) {
        SolrDocumentList documentList = queryResponse.getResults();
        if ( null == documentList || documentList.isEmpty() ) {
            return;
        }
        if ( null == queryResponse.getHighlighting() || queryResponse.getHighlighting().isEmpty() ) {
            return;
        }
        for ( SolrDocument solrDocument : documentList ) {
            Map listMap = (Map) queryResponse.getHighlighting().get(solrDocument.get("id"));
            if ( null == listMap || listMap.isEmpty() ) {
                continue;
            }
            List<String> resultList = (List) listMap.get("name");
            if ( null == resultList || resultList.isEmpty() ) {
                continue;
            }
            solrDocument.setField("name", resultList.get(0));
        }
    }*/

    @Override
    public void statisticsGoodsInfo() {
       /* SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*");
        solrQuery.setFacet(true);
        solrQuery.addFacetField(new String[]{"name", "number"});
        solrQuery.setRows(0);
        solrQuery.setFacetMissing(false);
*/
        /*try {
            QueryResponse queryResponse = solrClient.query(solrQuery);
            List<FacetField> list = queryResponse.getFacetFields();
            if ( null == list || list.isEmpty() ) {
                return;
            }
            for ( FacetField facetField : list ) {
                System.out.println(facetField.getName());
                List<FacetField.Count> countList = facetField.getValues();
                if ( null == countList || countList.isEmpty() ) {
                    continue;
                }
                for ( FacetField.Count count : countList ) {
                    System.out.println(count.getName() + "===" + count.getCount());
                }
            }
        } catch ( SolrServerException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }*/
    }
}
