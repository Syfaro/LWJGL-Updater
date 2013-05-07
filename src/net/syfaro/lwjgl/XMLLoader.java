package net.syfaro.lwjgl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XMLLoader {
    public static HashMap loadInformation() throws ParserConfigurationException, IOException, SAXException {
        HashMap versions = versions();
        HashMap defaults = defaults();

        HashMap ret = new HashMap();
        ret.put("versions", versions);
        ret.put("defaults", defaults);

        return ret;
    }

    private static HashMap versions() throws ParserConfigurationException, IOException, SAXException {
        File files = new File(Main.downloadLocation + "lwjgl_version.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(files);
        doc.getDocumentElement().normalize();

        NodeList nodes = doc.getElementsByTagName("versions");

        HashMap versions = new HashMap();

        for(int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element version = (Element) node;

                NodeList ver = version.getElementsByTagName("version");

                for(int j = 0; j < ver.getLength(); j++) {
                    Node versi = ver.item(j);
                    if(versi.getNodeType() == Node.ELEMENT_NODE) {
                        Element versionInfo = (Element) versi;

                        versions.put(versionInfo.getElementsByTagName("id").item(0).getTextContent(), versionInfo.getElementsByTagName("type").item(0).getTextContent());
                    }

                }
            }
        }

        return versions;
    }

    private static HashMap defaults() throws ParserConfigurationException, IOException, SAXException {
        File files = new File(Main.downloadLocation + "lwjgl_version.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(files);
        doc.getDocumentElement().normalize();

        NodeList nodes = doc.getElementsByTagName("defaults");

        HashMap versions = new HashMap();

        for(int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element version = (Element) node;

                NodeList ver = version.getElementsByTagName("os");

                for(int j = 0; j < ver.getLength(); j++) {
                    Node versi = ver.item(j);
                    if(versi.getNodeType() == Node.ELEMENT_NODE) {
                        Element versionInfo = (Element) versi;

                        versions.put(versionInfo.getElementsByTagName("name").item(0).getTextContent(), versionInfo.getElementsByTagName("id").item(0).getTextContent());
                    }

                }
            }
        }

        return versions;
    }

    public static HashMap loadFileList(String request) throws ParserConfigurationException, IOException, SAXException {
        File files = new File(Main.downloadLocation + "lwjgl_version.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(files);
        doc.getDocumentElement().normalize();

        NodeList nodes = doc.getElementsByTagName("files");

        HashMap filesToGet = new HashMap();

        for(int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element version = (Element) node;

                NodeList versions = version.getElementsByTagName("version");

                for(int j = 0; j < versions.getLength(); j++) {
                    Node ver = versions.item(j);
                    if(ver.getNodeType() == Node.ELEMENT_NODE) {
                        Element lwjglFile = (Element) ver;

                        if(!lwjglFile.getAttribute("id").equals(request)) continue;

                        NodeList fi = lwjglFile.getElementsByTagName("file");

                        for(int k = 0; k < fi.getLength(); k++) {
                            Node get = fi.item(k);
                            if(get.getNodeType() == Node.ELEMENT_NODE) {
                                Element nodeFiles = (Element) get;

                                if(Main.currentOperatingSystem(nodeFiles.getAttribute("os"))) {
                                    filesToGet.put(nodeFiles.getElementsByTagName("path").item(0).getTextContent(), nodeFiles.getElementsByTagName("uri").item(0).getTextContent());
                                }
                            }
                        }
                    }
                }
            }
        }

        return filesToGet;
    }
}
