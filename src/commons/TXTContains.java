package commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class TXTContains {
	public static void main(String[] args) throws IOException {
		//字节输入流对象s1
		InputStream s1 = new FileInputStream(new File("e:/3.txt"));
		//字节输入流对象s2
		InputStream s2 = new FileInputStream(new File("f:/4.txt"));
		/**
		 * java.io 
		        类 SequenceInputStream
		    SequenceInputStream 表示其他输入流的逻辑串联。它从输入流的有序集合开始，
		    并从第一个输入流开始读取，直到到达文件末尾，接着从第二个输入流读取，依次类推，
		    直到到达包含的最后一个输入流的文件末尾为止。
		     
		    SequenceInputStream(InputStream s1, InputStream s2) 
		    通过记住这两个参数来初始化新创建的 SequenceInputStream（将按顺序读取这两个参数，先读取 s1，然后读取 s2），
		    以提供从此 SequenceInputStream 读取的字节。
		 */
		//创建一个序列流，合并两个字节流is1和is2
		SequenceInputStream se = new SequenceInputStream(s1, s2);
		//字节输出流对象os
		OutputStream os = new FileOutputStream("c:/12.txt");
		//创建一个1024个字节数组的缓存区
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = se.read(b)) != -1) {
			os.write(b, 0, len);//将缓冲区的数据输出
			os.write("\r\n".getBytes());//回车换行
		}
		System.out.println("合并成功");
	}
}

class ManyFileCombine {
	public static void main(String[] args) throws IOException {
		//创建字节输入流对象s1,s2,s3
		InputStream s1 = new FileInputStream(new File("c:/12.txt"));
		InputStream s2 = new FileInputStream(new File("c:/12.txt"));
		InputStream s3 = new FileInputStream(new File("c:/12.txt"));

		/**
		 * SequenceInputStream(Enumeration<? extends InputStream> e) 
		    通过记住参数来初始化新创建的 SequenceInputStream，
		    该参数必须是生成运行时类型为 InputStream 对象的 Enumeration 型参数。
		 */
		//创建一个Vector类对象v
		Vector<InputStream> v = new Vector<InputStream>();
		/**
		 * void addElement(E obj) 将指定的组件添加到此向量的末尾，将其大小增加 1。 
		 */
		//将3个字节流对象添加到Vector容器中
		v.addElement(s1);
		v.addElement(s2);
		v.addElement(s3);
		//获取Vector对象中的元素
		Enumeration<InputStream> e = v.elements();
		//将Enumeration对象中的流合并（创建一个序列流，用于合并多个字节流文件s1,s2,s3）
		SequenceInputStream se = new SequenceInputStream(e);
		OutputStream os = new FileOutputStream("c:/123.txt");
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = se.read(b)) != -1) {
			os.write(b, 0, len);
			os.write("\r\n".getBytes());
		}
		System.out.println("合并成功");

	}
}
