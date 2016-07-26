package com.WSZW.entity;

/**
 *二级子栏目
 * @author Administrator
 *
 */
public class GzLm_secondColumn_bean {
	 
	String id;    //传入id chanid
	String channelType;  
	String name;
	String uniqueName;   
	String directory;   //名称缩写
	String indexPage;  
	String siteId;    //位置ID 3
	String parentId; //父类Id
	String dren;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getIndexPage() {
		return indexPage;
	}
	public void setIndexPage(String indexPage) {
		this.indexPage = indexPage;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDren() {
		return dren;
	}
	public void setDren(String dren) {
		this.dren = dren;
	}
	

}
