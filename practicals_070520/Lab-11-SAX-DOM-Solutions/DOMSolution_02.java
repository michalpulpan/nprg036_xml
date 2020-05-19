
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


public class DOMSolution_02 {
    
    private static final String INPUT_FILE = "data.xml";
    private static final String OUTPUT_FILE = "data.2.out.xml";
    
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
        
        NodeList flats = doc.getElementsByTagName("flat");
        
        for (int i = flats.getLength() - 1; i >= 0; --i) {
            
            Element flat = (Element)flats.item(i);
            Element rate = (Element)flat.getElementsByTagName("rate").item(0);
            
            int value = Integer.parseInt(rate.getTextContent().trim());
            if (value >= 20000) {
                flat.getParentNode().removeChild(flat);
            }
            
        }
        
    }
    
}
