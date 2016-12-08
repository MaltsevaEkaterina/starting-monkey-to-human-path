/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po41.Maltseva.wdad.learn.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author Мрр
 */
public class XmlTask {
    public XmlTask () throws IOException,ParserConfigurationException, SAXException {
       generateDocument();
     
    }
    private String path = "D:/starting-monkey-to-human-path/src/po41/Maltseva/wdad/learn/xml/XMLDocument1.xml";
    private Document document;
    
    private void generateDocument() throws IOException, ParserConfigurationException, SAXException{
        File xmlFile = new File(path); 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(xmlFile);
} 
    
   private int salaryAverage(NodeList node){
       int count = 0;
       int sumSalary = 0;
       for (int i = 0; i < node.getLength(); i++ ){
           sumSalary += Integer.valueOf(node.item(i).getTextContent());
           count++;
       }
       return sumSalary/count;
   }
    public int salaryAverage(){
       NodeList salary = document.getElementsByTagName("salary");
        return salaryAverage(salary);
    }
    public int salaryAverage(String departmentName){
        NodeList departments = document.getElementsByTagName("department");
        NodeList salary = null;
        NamedNodeMap map;
        for (int i = 0; i < departments.getLength(); i++){
            map  =  departments.item(i).getAttributes();
            if (map.getNamedItem("name").getTextContent().equals(departmentName)){
                salary = ((Element)departments.item(i)).getElementsByTagName("salary");
            }
        }
        return salaryAverage(salary);
    }
    public void transferEmployee(String firstName, String secondName, String departmentName) throws TransformerException{
            NodeList departments = document.getElementsByTagName("department");
            NodeList employeers;
            Node employeer = findEmployee(firstName,secondName);
            fireEmployee(firstName, secondName);
          
            for ( int i = 0; i < departments.getLength(); i++){   
                if (departments.item(i).getAttributes().getNamedItem("name").getNodeValue().equals(departmentName)){
                    departments.item(i).appendChild(employeer);
                }
            }
            writeDoc();
    }
            
            
        private Node findEmployee (String firstName, String secondName){
         NodeList employees = document.getElementsByTagName("employee");
         NamedNodeMap map;
         for ( int i = 0; i < employees.getLength(); i++){
             map = employees.item(i).getAttributes();
             if (map.getNamedItem("firstname").getTextContent().equals(firstName) &&
                   map.getNamedItem("secondname").getTextContent().equals(secondName)  ){
                 return employees.item(i);
             }
         }
         return null;
        }
     private void writeDoc() throws TransformerException {
     TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(domSource, streamResult);
    }
    public void setJobTitle(String firstName, String secondName, String newJobTitle) throws TransformerException{
        NodeList employee = findEmployee(firstName,secondName).getChildNodes();
        for ( int i = 0; i < employee.getLength(); i++){
            if (employee.item(i).getNodeName().equals("jobtitle")){
                employee.item(i).getAttributes().getNamedItem("value").setNodeValue(newJobTitle);
            }
        }
        writeDoc();
        
    }
   
    public void setSalary(String firstName, String secondName, int newSalary) throws TransformerException{
         NodeList employee = findEmployee(firstName,secondName).getChildNodes();
        for ( int i = 0; i < employee.getLength(); i++){
            if (employee.item(i).getNodeName().equals("salary")){
                employee.item(i).setTextContent(String.valueOf(newSalary));
            }
        }
        writeDoc();
    }
    public void fireEmployee(String firstName, String secondName) throws TransformerException{
                 Node employee = findEmployee(firstName,secondName);
                 Node parent = employee.getParentNode();
        parent.removeChild(employee);
       writeDoc();
    }
}
