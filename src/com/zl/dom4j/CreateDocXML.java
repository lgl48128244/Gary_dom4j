package com.zl.dom4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 
 * @author liguoliang
 *
 */
public class CreateDocXML {
	public static void main(String[] args) throws IOException, DocumentException {

		//String filePath = "d:/user.xml";
		String filePath = "C:\\Users\\Administrator\\Desktop\\资料\\xml\\playlist.xml";
		//createXml(filePath);
		parseXML(filePath);
	}

	/**
	 * 如何创建ＸＭＬ文档
	 * 1.创建根节点
	 * liguoliang
	 * 2015年9月14日下午1:23:43
	 * @param filePath
	 * @throws IOException void
	 */
	public static void createXml(String filePath) throws IOException {
		//创建document文档对象模型
		Document document = DocumentHelper.createDocument();
		//创建根节点
		Element rootElement = document.addElement("user");
		//创建子节点１
		Element userElement = rootElement.addElement("username");
		userElement.addAttribute("attr", "用户名");
		userElement.addText("zhangsan");
		//创建兄弟节点
		Element pwdElement = rootElement.addElement("pwd");
		pwdElement.addAttribute("attr", "密码");
		pwdElement.addText("１２３４５６");
		//创建兄弟节点
		Element realnameElement = rootElement.addElement("realname");
		//创建节点子节点
		Element xingElement = realnameElement.addElement("xing");
		xingElement.addText("张");
		Element mingElement = realnameElement.addElement("ming");
		mingElement.addText("三");

		FileWriter writer = new FileWriter(filePath);
		//将ＸＭＬ写入硬盘中
		OutputFormat format = OutputFormat.createPrettyPrint();//适合阅读
		//	OutputFormat format = OutputFormat.createCompactFormat();//适合传输
		format.setEncoding("UTF-8");
		XMLWriter xmlWriter = new XMLWriter(writer, format);
		xmlWriter.write(document);
		xmlWriter.flush();
		xmlWriter.close();
	}

	public static void parseXML(String filePath) throws DocumentException, FileNotFoundException {
		File file = new File(filePath);
		FileInputStream in = new FileInputStream(file);
		//获取文档对象模型
		Document document = XMLUtil.loadXMLInputstream(in);
		//获取根节点
		Element root = (Element) document.selectSingleNode("//feed");
		System.out.println(root.asXML());
		//从根节点获取指定子节点
		List<Element> usernamElements = root.selectNodes("//author");
		for (Element element : usernamElements) {
			String username = element.attributeValue("attr");
			String usernameText = element.getText();
			System.out.println("username:" + username + "\t" + "usernameText:" + usernameText);
		}
		List<Element> pwdeElements = root.selectNodes("//pwd");
		for (Element element : pwdeElements) {
			String pwd = element.attributeValue("attr");
			String pwdText = element.getText();
			System.out.println("pwd:" + pwd + "\t" + "pwdText:" + pwdText);
		}
		Element realnameElement = (Element) root.selectSingleNode("//realname");
		List<Element> xingeElement = realnameElement.selectNodes("//xing");
		for (Element element : xingeElement) {
			System.out.println(element.getText());
		}
		List<Element> mingeElement = realnameElement.selectNodes("//ming");
		for (Element element : mingeElement) {
			System.out.println(element.getText());
		}
	}
}
