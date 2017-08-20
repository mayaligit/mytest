/**
 * @Title:SolrTest.java
   @Package cn.itcast.solr.test
   @Description:TODO
   @author myl
   @date 2017年8月17日下午10:34:16
   @version V1.0


*/
package cn.itcast.solr.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.solr.domain.Item;

/**
 * @author Administrator
 *
 */
public class SolrTest {

	//定义solrserver操作solr的服务器
	private SolrServer solrServer;
	
	@Before
	public void before(){
		String baseURL="http://localhost:8080/solr/collection1/";
		this.solrServer = new HttpSolrServer(baseURL);
	}
	
	@Test
	public void saveOrUpdate() throws SolrServerException, IOException{
		//创建文档的对象
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "1");
		doc.addField("title", "华为18");
		doc.addField("sellPoint", "华为8，好用！");
		doc.addField("price", "280000");
		doc.addField("image", "http://image.jd.com/1.jpg");
		doc.addField("status", "1");
		//添加或者修改的额判断返回响应的对象
		
		UpdateResponse updateResponse = solrServer.add(doc);
		System.out.println("状态码：" + updateResponse.getStatus());
		if(updateResponse.getStatus()==0){
			//提交事务
			solrServer.commit();
		}else{
			solrServer.rollback();
		}
	}
	@Test  /*添加修改的第二种方式*/
	public void saveOrUpdate2() throws SolrServerException, IOException{
		//创建一个item对象
		Item item = new Item();
		item.setId(3L);
		item.setTitle("华为7");
		item.setPrice(340000L);
		item.setImage("http://image.taotao.com/jd/efa001a9eae3498d8af1cbb804fc358a.jpg");
		item.setSellPoint("中国华为手机真好用3！");
		item.setStatus(1);

		
		//添加或者修改文档，判断返回响应的对象
		
		UpdateResponse updateResponse = solrServer.addBean(item);
		System.out.println("状态码：" + updateResponse.getStatus());
		if(updateResponse.getStatus()==0){
			//提交事务
			solrServer.commit();
		}else{
			solrServer.rollback();
		}
	}
	@Test
	public void delete() throws Exception{
		//根据id删除	
		UpdateResponse updateResponse = solrServer.deleteById("1");
		System.out.println("状态码：" + updateResponse.getStatus());
		//判断响应状态码: 0 代表成功
		if(updateResponse.getStatus()==0){
			//提交事务
			solrServer.commit();
		}else{
			solrServer.rollback();
		}
	}
	
	@Test   //批量删除
	public void delete2() throws Exception{
		List<String> ids=new ArrayList<>();
		//根据id删除	
		UpdateResponse updateResponse = solrServer.deleteById(ids);
		System.out.println("状态码：" + updateResponse.getStatus());
		//判断响应状态码: 0 代表成功
		if(updateResponse.getStatus()==0){
			//提交事务
			solrServer.commit();
		}else{
			solrServer.rollback();
		}
	}
	
	@Test   //查询
	public void query() throws Exception{
			//定义solrQuery封装查询的参数
		SolrQuery solrQuery = new SolrQuery();
		/** 设置查询对象 q */
		solrQuery.setQuery("title:手机");
		/** 设置过滤条件  fq */
		solrQuery.setFilterQueries("price:[200000 TO 600000]");
		/** 设置排序  sort */
		solrQuery.setSort("price", ORDER.desc);
		/** 设置起始记录数 start */
		solrQuery.setStart(0);
		/** 设置每页显示的记录数 rows */
		solrQuery.setRows(5);
		/** 设置显示的field fl */
		solrQuery.setFields("id,title,price");
		
		//设置是否高亮
		solrQuery.setHighlight(true);
		//设置文本截断
		solrQuery.setHighlightFragsize(20);
		//添加高亮的字段
		solrQuery.addHighlightField("title");
		//设置高亮的前缀
		solrQuery.setHighlightSimplePre("<font color='red'>");
		//设置高亮的后缀
		solrQuery.setHighlightSimplePost("</font>");
		//查询 得到查询响应的对象
		QueryResponse query = solrServer.query(solrQuery);
		//状态码的判断  0 代表成功
		if(query.getStatus()==0){
			System.out.println("总记录数："+query.getResults().getNumFound());
		//获取高亮查询结果
		Map<String, Map<String, List<String>>> maps = query.getHighlighting();
		//把查询的结果转为list集合
		List<Item> items = query.getBeans(Item.class);
		//迭代查询的数据集合
		for (Item item : items) {
			//获取改行的高亮的内容集合
			Map<String, List<String>> map = maps.get(String.valueOf(item.getId()));
			System.out.println(item.getId() + "\t"
		             + item.getTitle() + "\t" + item.getPrice());
					/** 输出高亮内容 */
					System.out.println("title高亮内容：" + map.get("title").get(0));

		}
		
		
		}
		
		
	
	}
}
