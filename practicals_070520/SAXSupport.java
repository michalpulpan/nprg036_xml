
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * Main class for SAX parsing
 * @author XML Technologies
 */
public class SAXSupport {

    // Path to input file
    private static final String INPUT_FILE = "data.xml";

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            // Create parser instance
            XMLReader parser = XMLReaderFactory.createXMLReader();
            
            // Create input stream from source XML document
            InputSource source = new InputSource(INPUT_FILE);
            
            // Set our custom content handler for handling SAX events
            parser.setContentHandler(new CustomContentHandler());
            
            // Process input data
            parser.parse(source);
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}


/**
 * Out custom content handler for managing SAX events
 * @see http://www.saxproject.org/apidoc/org/xml/sax/ContentHandler.html
 */
class CustomContentHandler implements ContentHandler {
    
    // Reference to the locator
    Locator locator;
    
    /**
     * Stores reference to the locator
     * @param Locator locator Location in the source file
     */
    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
    
    /**
     * Document beginning handler
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        // ...
    }
    
    /**
     * Document end handler
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        // ...
    }
    
    /**
     * Element beginning handler
     * @param uri URI of the element namespace (empty when no namespace)
     * @param localName Local name of the element (never empty)
     * @param qName Qualified name
     * @param atts Attributes
     * @throws SAXException
     */
    @Override
    public void startElement(
        String uri, String localName, String qName, Attributes atts
    ) throws SAXException {
        // ...
    }
    
    /**
     * Element end handler
     * @param uri URI of the element namespace (empty when no namespace)
     * @param localName Local name of the element (never empty)
     * @param qName Qualified name
     * @throws SAXException
     */
    @Override
    public void endElement(
        String uri, String localName, String qName
    ) throws SAXException {
        // ...
    }
    
    /**
     * Character data handler
     *  - Text content may be delivered within multiple events, not just one
     * @param chars Array with character data
     * @param start Index of the start position in the array
     * @param length Number of characters to read from the array
     * @throws SAXException
     */
    @Override
    public void characters(
        char[] chars, int start, int length
    ) throws SAXException {
        // ...
    }
    
    /**
     * Namespace declaration beginning handler
     * @param prefix Prefix of the namespace
     * @param uri URI of the namespace
     * @throws SAXException
     */
    @Override
    public void startPrefixMapping(
        String prefix, String uri
    ) throws SAXException {
        // ...
    }
    
    /**
     * Namespace declaration end handler
     * @param prefix Prefix of the namespace
     * @throws SAXException
     */
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        // ...
    }

    /**
     * Ignorable whitespace characters handler
     * @param chars Array with character data
     * @param start Index of the start position in the array
     * @param length Number of characters to read from the array
     * @throws SAXException
     */
    @Override
    public void ignorableWhitespace(
        char[] chars, int start, int length
    ) throws SAXException {
        // ...
    }
    
    /**
     * Processing instruction handler
     * @param target Processing instruction target
     * @param data Processing instruction data
     * @throws SAXException
     */
    @Override
    public void processingInstruction(
        String target, String data
    ) throws SAXException {
        // ...
    }
    
    /**
     * Unprocessed entity handler
     * @param name Name of the skipped entity
     * @throws SAXException
     */
    @Override
    public void skippedEntity(String name) throws SAXException {
        // ...
    }
    
}
