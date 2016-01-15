package com.zl.dom4j.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

//根元素的标签名为articles
@XmlRootElement(name = "articles")
public class ArticleData {

	//articles元素下有多个article元素,注意变量名必须与Article类名一致，否则读取不到XML信息
	List<Article> article = new ArrayList<Article>();

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}

}
