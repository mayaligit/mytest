/**
 * @Title:Item.java
   @Package cn.itcast.solr.domain
   @Description:TODO
   @author myl
   @date 2017年8月17日下午10:33:38
   @version V1.0


*/
package cn.itcast.solr.domain;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author Administrator
 *
 */
public class Item {
	@Field("id")
	private Long id;
	@Field("title")
	private String title;
	@Field("sellPoint")
	private String sellPoint;
	@Field
	private String image;
	@Field
	private Long price;
	@Field
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
