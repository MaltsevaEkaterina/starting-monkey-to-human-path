/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po41.Maltseva.wdad.learn.xml;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Мрр
 */
public class TestXmlTask {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        
        XmlTask salaryAverage = new XmlTask();
        System.out.println(salaryAverage.salaryAverage());
        System.out.println(salaryAverage.salaryAverage("assistent"));
        salaryAverage.setJobTitle("Jame", "Stet", "newJobTitle");
        salaryAverage.setSalary("Anton","Samon",50000);
        salaryAverage.fireEmployee("Sam","Vin");
    }
    
}
