// dom/user/MyDomTransformer.java

package user;

import org.w3c.dom.Document;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.time.YearMonth;



public class MyDomTransformer {
    
    public void transform(org.w3c.dom.Document doc){
        
        removeEmployees(30000, doc);
        giveBonus(doc); 
        
    }
    
    private void removeEmployees(int value, org.w3c.dom.Document doc){ //odstraní všechny zaměstnance jejíž plat je nižší než parametr value
        
        NodeList employees = doc.getElementsByTagName("employee");

        for (int i = employees.getLength() - 1; i >= 0; --i) {
            Element employee = (Element)employees.item(i);
            
            System.out.println("Working wit employee: " + employee.getAttribute("id_num"));

            
            Element sallary = (Element)employee.getElementsByTagName("salary").item(0);
            
            int rateVal = Integer.parseInt(sallary.getTextContent().trim());
            
            if(rateVal < value) {
                System.out.println("####Removing this employee, his/her sallary is: " + sallary.getTextContent().trim());
                Element employeeParent = (Element)employee.getParentNode(); 
                employeeParent.removeChild(employee);
            } 
            
        }
        
    }
    
    private  void giveBonus(org.w3c.dom.Document doc){ //dá bonus všem zaměstnancům 20%, kteří si v předchozím roce nevybrali dovolenou
        
        NodeList employees = doc.getElementsByTagName("employee");

        for (int i = employees.getLength() - 1; i >= 0; --i) {
            Element employee = (Element)employees.item(i);
            
            System.out.println("Working wit employee: " + employee.getAttribute("id_num"));

            NodeList holidays = employee.getElementsByTagName("holiday");
            Boolean noHolidays = true;
            for (int j = holidays.getLength()-1; j>= 0; --j ){
                
                Element from = (Element)employee.getElementsByTagName("from").item(0);
                Element to = (Element)employee.getElementsByTagName("to").item(0);

                int fromYear = Integer.parseInt(from.getTextContent().trim()) % 10000;
                int toYear = Integer.parseInt(to.getTextContent().trim()) % 10000;
                
                
                int prevYear = YearMonth.now().getYear() -1;
                
                if (fromYear == prevYear || toYear == prevYear){
                    noHolidays = false;
                    break;
                }
            }
            if(noHolidays){
                
                Element sallary = (Element)employee.getElementsByTagName("salary").item(0);
                int newSallary = (int)(Integer.parseInt(sallary.getTextContent().trim())*(float)1.21);
                sallary.setTextContent(Integer.toString((newSallary)));
            }
           
        }
    }
}
