package com.automonia.tools;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * @作者 温腾
 * @创建时间 2019年01月27日 10:58
 */
public enum XmlUtils {

    singleton;

    /**
     * 将xml数据转成对象
     *
     * @param xml         xml数据
     * @param targetClass 目标对象
     * @param <T>
     * @return 对象实例
     */
    @SuppressWarnings("unchecked")
    public <T> T toObject(String xml, Class<T> targetClass) {
        if (StringUtils.singleton.isEmpty(xml) || targetClass == null) {
            return null;
        }
        try {
            JAXBContext context = JAXBContext.newInstance(targetClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader stringReader = new StringReader(xml);
            return (T) unmarshaller.unmarshal(stringReader);
        } catch (JAXBException e) {
            LogUtils.singleton.exception(e);
        }
        return null;
    }

    /**
     * 将对象转成xml格式数据，详情格式内容由对象自身决定
     *
     * @param value 待转换的对象
     * @return xml格式数据
     */
    public String toString(Object value) {
        try {
            JAXBContext context = JAXBContext.newInstance(value.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(value, outputStream);
            return new String(outputStream.toByteArray(), "UTF-8");
        } catch (JAXBException | UnsupportedEncodingException e) {
            LogUtils.singleton.exception(e);
        }
        return null;
    }
}
