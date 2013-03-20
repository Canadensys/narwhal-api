package net.canadensys.api.narwhal.geotools;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.gml3.GMLConfiguration;
import org.geotools.xml.Configuration;
import org.geotools.xml.Encoder;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

/**
 * Helper class to write a GML xml file
 * @author canadensys
 *
 */
public class GMLWriter {
	
	private Configuration configuration = null;

	private QName ns = null;
    
    public GMLWriter(){
    	configuration = new GMLConfiguration();
    	configuration.getProperties().add(GMLConfiguration.NO_FEATURE_BOUNDS);
    	ns = new QName("http://www.opengis.net/gml","FeatureCollection","gml");
    }
    
	public String toGML(SimpleFeatureCollection featureCollection){
        try {
        	Encoder encoder = new Encoder( configuration );
        	encoder.getNamespaces().declarePrefix("xs", "http://www.w3.org/2001/XMLSchema");
        	encoder.setIndenting(true);
        	
        	ByteArrayBuffer baBuffer = new ByteArrayBuffer();
        	encoder.encode(featureCollection, ns, baBuffer);
        	baBuffer.flush();
        	String xml = baBuffer.toString();
        	baBuffer.close();
			return xml;
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
}
