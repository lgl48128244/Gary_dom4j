package com.zl.dom4j.jaxb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class CreateDocumentXML {

	public static void main(String[] args) throws IOException {

		String dom4j = "d:/dom4j/dom4j.xml";
		String jaxb = "d:/dom4j/jaxb.xml";
		createXML(dom4j);
		javaTOxml(jaxb);
		xmlTOjava(dom4j);
		System.out.println("============================");
		xmlTOjava(jaxb);
		more(dom4j);
		System.exit(0);
	}

	/**
	 * jdom创建XML
	 * liguoliang--
	 * 2015年9月15日下午2:19:52
	 * @param path
	 * @throws IOException void
	 */
	public static void createXML(String path) throws IOException {
		Document document = DocumentHelper.createDocument();
		Element rooteElement = document.addElement("articles");
		Element subeElement = rooteElement.addElement("article");
		Element authorElement = subeElement.addElement("author");
		authorElement.addText("作者");
		Element titleElement = subeElement.addElement("title");
		titleElement.addText("标题");
		Element dateElement = subeElement.addElement("date");
		dateElement.addText("日期");
		Element emailElement = subeElement.addElement("email");
		emailElement.addText("邮箱");
		Element e1 = rooteElement.addElement("article");
		Element e11 = e1.addElement("author");
		e11.addText("zhangsan");
		Element e12 = e1.addElement("title");
		e12.addText("dom4j");
		Element e13 = e1.addElement("date");
		e13.addText(new Date().toLocaleString());
		Element e14 = e1.addElement("email");
		e14.addText("zhangsan@qq.com");
		Element e2 = rooteElement.addElement("article");
		Element e21 = e2.addElement("author");
		e21.addText("lisi");
		Element e22 = e2.addElement("title");
		e22.addText("dom4j");
		Element e23 = e2.addElement("date");
		e23.addText(new Date().toLocaleString());
		Element e24 = e2.addElement("email");
		e24.addText("lisi@qq.com");

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		FileWriter out = new FileWriter(path);
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(document);
		writer.flush();
		out.close();
		writer.close();
	}

	/**
	 * JAVA to XML
	 * liguoliang
	 * 2015年9月15日下午2:26:13
	 * @param path void
	 */
	public static void javaTOxml(String path) {
		File xmlFile = new File(path);
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Article.class);
			Marshaller marshaller = context.createMarshaller();
			Article article = new Article();
			article.setAuthor("张三");
			article.setDate(new Date().toLocaleString());
			article.setEmail("zhangsan@qq.com");
			article.setTitle("JAXB");
			//将JAVA转换为XML
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//适合阅读
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");//设置编码
			marshaller.marshal(article, xmlFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void xmlTOjava(String path) {
		File xmlFile = new File(path);
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Article.class);
			Unmarshaller unmarshal = context.createUnmarshaller();
			Article article = (Article) unmarshal.unmarshal(xmlFile);
			System.out.println(article.getAuthor());
			System.out.println(article.getDate());
			System.out.println(article.getEmail());
			System.out.println(article.getTitle());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void more(String path) {
		File xmlFile = new File(path);
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(ArticleData.class);
			Unmarshaller unmarshal = context.createUnmarshaller();
			ArticleData data = (ArticleData) unmarshal.unmarshal(xmlFile);
			List<Article> article = data.getArticle();
			for (Article article2 : article) {
				System.out.println("======================");
				System.out.println(article2.getAuthor());
				System.out.println(article2.getDate());
				System.out.println(article2.getEmail());
				System.out.println(article2.getTitle());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
