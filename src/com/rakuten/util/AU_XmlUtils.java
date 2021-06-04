package com.rakuten.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class AU_XmlUtils {

	public static Map<String, Object> Dom2Map(String xml) throws Exception {
		Document doc = null;
		doc = DocumentHelper.parseText(xml);
		return Dom2Map(doc);
	}
	
    private static Map<String, Object> Dom2Map(Document doc) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (doc == null)
            return map;
        Element root = doc.getRootElement();
        List<Object> mapList = new ArrayList<Object>();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Element e = (Element) iterator.next();
            List<Object> list = e.elements();
            if (list.size() > 0) {
            	if (map.get(e.getName()) != null) {
            		Object obj = map.get(e.getName());
                    if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                        mapList = new ArrayList<Object>();
                        mapList.add(obj);
                        mapList.add(Dom2Map(e));
                    }
                    if (obj.getClass().getName().equals("java.util.ArrayList")) {
                        mapList = (List<Object>) obj;
                        mapList.add(Dom2Map(e));
                    }
                    map.put(e.getName(), mapList);
            	} else
                map.put(e.getName(), Dom2Map(e));
            } else {
            	if (map.get(e.getName()) != null) {
                    Object obj = map.get(e.getName());
                    if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                        mapList = new ArrayList<Object>();
                        mapList.add(obj);
                        mapList.add(e.getText());
                    }
                    if (obj.getClass().getName().equals("java.util.ArrayList")) {
                        mapList = (List<Object>) obj;
                        mapList.add(e.getText());
                    }
                    map.put(e.getName(), mapList);
                } else
                map.put(e.getName(), e.getText());
            }
        }
        return map;
    }


    private static Map<String, Object> Dom2Map(Element e) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List<Object> mapList = new ArrayList<Object>();

                if (iter.elements().size() > 0) {
                    Map<String, Object> m = Dom2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList<Object>();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List<Object>) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList<Object>();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List<Object>) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), iter.getText());
                }
            }
        } else
            map.put(e.getName(), e.getText());
        return map;
    }
}
