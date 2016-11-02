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

public class PreferencesManager {
    private static PreferencesManager instance;
    private String path = "C:/Users/User/IdeaProjects/starting-monkey-to-human-path/src/PO43/Ivanova/wdad/resources/configuration/appconfig.xml";
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

    public boolean isCreateRegistry() {
        String tegCreateRegistry = "createregistry";
        if (document.getElementsByTagName(tegCreateRegistry).item(0).getTextContent().equals("yes"))
            return true;
        return false;
    }
    private void writeDoc() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(domSource, streamResult);
    }
    public void setCreateRegistry(boolean createRegistry) throws TransformerException {
        String textContent;
        String tegCreateRegistry = "createregistry";
        if (createRegistry) {
           textContent = "yes";
        } else textContent = "no";
        document.getElementsByTagName(tegCreateRegistry).item(0).setTextContent(textContent);
        writeDoc();
    }

    public String getRegistryAddress() {
        String tegRegistryAddress = "registryaddress";
        return document.getElementsByTagName(tegRegistryAddress).item(0).getTextContent();
    }

    public void setRegistryAddress(String newTextContent) throws TransformerException {
        String tegRegistryAddress = "registryaddress";
        document.getElementsByTagName(tegRegistryAddress).item(0).setTextContent(newTextContent);
        writeDoc();
    }

    public String getRegistryPort() {
        String tegRegistryPort = "registryport";
        return document.getElementsByTagName(tegRegistryPort).item(0).getTextContent();
    }

    public void setRegistryPort(String newRegistryPort) throws TransformerException {
        String tegRegistryPort = "registryport";
        document.getElementsByTagName(tegRegistryPort).item(0).setTextContent(newRegistryPort);
        writeDoc();
    }

    public String getPolicyPath() {
        String tegPolicyPath = "policypath";
        return document.getElementsByTagName(tegPolicyPath).item(0).getTextContent();
    }

    public void setPolicyPath(String newPolicyPath) {
        String tegPolicyPath = "policypath";
        document.getElementsByTagName(tegPolicyPath).item(0).setTextContent(newPolicyPath);
    }

    public boolean isUseCodeBaseOnly() {
        String tegUseCodebaseOnly = "usecodebaseonly";
        if (document.getElementsByTagName(tegUseCodebaseOnly).item(0).getTextContent().equals("yes"))
            return true;
        return false;
    }
    public void setUseCodeBaseOnly(boolean newUseCodeBaseOnly) throws TransformerException {
        String tegUseCodebaseOnly = "usecodebaseonly";
        String textContent;
        if (newUseCodeBaseOnly){
            textContent="yes";
        } else textContent="no";
        document.getElementsByTagName(tegUseCodebaseOnly).item(0).setTextContent(textContent);
        writeDoc();
    }
    public String getClassProvider(){
        String tegClassProvider="classprovider";
        return document.getElementsByTagName(tegClassProvider).item(0).getTextContent();
    }
    public void setClassProvider(String newClassProvider) throws TransformerException {
        String tegClassProvider="classprovider";
        document.getElementsByTagName(tegClassProvider).item(0).setTextContent(newClassProvider);
        writeDoc();
    }

}
