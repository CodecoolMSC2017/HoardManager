package com.codecool;

import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;

public class FileWriter {

    public void saveStorages(List<Storage> storages, String dragonName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("storages");
            doc.appendChild(rootElement);

            for (Storage stor : storages) {
                Element storage = doc.createElement("storage");
                rootElement.appendChild(storage);

                Element storageName = doc.createElement("storageName");
                Element storageSize = doc.createElement("storageSize");
                storageName.appendChild(doc.createTextNode(stor.getName()));
                storageSize.appendChild(doc.createTextNode(Integer.toString(stor.getSize())));
                storage.appendChild(storageName);
                storage.appendChild(storageSize);

                for (Hoard hoards : stor.getContents()) {
                    String hoardType = "";

                    Element hoard = doc.createElement("hoard");
                    storage.appendChild(hoard);

                    if (hoards instanceof Gems) {
                        hoardType = "gem";
                    } else if (hoards instanceof Coins) {
                        hoardType = "coin";
                    } else if (hoards instanceof CommonMagicItem) {
                        hoardType = "common";
                    } else if (hoards instanceof UniqueItem) {
                        hoardType = "unique";
                    }

                    Element type = doc.createElement("type");
                    Element name = doc.createElement("name");
                    Element value = doc.createElement("size");
                    Element size = doc.createElement("value");
                    type.appendChild(doc.createTextNode(hoardType));
                    name.appendChild(doc.createTextNode(hoards.getName()));
                    value.appendChild(doc.createTextNode(Integer.toString(hoards.getSize())));
                    size.appendChild(doc.createTextNode(Long.toString(hoards.getValue())));
                    hoard.appendChild(type);
                    hoard.appendChild(name);
                    hoard.appendChild(size);
                    hoard.appendChild(value);

                    if (hoardType.equals("gem")) {
                        Element gemtype = doc.createElement("gemtype");
                        gemtype.appendChild(doc.createTextNode(((Gems) hoards).getType()));
                        hoard.appendChild(gemtype);
                    } else if (hoardType.equals("coin")) {
                        Element material = doc.createElement("material");
                        material.appendChild(doc.createTextNode(((Coins) hoards).getMaterial()));
                        hoard.appendChild(material);
                    } else if (hoardType.equals("common")) {
                        Element description = doc.createElement("description");
                        description.appendChild(doc.createTextNode(((CommonMagicItem) hoards).getDescription()));
                        hoard.appendChild(description);
                    } else if (hoardType.equals("unique")) {
                        Element description = doc.createElement("description");
                        Element creator = doc.createElement("creator");
                        description.appendChild(doc.createTextNode(((UniqueItem) hoards).getDescription()));
                        creator.appendChild(doc.createTextNode(((UniqueItem) hoards).getCreator()));
                        hoard.appendChild(description);
                        hoard.appendChild(creator);
                    }
                }

                String fileName = dragonName + ".xml";
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(fileName));

                transformer.transform(source, result);

                System.out.println("File saved!");
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public List<Storage> loadStorages(String dragonName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String path = dragonName + ".xml";
            File xmlFile = new File(path);
            Document document = dBuilder.parse(xmlFile);
            List<Storage> storages = new ArrayList<>();

            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("storage");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node storage = nList.item(temp);

                if (storage.getNodeType() == Node.ELEMENT_NODE) {
                    Element storageElement = (Element) storage;
                    String storageName = storageElement.getElementsByTagName("storageName").item(0).getTextContent();
                    int storageSize = Integer
                            .parseInt(storageElement.getElementsByTagName("storageSize").item(0).getTextContent());

                    Storage st = new Storage(storageName, storageSize);
                    storages.add(st);
                    NodeList hoards = storageElement.getElementsByTagName("hoard");

                    for (int i = 0; i < hoards.getLength(); i++) {

                        Node hoard = hoards.item(i);

                        if (hoard.getNodeType() == Node.ELEMENT_NODE) {
                            Element hoardElement = (Element) hoard;
                            String type = hoardElement.getElementsByTagName("type").item(0).getTextContent();
                            String name = hoardElement.getElementsByTagName("name").item(0).getTextContent();
                            long value = Long
                                    .parseLong(hoardElement.getElementsByTagName("value").item(0).getTextContent());
                            int size = Integer
                                    .parseInt(hoardElement.getElementsByTagName("size").item(0).getTextContent());
                            if (type.equals("gem")) {
                                String gemtype = hoardElement.getElementsByTagName("gemtype").item(0).getTextContent();
                                Gems gem = new Gems(name, value, size, gemtype);
                                storages.get(temp).addToStorage(gem);
                            } else if (type.equals("coin")) {
                                String material = hoardElement.getElementsByTagName("material").item(0)
                                        .getTextContent();
                                Coins coin = new Coins(name, value, size, material);
                                storages.get(temp).addToStorage(coin);
                            } else if (type.equals("common") || type.equals("unique")) {
                                String description = hoardElement.getElementsByTagName("description").item(0)
                                        .getTextContent();
                                if (type.equals("common")) {
                                    CommonMagicItem cm = new CommonMagicItem(name, value, size, description);
                                    storages.get(temp).addToStorage(cm);
                                } else {
                                    String creator = hoardElement.getElementsByTagName("creator").item(0)
                                            .getTextContent();
                                    UniqueItem ui = new UniqueItem(name, value, size, description, creator);
                                    storages.get(temp).addToStorage(ui);
                                }
                            }
                        }
                    }
                }
            }
            return storages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}