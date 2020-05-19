// sax/user/MySaxHandler.java

package user;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.YearMonth;


public class MySaxHandler extends DefaultHandler {
    
    Locator locator;
    
    private Integer productCounter = 0;
    private Boolean inProducts = false;
    private Boolean inProductsQty = false;

    private Integer fullTimeCounter = 0;
    private Integer partTimeCounter = 0;
    private Boolean inEmployees = false;
    
    private Boolean inSalary = false;
    private Boolean salarySet = false;
    private Integer employeeSalary = 0;
    private Boolean inHolidays = false;
    private Boolean inHolidaysFrom = false;
    private Boolean inHolidaysTo = false;
    private Boolean hadHolidaysPrevYear = false;
    private Integer cheapEmployees = 0;
    
    private final Integer TAB_SIZE_ = 2;    
    OutputStreamWriter outputStreamWriter_;
    
    
    
    public MySaxHandler(OutputStreamWriter outputStreamWriter_) {
        this.outputStreamWriter_ = outputStreamWriter_;
    }
    
    public MySaxHandler() {
        //CONSTRUCTOR
    }
    
    
    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
    
    
    @Override
    public void startDocument() throws SAXException {
        //
    }
    
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Total number of products: " + productCounter);
        System.out.println("Total number of full-time employees: " + fullTimeCounter + "; Total number of part-time employees: " + partTimeCounter);
        System.out.println("Total number of employees with salary below 30k who did not have holidays last year: " + cheapEmployees);

    }
    
    
    @Override
    public void startElement(String uri, String localName, String elName, Attributes attrs) throws SAXException {
        //print(String.format("<%s>", elName));
        //printAttrs(attrs);
        
        if(elName == "products"){
            inProducts = true;
        }
        
        if(elName == "qty" && inProducts){
            inProductsQty = true;
        }
        
        if(elName == "employees"){
            inEmployees = true;
        }
        
        if(elName == "employee" && inEmployees){
            attrsParser(attrs);
        }
        
        if(elName == "salary" && inEmployees){
            inSalary = true;
        }
        
        if(elName == "holidays"){
            inHolidays = true;
        }
        
        if(elName == "from" && inHolidays){
            inHolidaysFrom = true;
        }
        if(elName == "to" && inHolidays){
            inHolidaysTo = true;
        }
        

        
        
    }
    
    @Override
    public void endElement(String uri, String localName, String elName) throws SAXException {
        
        if(elName == "products"){
            inProducts = false;
        }
        if(elName == "qty"){
            inProductsQty = false;
        }
        
        if(elName == "employees"){
            inEmployees = false;
        }
        if(elName == "employee"){
            //nastav salary, to a from na null
            if(employeeSalary < 30000 && !hadHolidaysPrevYear){
                cheapEmployees++;
            }
            
            hadHolidaysPrevYear = false;
            inSalary = false;
            salarySet = false;
            employeeSalary = 0;
            inHolidays = false;
            inHolidaysFrom = false;
            inHolidaysTo = false;
            hadHolidaysPrevYear = false;
        }
        if(elName == "salary"){
            inSalary = false;
        }
        
        if(elName == "holidays"){
            inHolidays = false;
        }
        
        if(elName == "from" && inHolidays){
            inHolidaysFrom = false;
        }
        if(elName == "to" && inHolidays){
            inHolidaysTo = false;
        }
        

    }
    
    
    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        String s = new String(chars, start, length).toUpperCase().trim();
        if (s.length() > 0) {
            
            if(inProductsQty){
                productCounter += Integer.parseInt(s);
            }
            if(inSalary){
                employeeSalary = Integer.parseInt(s); 
                salarySet = true;
            }
            
            if(inHolidaysTo || inHolidaysFrom){
                if(Integer.parseInt(s) % 10000 == YearMonth.now().getYear()-1){
                    hadHolidaysPrevYear = true;
                }
            }

            
        }
    }
    
    
    @Override
    public void startPrefixMapping(
        String prefix, String uri
    ) throws SAXException {
        // ...
    }
    
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        // ...
    }
    
    @Override
    public void ignorableWhitespace(
        char[] chars, int start, int length
    ) throws SAXException {
        // ...
    }
    
    @Override
    public void processingInstruction(
        String target, String data
    ) throws SAXException {
        // ...
    }
    
    @Override
    public void skippedEntity(String name) throws SAXException {
        // ...
    }
    
    
    private void attrsParser(Attributes attrs){
        
        
        if(attrs.getLength() <= 0){
            return;
        }
        

        
        if(attrs.getValue("employment_type").equals("full_time")){
            fullTimeCounter++;
        } else if(attrs.getValue("employment_type").equals("part_time")){
            partTimeCounter++;
        }
        
        
    }
    
    
}