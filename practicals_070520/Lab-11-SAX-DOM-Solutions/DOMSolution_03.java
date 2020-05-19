
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class DOMSolution_03 {
    
    private static final String INPUT_FILE = "data.xml";
    private static final String OUTPUT_FILE = "data.3.out.xml";
    
    public static void main(String[] args) {

        try {
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(INPUT_FILE);
            
            processTree(doc);
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer writer = tf.newTransformer();
            writer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            writer.transform(
                new DOMSource(doc),
                new StreamResult(new File(OUTPUT_FILE))
            );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private static void processTree(Document doc) {
        
        Element owner = doc.createElement("owner");
        
        owner.setAttribute("idOwner", "o2");
        
        owner.appendChild(doc.createElement("name")).
            setTextContent("Alexandr Kendik");
        
        Element address = doc.createElement("address");
        address.appendChild(doc.createElement("street")).
            setTextContent("Soukenne namesti 121/1");
        address.appendChild(doc.createElement("postCode")).
            setTextContent("46001");
        address.appendChild(doc.createElement("city")).
            setTextContent("Liberec");
        address.appendChild(doc.createElement("country")).
            setTextContent("Ceska republika");
        owner.appendChild(address);
        
        NodeList ownersList = doc.getElementsByTagName("owners");
        Element owners;
        if (ownersList.getLength() == 0) {
            owners = doc.createElement("owners");
            NodeList propertiesList = doc.getElementsByTagName("properties");
            Element properties = (Element)propertiesList.item(0);
            doc.getDocumentElement().insertBefore(owners, properties);
        } else {
            owners = (Element)ownersList.item(0);
        }
        
        owners.appendChild(owner);
        
    }
    
}
