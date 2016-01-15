package com.zl.dom4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageDown {
	/**
	 * 获取HTML源码
	 * liguoliang
	 * 2015年9月23日下午3:02:56
	 * @param httpUrl
	 * @param encode
	 * @return String
	 */
	public static String getHtmlCode(String httpUrl, String encode) {
		StringBuffer content = new StringBuffer();
		URL url;
		BufferedReader br = null;
		try {
			url = new URL(httpUrl);
			br = new BufferedReader(new InputStreamReader(url.openStream(), encode)); // //使用openStream得到一输入流并由此构造一个BufferedReader对象 
			String input;
			while ((input = br.readLine()) != null) { // 建立读取循环，并判断是否有读取值 
				content.append(input + "\n");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content.toString();
	}

	/**
	 * 获取HTML元素信息
	 * liguoliang
	 * 2015年9月23日下午3:03:09
	 * @param httpUrl
	 * @param filePath void
	 */
	public static String getHtmlPicture(String httpUrl, String filePath) {
		FileOutputStream fos = null;
		String fileName = null;
		InputStream in = null;
		URL url = null;
		String imageUrl = null;
		try {
			File imageFile = new File(filePath);
			if (!imageFile.exists()) {
				imageFile.mkdirs();
			}

			Document doc = Jsoup.connect(httpUrl).get();
			//带src属性的元素
			Elements image = doc.select("[src]");

			for (Element src : image) {
				if (src.tagName().equals("img")) {
					imageUrl = src.attr("src");
					System.out.println("图片地址：" + imageUrl);
					File file = new File(imageUrl);
					fileName = file.getName();
					if (imageUrl.startsWith("http://") && fileName.contains(".") && !"".equals(imageUrl)) {
						url = new URL(imageUrl);
						URLConnection connection = url.openConnection();
						in = connection.getInputStream();
						//删除相同文件名并重新下载
						File[] files = imageFile.listFiles();
						for (File file2 : files) {
							if (file2.getName().equals(fileName)) {
								file2.delete();
							}
						}
						File targetFile = new File(filePath + fileName);
						fos = new FileOutputStream(targetFile);
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = in.read(buffer)) != -1) {
							fos.write(buffer, 0, len);
						}
					}
				}
			}
			fos.flush();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return imageUrl;
	}

	/**
	 * 
	 * liguoliang
	 * 2015年9月23日下午7:06:57
	 * @param filePath
	 * @param imageUrl void
	 */
	public static void downImag(String filePath, String imageUrl) {
		String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));
		URL url = null;
		InputStream in = null;
		OutputStream os = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			url = new URL(imageUrl);
			URLConnection connection = url.openConnection();
			in = connection.getInputStream();
			File targetPath = new File(filePath + fileName);
			os = new FileOutputStream(targetPath);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = (in.read(b))) != -1) {
				os.write(b, 0, length);
			}
			os.flush();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				os.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String httpUrl = "http://bizhi.sogou.com/detail/info/714894?tag=newbizhi&f=index&cate=-2";
		String encode = "gb2312";
		String filePath = "e:/phone/newfile/111/";
		String resource = getHtmlCode(httpUrl, encode);
		System.out.println(resource);
		//方式一：
		/*Document document = Jsoup.parse(resource);
		Elements element = document.getElementsByTag("img");
		for (Element element2 : element) {
			String imgUrl = element2.attr("src");
			File file = new File(imgUrl);
			if (!"".equals(imgUrl) && imgUrl.startsWith("http://") && file.getName().contains(".")) {
				System.out.println("正在批量下图片===========================");
				downImag(filePath, imgUrl);
				System.out.println("图片地址：" + imgUrl);
			}
		}*/
		//方式二：
		getHtmlPicture(httpUrl, filePath);
	}
}
