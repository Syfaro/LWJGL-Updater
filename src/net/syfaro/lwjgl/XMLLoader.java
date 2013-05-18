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
    public static HashMap<String, HashMap<String, Object>> loadInformation() throws ParserConfigurationException, IOException, SAXException {
        HashMap<String, Object> versions = versions();
        HashMap<String, Object> defaults = defaults();

        HashMap<String, HashMap<String, Object>> ret = new HashMap<String, HashMap<String, Object>>();
        ret.put("versions", versions);
        ret.put("defaults", defaults);

        return ret;
    }

    private static HashMap<String, Object> versions() throws ParserConfigurationException, IOException, SAXException {
        File files = new File(Main.downloadLocation + "lwjgl_version.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(files);
        doc.getDocumentElement().normalize();

        NodeList nodes = doc.getElementsByTagName("versions");

        HashMap<String, Object> versions = new HashMap<String, Object>();

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

    private static HashMap<String, Object> defaults() throws ParserConfigurationException, IOException, SAXException {
        File files = new File(Main.downloadLocation + "lwjgl_version.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(files);
        doc.getDocumentElement().normalize();

        NodeList nodes = doc.getElementsByTagName("defaults");

        HashMap<String, Object> versions = new HashMap<String, Object>();

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

    public static HashMap<String, HashMap> loadActions(String request) throws ParserConfigurationException, IOException, SAXException {
        File files = new File(Main.downloadLocation + "lwjgl_version.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(files);
        doc.getDocumentElement().normalize();

        NodeList nodes = doc.getElementsByTagName("files");

        HashMap<String, String> filesToGet = new HashMap<String, String>();
        HashMap<String, String> filesToCopy = new HashMap<String, String>();
        HashMap<String, String> filesToMove = new HashMap<String, String>();
        HashMap<String, String> filesToDelete = new HashMap<String, String>();

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

                        NodeList fi = lwjglFile.getElementsByTagName("download");

                        for(int k = 0; k < fi.getLength(); k++) {
                            Node get = fi.item(k);
                            if(get.getNodeType() == Node.ELEMENT_NODE) {
                                Element nodeFiles = (Element) get;
                                if(Main.currentOperatingSystem(nodeFiles.getAttribute("os"))) {
                                    filesToGet.put(nodeFiles.getElementsByTagName("path").item(0).getTextContent(), nodeFiles.getElementsByTagName("uri").item(0).getTextContent());
                                }
                            }
                        }

                        fi = lwjglFile.getElementsByTagName("copy");

                        for(int k = 0; k < fi.getLength(); k++) {
                            Node get = fi.item(k);
                            if(get.getNodeType() == Node.ELEMENT_NODE) {
                                Element nodeFiles = (Element) get;

                                if(Main.currentOperatingSystem(nodeFiles.getAttribute("os"))) {
                                    filesToCopy.put(nodeFiles.getElementsByTagName("name").item(0).getTextContent(), nodeFiles.getElementsByTagName("newname").item(0).getTextContent());
                                }
                            }
                        }

                        fi = lwjglFile.getElementsByTagName("delete");

                        for(int k = 0; k < fi.getLength(); k++) {
                            Node get = fi.item(k);
                            if(get.getNodeType() == Node.ELEMENT_NODE) {
                                Element nodeFiles = (Element) get;

                                if(Main.currentOperatingSystem(nodeFiles.getAttribute("os"))) {
                                    filesToDelete.put(nodeFiles.getElementsByTagName("name").item(0).getTextContent(), nodeFiles.getElementsByTagName("toolazytofixthis").item(0).getTextContent());
                                }
                            }
                        }

                        fi = lwjglFile.getElementsByTagName("move");

                        for(int k = 0; k < fi.getLength(); k++) {
                            Node get = fi.item(k);
                            if(get.getNodeType() == Node.ELEMENT_NODE) {
                                Element nodeFiles = (Element) get;

                                if(Main.currentOperatingSystem(nodeFiles.getAttribute("os"))) {
                                    filesToMove.put(nodeFiles.getElementsByTagName("name").item(0).getTextContent(), nodeFiles.getElementsByTagName("newname").item(0).getTextContent());
                                }
                            }
                        }
                    }
                }
            }
        }

        HashMap<String, HashMap> ret = new HashMap<String, HashMap>();

        ret.put("download", filesToGet);
        ret.put("copy", filesToCopy);
        ret.put("move", filesToMove);
        ret.put("delete", filesToDelete);

        return ret;
    }
}
