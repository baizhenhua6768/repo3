/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: XmlUtils2
 * Author:   17061157_王薛
 * Date:     2018/8/17 15:45
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

/**
 * 功能描述：XML工具类
 *
 * @author 17061157_王薛
 * @since 2018/8/17
 */
public class XmlUtils2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils2.class);

    private static XStream xStream = null;

    private static Map<String, XStream> xStreamMap = null;

    private static XPathFactory factory = null;

    /**
     *
     */
    protected XmlUtils2() {
    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param doc
     * @param xquery
     * @param ns
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static NodeList runXpath(Node doc, String xquery, NamespaceContext ns) {
        NodeList list = null;
        try {
            XPathFactory factory = getFactory();
            XPath xpath = factory.newXPath();
            if (null != ns) {
                xpath.setNamespaceContext(ns);
            }
            XPathExpression expression = xpath.compile(xquery);

            Object result = expression.evaluate(doc, XPathConstants.NODESET);
            list = (NodeList) result;
        } catch (Exception e) {
            LOGGER.warn("XMLUtils:  unable to evaluate xpath", e);
            LOGGER.error("", e);
        }
        return list;
    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param doc
     * @param xquery
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getValueByXpath(Node doc, String xquery) {
        String result = null;
        try {
            XPathFactory factory = getFactory();
            XPath xpath = factory.newXPath();
            XPathExpression expression = xpath.compile(xquery);
            result = (String) expression.evaluate(doc, XPathConstants.STRING);
        } catch (Exception e) {
            LOGGER.error("", e);
            LOGGER.warn("XMLUtils:  unable to evaluate xpath", e);
        }
        return result;
    }

    private static XPathFactory getFactory() {
        if (factory == null) {
            synchronized (XmlUtils.class) {
                if (factory == null) {
                    factory = XPathFactory.newInstance();
                }
            }
        }

        return factory;
    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param node
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String nodeAsString(Node node) {
        String nodeStr = "";
        TransformerFactory tff = TransformerFactory.newInstance();
        try {
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            tf.transform(new DOMSource(node), new StreamResult(bos));
            return bos.toString("UTF-8");
        } catch (Exception e) {
            LOGGER.warn("XMLUtils#nodeAsString: ", e);
            LOGGER.error("", e);
        }

        return nodeStr;
    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param xmlStr
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Document getW3CDom(String xmlStr)
            throws ParserConfigurationException, SAXException, IOException {
        StringReader sr = new StringReader(xmlStr);
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);

        return doc;
    }

    /**
     *
     * 功能描述: 返回XStream实例<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static XStream getXStreamInstance() {
        if (xStream == null) {
            synchronized (XmlUtils.class) {
                if (xStream == null) {
                    xStream = new XStream();
                }
            }
        }

        return xStream;
    }

    /**
     *
     * 功能描述: 根据类名返回instance实例<br>
     * 〈功能详细描述〉 在单例情况，如果解析根节点相同的报文时，XStream会报错 如<root><A></A></root>,
     * <root><B></B></root> 为避免反复new xstream而带来的loadclass开销，一个类名-一个xstream实例
     * 若无上述特殊报文解析场景，慎重此方法
     *
     * @param clazz
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static XStream getXStreamInstance(Class<?> clazz) {
        // 因为此为公用工具类，以懒汉式方式加载，避免浪费
        if (null == xStreamMap) {
            xStreamMap = new ConcurrentHashMap<String, XStream>();
        }
        String clazzName = clazz.getName();
        XStream instance = xStreamMap.get(clazzName);
        if (null == instance) {
            instance = new XStream();
            xStreamMap.put(clazzName, instance);
        }
        return instance;
    }

    public static Object xmlToBean( String xml,Class<?> clazz) {
        XStream instance = getXStreamInstance(clazz);
        instance.processAnnotations(clazz);
        return instance.fromXML(xml);
    }


    /**
     * 根据传入的bean类型和bean实例生成xml字符串
     *
     * @param clazz
     *            bean类型 必填
     * @param bean
     *            bean实例 必填
     * @return 生成xml字符串
     */
    public static String beanToXml(Class<?> clazz, Object bean) {
        XStream instance = getXStreamInstance(clazz);
        instance.processAnnotations(clazz);
        return instance.toXML(bean);
    }

}
