package com.interopx.fhir.parser.processors;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.MetaData;
import com.interopx.fhir.parser.model.Organization;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * This class will convert the Organization FHIR Object to POJO Object
 * @author admin
 *
 */
@Service
public class OrganizationProcessor {

	public Organization retrieveOrganization(Resource organization, Bundle bundle, String fullUrl) {
		Organization organizationData = new Organization();
		org.hl7.fhir.r4.model.Organization organizationFhirObj = (org.hl7.fhir.r4.model.Organization) organization;
		
		if(organizationFhirObj.hasId()) {
			organizationData.setOrganizationId(organizationFhirObj.getIdElement().getIdPart());
		}
		
		if(organizationFhirObj.hasMeta()) {
			MetaData metaInfo = new MetaData() {};
			if(organizationFhirObj.getMeta().hasVersionId()) {
				metaInfo.setVersion(organizationFhirObj.getMeta().getVersionId());	
			}
			if(organizationFhirObj.getMeta().hasLastUpdated()) {
				metaInfo.setLastModifiedTimestamp(organizationFhirObj.getMeta().getLastUpdated());
			}
			if(!fullUrl.isEmpty()) {
				metaInfo.setUrl(fullUrl);	
			}
			organizationData.setMeta(metaInfo);
		}
		
		if (organizationFhirObj.hasIdentifier()) {
			organizationData.setIdentifiers(ParserUtil.readIdentfiers(organizationFhirObj.getIdentifier()));
		}
		
		if(organizationFhirObj.hasActive()) {
			organizationData.setActive(organizationFhirObj.getActive());
		}
		
		if(organizationFhirObj.hasAddress()) {
			organizationData.setAddress(ParserUtil.readAddress(organizationFhirObj.getAddress()));
		}
		
		if(organizationFhirObj.hasTelecom()) {
			organizationData.setTelecom(ParserUtil.readTelecomElement(organizationFhirObj.getTelecom()));
		}
		
		if(organizationFhirObj.hasName()) {
			organizationData.setName(organizationFhirObj.getName());
		}
		
		return organizationData;
	}

}
