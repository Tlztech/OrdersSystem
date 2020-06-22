package com.rakuten.r1302.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class TEST {

	public static void main(String[] args)
			throws FailingHttpStatusCodeException, MalformedURLException, IOException, DatatypeConfigurationException {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	}

}
