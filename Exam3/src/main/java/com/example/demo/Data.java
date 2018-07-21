package com.example.demo;

import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

@XmlRootElement(name = "stock")
public class Data {
    private String name;
    private String open;
    private String close;
    private String current;
    private String high;
    private String low;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    private static Data convert(String text) {
        Data data = new Data();
        String[] tmps = text.split(",", 7);
        data.name = tmps[0].substring(tmps[0].indexOf('\"') + 1);
        data.open = tmps[1];
        data.close = tmps[2];
        data.current = tmps[3];
        data.high = tmps[4];
        data.low = tmps[5];
        return data;
    }

    public static String toJson(String dataText) {
        return new Gson().toJson(convert(dataText));
    }

    public static String toXML(String dataText) throws JAXBException {
        return "<xml>" + beanToXml(convert(dataText)) + "</xml>";
    }

    private static String beanToXml(Object obj) throws JAXBException {
        StringWriter writer;
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }

    private Data() {
    }
}
