package commons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * IO 读写操作
 * 
 * @version 1.0 2014年12月17日 下午9:52:32
 * @author asher-xu
 * @date 1.0 2014年12月17日 下午9:52:32
 */
public class TXT {

	/**
	 * 读取文件
	 * 
	 * @param readPath
	 *            文件路径
	 * @return
	 */
	public String read(String readPath) {
		String readTxt = "";
		try {
			File f = new File(readPath);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					readTxt += line + "\r\n";
				}
				read.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readTxt;
	}

	/**
	 * 写入文件
	 * 
	 * @param writePath
	 *            文件路径
	 */
	public void write(String writePath, String writeTxt) {
		try {
			File f = new File(writePath);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(writeTxt);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TXT readWrite = new TXT();

		String iptMsg = "";
		Scanner sc = new Scanner(System.in);
		System.err.println("请输入内容：");
		iptMsg = sc.next();
		System.err.println("接收到内容：" + iptMsg);
		System.out.println("----开始写文件----");
		String writePath = "e:/3.txt";
		readWrite.write(writePath, iptMsg);
		System.out.println("----结束写文件----");

		System.out.println("----读取文件开始----");
		String readPath = "e:/3.txt";
		String txt = readWrite.read(readPath);
		System.out.println("----读取文件结束----");

		System.out.println("----开始写文件----");
		String writePathString = "f:/4.txt";
		readWrite.write(writePathString, txt);
		System.out.println("----结束写文件----");
	}
}
