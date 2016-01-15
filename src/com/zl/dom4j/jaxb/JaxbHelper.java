package com.zl.dom4j.jaxb;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JaxbHelper {

	/**
	 * pengcl
	 * @param args
	 */
	private static final Log logger = LogFactory.getLog(JaxbHelper.class);

	public static String toXml(Object object) {
		JAXBContext jaxbContext;
		StringWriter stringWriter = new StringWriter();
		try {
			jaxbContext = JAXBContext.newInstance(object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, stringWriter);
			//			jaxbMarshaller.marshal(object, System.out);  
		} catch (JAXBException ex) {
			// TODO Auto-generated catch block
			logger.info("JaxbHelper.toXml异常:Object[" + object.toString() + "];ERROR[" + ex.toString() + "]");
		}
		return stringWriter.toString();
	}

	public static Object toBean(String xml, Object toBean) {
		if (xml == null || xml.equals(""))
			return null;
		JAXBContext jaxbContext;
		Object object = null;
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			jaxbContext = JAXBContext.newInstance(toBean.getClass());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			object = jaxbUnmarshaller.unmarshal(stream);
		} catch (JAXBException ex) {
			logger.info("JaxbHelper.toBean异常:XML[" + xml + "];ERROR[" + ex.toString() + "]");
		} catch (UnsupportedEncodingException e) {
			logger.info("JaxbHelper.toBean异常:XML[" + xml + "];ERROR[" + e.toString() + "]");
		}
		return object;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
