
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


public class DOMSolution_01 {
    
    private static final String INPUT_FILE = "data.xml";
    private static final String OUTPUT_FILE = "data.1.out.xml";
    
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
        
        NodeList rateList = doc.getElementsByTagName("rate");
        
        for (int i = rateList.getLength() - 1; i >= 0; --i) {
            
            Element rate = (Element)rateList.item(i);
            Element flat = (Element)rate.getParentNode();
            
            flat.setAttribute("rate", rate.getTextContent());
            flat.removeChild(rate);
            
        }
        
    }
    
}
