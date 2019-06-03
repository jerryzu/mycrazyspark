package org.rdb.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SimpleSQLPipeFactory {
	static private String templatePath;
	static private String targetFileName;
	static private FileOutputStream fos;
	static private Writer writer;

	static public void setExportInfo(FreeMarkerFileType ft, FreeMarkerExportWay ew) {
		setExportInfo(ft, ew, null);
	}

	static public void setExportInfo(FreeMarkerFileType ft, FreeMarkerExportWay ew, String targetFileName) {
		Date currentTime = new Date();
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);

		String targetPath = "D:\\";

		if (targetFileName == null || targetFileName == "") {
			targetFileName = targetPath + "table_" + dateString + ".xml";
		} else {
			targetFileName = targetPath + "\\" + targetFileName + ".ctl";
		}

		switch (ft) {
		case script:
			templatePath = "E:\\workspace\\MySpring\\Templates\\script\\";
			break;
		case doc:
			templatePath = "E:\\workspace\\MySpring\\Templates\\doc\\";
			break;
		default:
			templatePath = "E:\\workspace\\MySpring\\Templates\\doc\\";
		}

		switch (ew) {

		case file: {
			try {
				// fos = new FileOutputStream(targetFileName, true);
				fos = new FileOutputStream(targetFileName, false);
				writer = new OutputStreamWriter(fos, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		}
		case screen:
			writer = new OutputStreamWriter(System.out);
			break;
		default:
			writer = new OutputStreamWriter(System.out);
		}
	}

	static public void analysisTemplate(String ObjectType, String templateFileName, Map<?, ?> root) {
		try {
			Configuration config = new Configuration(Configuration.VERSION_2_3_27);
			config.setDirectoryForTemplateLoading(new File(templatePath));
			config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_27));
			config.setNumberFormat("0.######");
			Template template = config.getTemplate(templateFileName, "UTF-8");
			template.process(root, writer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	static public void doanalysis(String ObjectType, String templateFileName, Map<?, ?> root) {
		try {
			Configuration config = new Configuration(Configuration.VERSION_2_3_27);
			config.setDirectoryForTemplateLoading(new File(templatePath));
			config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_27));
			config.setNumberFormat("0.######");
			Template template = config.getTemplate(templateFileName, "UTF-8");
			template.process(root, writer);
			// writer.flush();
			// writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	static public void finish() {
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
