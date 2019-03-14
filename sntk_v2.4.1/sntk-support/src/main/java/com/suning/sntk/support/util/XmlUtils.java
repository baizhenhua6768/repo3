package com.suning.sntk.support.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML与 Bean 转换类<br>
 */
public class XmlUtils {

    private static XStream inputXstream;
    private static XStream outputXstream;

    static {
        inputXstream = new XStream(new DomDriver("UTF-8"));
        inputXstream.autodetectAnnotations(true);
        outputXstream = new XStream(new DomDriver("UTF-8"));
        outputXstream.autodetectAnnotations(true);

    }

    private XmlUtils() {

    }

    /**
     * 
     * 功能描述: object转化为xml<br>
     *
     * @param obj Java对象
     * @return xml报文
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String toXml(Object obj) {
        return inputXstream.toXML(obj);
    }

    /**
     * 
     * 功能描述:xml装换为对象 <br>
     *
     * @param xml 报文
     * @param clazz 实体类型
     * @return Java对象
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> T toObject(String xml, Class<T> clazz) {
        outputXstream.processAnnotations(clazz);
        Object object = outputXstream.fromXML(xml);
        return (T) object;
    }

}
