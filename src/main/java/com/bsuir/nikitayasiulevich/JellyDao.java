package com.bsuir.nikitayasiulevich;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JellyDao {
    private static final String XML_FILE_PATH = "D:\\Nikita Yasiulevich\\BSUIR 172301\\5 семестр\\СиТАиРИС\\Laba_6\\src\\main\\webapp\\WEB-INF\\jellies.xml";
    private static final String XSD_FILE_PATH = "D:\\Nikita Yasiulevich\\BSUIR 172301\\5 семестр\\СиТАиРИС\\Laba_6\\src\\main\\resources\\jellies.xsd";

    public void addJelly(Jelly jelly) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(XML_FILE_PATH));

            Element rootElement = doc.getDocumentElement();

            Element jellyElement = doc.createElement("jelly");
            rootElement.appendChild(jellyElement);

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(jelly.getId()));
            jellyElement.appendChild(idElement);

            Element flavorElement = doc.createElement("flavor");
            flavorElement.appendChild(doc.createTextNode(jelly.getFlavor()));
            jellyElement.appendChild(flavorElement);

            Element quantityElement = doc.createElement("quantity");
            quantityElement.appendChild(doc.createTextNode(Integer.toString(jelly.getQuantity())));
            jellyElement.appendChild(quantityElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_FILE_PATH));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Jelly> getAllJellies() {
        List<Jelly> jellies = new ArrayList<>();
        if(!validateXMLSchema(XSD_FILE_PATH, XML_FILE_PATH)){
            return null;
        }

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(XML_FILE_PATH));

            doc.getDocumentElement().normalize();

            NodeList jellyNodeList = doc.getElementsByTagName("jelly");

            for (int i = 0; i < jellyNodeList.getLength(); i++) {
                Node jellyNode = jellyNodeList.item(i);
                if (jellyNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element jellyElement = (Element) jellyNode;
                    int id = Integer.parseInt(jellyElement.getElementsByTagName("id").item(0).getTextContent());
                    String flavor = jellyElement.getElementsByTagName("flavor").item(0).getTextContent();
                    int quantity = Integer.parseInt(jellyElement.getElementsByTagName("quantity").item(0).getTextContent());

                    Jelly jelly = new Jelly(id+"", flavor, quantity);
                    jellies.add(jelly);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jellies;
    }

    public List<Jelly> retrieveJelliesFromFile(String TEST_XML_FILE_PATH) {
        List<Jelly> jellies = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(TEST_XML_FILE_PATH));

            doc.getDocumentElement().normalize();

            NodeList jellyNodeList = doc.getElementsByTagName("jelly");

            for (int i = 0; i < jellyNodeList.getLength(); i++) {
                Node jellyNode = jellyNodeList.item(i);
                if (jellyNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element jellyElement = (Element) jellyNode;
                    int id = Integer.parseInt(jellyElement.getElementsByTagName("id").item(0).getTextContent());
                    String flavor = jellyElement.getElementsByTagName("flavor").item(0).getTextContent();
                    int quantity = Integer.parseInt(jellyElement.getElementsByTagName("quantity").item(0).getTextContent());

                    Jelly jelly = new Jelly(id+"", flavor, quantity);
                    jellies.add(jelly);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jellies;
    }

    public void saveJellies(List<Jelly> jellies) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("jellies");
            doc.appendChild(rootElement);

            for (Jelly jelly : jellies) {
                Element jellyElement = doc.createElement("jelly");
                rootElement.appendChild(jellyElement);

                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(jelly.getId()));
                jellyElement.appendChild(idElement);

                Element flavorElement = doc.createElement("flavor");
                flavorElement.appendChild(doc.createTextNode(jelly.getFlavor()));
                jellyElement.appendChild(flavorElement);

                Element quantityElement = doc.createElement("quantity");
                quantityElement.appendChild(doc.createTextNode(Integer.toString(jelly.getQuantity())));
                jellyElement.appendChild(quantityElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_FILE_PATH));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveJelliesToFile(List<Jelly> jellies, String TEST_XML_FILE_PATH) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("jellies");
            doc.appendChild(rootElement);

            for (Jelly jelly : jellies) {
                Element jellyElement = doc.createElement("jelly");
                rootElement.appendChild(jellyElement);

                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(jelly.getId()));
                jellyElement.appendChild(idElement);

                Element flavorElement = doc.createElement("flavor");
                flavorElement.appendChild(doc.createTextNode(jelly.getFlavor()));
                jellyElement.appendChild(flavorElement);

                Element quantityElement = doc.createElement("quantity");
                quantityElement.appendChild(doc.createTextNode(Integer.toString(jelly.getQuantity())));
                jellyElement.appendChild(quantityElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(TEST_XML_FILE_PATH));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Jelly getJellyById(String id) {
        List<Jelly> jellies = getAllJellies();

        for (Jelly jelly : jellies) {
            if (jelly.getId().equals(id)) {
                return jelly;
            }
        }

        return null;
    }

    public void updateJelly(Jelly jelly) {
        List<Jelly> jellies = getAllJellies();

        for (int i = 0; i < jellies.size(); i++) {
            if (jellies.get(i).getId().equals(jelly.getId())) {
                jellies.set(i, jelly);
                break;
            }
        }

        saveJellies(jellies);
    }

    public void deleteJelly(String id) {
        List<Jelly> jellies = getAllJellies();

        for (int i = 0; i < jellies.size(); i++) {
            if (jellies.get(i).getId().equals(id)) {
                jellies.remove(i);
                break;
            }
        }

        saveJellies(jellies);
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath){

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        } catch (org.xml.sax.SAXException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
