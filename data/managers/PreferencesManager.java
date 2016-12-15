/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po41.Maltseva.wdad.data.managers;

/**
 *
 * @author Мрр
 */
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PreferencesManager {
    private static PreferencesManager instance;
    private String path = "D:/starting-monkey-to-human-path/src/po41/Maltseva/wdad/resources/configuration/appconfig.xml";
    Document document;

    PreferencesManager() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    public static PreferencesManager getInstance() throws IOException, SAXException, ParserConfigurationException {
        if (instance == null)
            instance = new PreferencesManager();
        return instance;
    }
    @Deprecated
    public boolean isCreateRegistry() {
        String tegCreateRegistry = "createregistry";
        if (document.getElementsByTagName(tegCreateRegistry).item(0).getTextContent().equals("yes"))
            return true;
        return false;
    }
    
      public void setProperty(String key, String value) throws IOException, TransformerException{
        String[] tags = key.split("\\.");
        NodeList node = document.getElementsByTagName(tags[tags.length-1]);
        node.item(0).setTextContent(value);
        writeDoc();
    }
    
    public String getProperty(String key) throws IOException{
        String[] tags = key.split("\\.");
        NodeList node = document.getElementsByTagName(tags[tags.length-1]);
        return node.item(0).getTextContent();
    }
    
    public void setProperties(Properties prop)throws IOException, TransformerException{
        for (String key: prop.stringPropertyNames()){
            setProperty(key, prop.getProperty(key));
        }
    }
    
    public Properties getProperties() throws IOException, XPathExpressionException{
        Properties properties = new Properties();
        String key, value;
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//*[not(*)]";
        
        NodeList node = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < node.getLength(); i++){
            key = getNodePath(node.item(i));
            value = getProperty(key);
            properties.put(key, value);
        }
        return properties;  
    }
    
    public void addBindedObject(String name, String className)throws IOException, TransformerException{
         Element bindedObjectNode = document.createElement("bindedobject");
         bindedObjectNode.setAttribute("name", name);
         bindedObjectNode.setAttribute("class", className);
         document.getElementsByTagName("server").item(0).appendChild(bindedObjectNode);
         writeDoc();
    }
    
    public void removeBindedObject(String name) throws IOException, TransformerException {
         NodeList bindedObjectList = document.getElementsByTagName("bindedobject");
         NamedNodeMap bindedObjectListAttributs;
         for (int i = 0; i < bindedObjectList.getLength(); i++) {
             bindedObjectListAttributs = bindedObjectList.item(i).getAttributes();
             if (bindedObjectListAttributs.getNamedItem("name").getNodeValue().equals(name)) {
                 bindedObjectList.item(i).getParentNode().removeChild(bindedObjectList.item(i));
             }
         }
         writeDoc();
     }
     @Deprecated
    private static String getNodePath(Node node) {
        Node parent = node.getParentNode();
        
        if (parent == null || parent.getNodeName().equals("#document")) {
            return node.getNodeName();
        }
        return getNodePath(parent) + '.' + node.getNodeName();
    }
     
    private void writeDoc() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(domSource, streamResult);
    }
     @Deprecated
    public void setCreateRegistry(boolean createRegistry) throws TransformerException {
        String textContent;
        String tegCreateRegistry = "createregistry";
        if (createRegistry) {
           textContent = "yes";
        } else textContent = "no";
        document.getElementsByTagName(tegCreateRegistry).item(0).setTextContent(textContent);
        writeDoc();
    }
 @Deprecated
    public String getRegistryAddress() {
        String tegRegistryAddress = "registryaddress";
        return document.getElementsByTagName(tegRegistryAddress).item(0).getTextContent();
    }
 @Deprecated
    public void setRegistryAddress(String newTextContent) throws TransformerException {
        String tegRegistryAddress = "registryaddress";
        document.getElementsByTagName(tegRegistryAddress).item(0).setTextContent(newTextContent);
        writeDoc();
    }
 @Deprecated
    public String getRegistryPort() {
        String tegRegistryPort = "registryport";
        return document.getElementsByTagName(tegRegistryPort).item(0).getTextContent();
    }
 @Deprecated
    public void setRegistryPort(String newRegistryPort) throws TransformerException {
        String tegRegistryPort = "registryport";
        document.getElementsByTagName(tegRegistryPort).item(0).setTextContent(newRegistryPort);
        writeDoc();
    }
 @Deprecated
    public String getPolicyPath() {
        String tegPolicyPath = "policypath";
        return document.getElementsByTagName(tegPolicyPath).item(0).getTextContent();
    }
 @Deprecated
    public void setPolicyPath(String newPolicyPath) {
        String tegPolicyPath = "policypath";
        document.getElementsByTagName(tegPolicyPath).item(0).setTextContent(newPolicyPath);
    }
 @Deprecated
    public boolean isUseCodeBaseOnly() {
        String tegUseCodebaseOnly = "usecodebaseonly";
        if (document.getElementsByTagName(tegUseCodebaseOnly).item(0).getTextContent().equals("yes"))
            return true;
        return false;
    }
     @Deprecated
    public void setUseCodeBaseOnly(boolean newUseCodeBaseOnly) throws TransformerException {
        String tegUseCodebaseOnly = "usecodebaseonly";
        String textContent;
        if (newUseCodeBaseOnly){
            textContent="yes";
        } else textContent="no";
        document.getElementsByTagName(tegUseCodebaseOnly).item(0).setTextContent(textContent);
        writeDoc();
    }
     @Deprecated
    public String getClassProvider(){
        String tegClassProvider="classprovider";
        return document.getElementsByTagName(tegClassProvider).item(0).getTextContent();
    }
     @Deprecated
    public void setClassProvider(String newClassProvider) throws TransformerException {
        String tegClassProvider="classprovider";
        document.getElementsByTagName(tegClassProvider).item(0).setTextContent(newClassProvider);
        writeDoc();
    }

}
