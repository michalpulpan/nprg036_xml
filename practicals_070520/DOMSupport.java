
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;


public class DOMSupport {
    
    private static final String INPUT_FILE = "data.xml";
    private static final String OUTPUT_FILE = "data.out.xml";
    
    /**
     * Main method
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            // Creating a new DOM parser factory instance
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            
            // Configuring the factory: the source file should not be validated
            dbf.setValidating(false);
            
            // Creating a new DOM parser instance
            DocumentBuilder builder = dbf.newDocumentBuilder();
            
            // Parsing the input file and creating a DOM tree of objects
            Document doc = builder.parse(INPUT_FILE);
            
            // Procesing and transforming the DOM tree
            processTree(doc);
            
            // Creating a new DOM serializer factory instance
            TransformerFactory tf = TransformerFactory.newInstance();
            
            // Creating a new DOM serializer instance
            Transformer writer = tf.newTransformer();
            
            // Configuring the output: setting file encoding
            writer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            
            // Serializing the altered DOM tree into an output XML document
            writer.transform(
                new DOMSource(doc),
                new StreamResult(new File(OUTPUT_FILE))
            );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Process document tree from the user perspective
     * @param doc Document to be parsed
     */
    private static void processTree(Document doc) {
        // ...
    }
    
}
