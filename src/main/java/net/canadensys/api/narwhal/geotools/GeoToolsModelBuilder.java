package net.canadensys.api.narwhal.geotools;

import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Point;

/**
 * Helper class that allows to create a SimpleFeatureType object for narwhal-api usage.
 * @author canadensys
 *
 */
public class GeoToolsModelBuilder {
	
	public static SimpleFeatureType createFeatureType() {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("result");
        builder.setCRS(DefaultGeographicCRS.WGS84); // <- Coordinate reference system

        // add attributes in order
        builder.add("coordinate", Point.class);
        builder.add("originalValue", String.class);
        builder.add("error", String.class);
        builder.setNamespaceURI("http://www.w3.org/2001/XMLSchema");
        
        // build the type
        return builder.buildFeatureType();
    }
}
