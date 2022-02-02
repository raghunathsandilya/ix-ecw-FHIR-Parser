package com.interopx.fhir.parser.processors;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.Location;
import com.interopx.fhir.parser.model.MetaData;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * This class will convert the Location FHIR Object into Location POJO
 * @author admin
 *
 */
@Service
public class LocationProcessor {
	
	public Location retrieveLocation(Resource location, Bundle bundle, String fullUrl) {
		Location locationObj = new Location();
		org.hl7.fhir.r4.model.Location locationFhirObj = (org.hl7.fhir.r4.model.Location) location;
		
		if(locationFhirObj.hasId()) {
			locationObj.setLocationId(locationFhirObj.getIdElement().getIdPart());
		}
		
		if(locationFhirObj.hasMeta()) {
			MetaData metaInfo = new MetaData() {};
			if(locationFhirObj.getMeta().hasVersionId()) {
				metaInfo.setVersion(locationFhirObj.getMeta().getVersionId());	
			}
			if(locationFhirObj.getMeta().hasLastUpdated()) {
				metaInfo.setLastModifiedTimestamp(locationFhirObj.getMeta().getLastUpdated());
			}
			if(!fullUrl.isEmpty()) {
				metaInfo.setUrl(fullUrl);	
			}
			locationObj.setMeta(metaInfo);
		}
		
		if(locationFhirObj.hasIdentifier()) {
			locationObj.setIdentifiers(ParserUtil.readIdentfiers(locationFhirObj.getIdentifier()));
		}
		
		if(locationFhirObj.hasAddress()) {
			List<Address> addressList = new ArrayList<Address>();
			addressList.add(locationFhirObj.getAddress());
			locationObj.setAddress(ParserUtil.readAddress(addressList));
		}
		
		if(locationFhirObj.hasTelecom()) {
			locationObj.setTelecom(ParserUtil.readTelecomElement(locationFhirObj.getTelecom()));
		}
		
		if(locationFhirObj.hasName()) {
			locationObj.setName(locationFhirObj.getName());
		}
		
		return locationObj;
	}

}
