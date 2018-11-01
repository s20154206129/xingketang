package com.chz.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
/**
 * File¹¤¾ßÀà
 * 
 * @author yyf
 * 
 */

public final class FileUtil {
	public static final String RootPath = "D:\\DevCloud2\\xing\\WebContent\\";
	private FileUtil() {
	}

	/**
	 *
	 * @param in
	 * @param fileName
	 *          
	 * @return
	 */
	public static void saveFile(InputStream in, java.io.File file) throws Exception {

		OutputStream out = new FileOutputStream(file);
		try {
			byte[] buffer = new byte[8192];
			int bytesRead = 0;
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		} finally {
			out.close();
			in.close();
		}

	}

	/**
	 * 
	 * 
	 * @param a
	 * @return
	 */
	public static Object getFileName(String fileName) {
		try {
			if (fileName.lastIndexOf("/") > 0) {
				return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
			} else {
				return fileName.substring(0, fileName.lastIndexOf("."));
			}
		} catch (Exception ex) {
			return fileName;
		}
	}

	/**
	 * 
	 * @param size
	 * @return
	 */
	public static String getSizeDescribe(long size) {
		try {
			if (size < 1024l) {
				return size + " bytes";
			} else if (size < 1048576l) {
				return (Math.round(((size * 10) / 1024)) / 10) + " KB";
			} else if (size < 1073741824l) {
				return (Math.round(((size * 10) / 1048576)) / 10) + " MB";
			} else if (size < 1099511627776l) {
				return (Math.round(((size * 10) / 1073741824)) / 10) + " GB";
			} else {
				return (Math.round(((size * 10) / 1099511627776l)) / 10) + " TB";
			}
		} catch (Exception ex) {
			return Long.toString(size);
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		try {
			return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		} catch (Exception ex) {
			return "unknow";
		}
	}

	/**
	 * 
	 */
	public static void copy(File fileFrom, File fileTo) throws Exception {

		FileInputStream in = new java.io.FileInputStream(fileFrom);
		FileOutputStream out = new FileOutputStream(fileTo);
		try {
			byte[] bt = new byte[1024];
			int count;
			while ((count = in.read(bt)) > 0) {
				out.write(bt, 0, count);
			}
		} finally {
			in.close();
			out.close();
		}

	}

	public static void delete(File file) throws Exception {
		file.delete();
	}

	/**
	 * 
	 * @param fileName
	 * @param obj
	 * @return
	 */
	public static void saveObject(File file, Object obj) throws Exception {
		FileOutputStream fo = new FileOutputStream(file);
		ZipOutputStream out = new ZipOutputStream(fo);
		ObjectOutputStream so = new ObjectOutputStream(out);
		try {
			ZipEntry entry = new ZipEntry("data");
			out.putNextEntry(entry);
			so.writeObject(obj);
		} finally {
			so.close();
			out.close();
			fo.close();
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static Object readObject(File file) throws Exception {
		Object obj = null;
		ZipFile zipFile = null;
		ObjectInputStream si = null;
		try {
			zipFile = new ZipFile(file);
			ZipEntry entry = zipFile.getEntry("data");
			si = new ObjectInputStream(zipFile.getInputStream(entry));
			obj = si.readObject();
		} finally {
			si.close();
			zipFile.close();
		}
		return obj;

	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String readText(File file) throws Exception {
		return readText(file, "UTF-8");
	}

	/**
	 * 
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String readText(File file, String charset) throws Exception {
		FileInputStream fis = null;
		DataInputStream in = null;
		InputStreamReader inr = null;
		BufferedReader reader = null;
		try {
			fis = new FileInputStream(file);
			in = new DataInputStream(fis);
			inr = new InputStreamReader(in, charset);
			reader = new BufferedReader(inr);
			StringBuffer text = new StringBuffer();
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				text.append(tempString);
				text.append("\r\n");
			}
			return text.toString();
		} finally {
			reader.close();
			inr.close();
			in.close();
			fis.close();
		}
	}

	/**
	 * 
	 * @param content
	 * @param file
	 * @return
	 */
	public static void saveText(String content, File file) throws Exception {
		saveText(content, file, "UTF-8");
	}

	/**
	 * 
	 * @param content
	 * @param file
	 * @param charset
	 * @return
	 */
	public static void saveText(String content, File file, String charset) throws Exception {
		FileOutputStream fos = null;
		Writer out = null;
		try {
			fos = new FileOutputStream(file);
			out = new OutputStreamWriter(fos, charset);
			out.write(content);
		} finally {
			out.close();
			fos.close();
		}

	}

}
