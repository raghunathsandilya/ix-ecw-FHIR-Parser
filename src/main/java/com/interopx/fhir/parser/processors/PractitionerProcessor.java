package com.interopx.fhir.parser.processors;

import java.util.ArrayList;

import org.hl7.fhir.r4.model.PractitionerRole;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.model.Practitioner;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * This class will process the Practitioner and PractitionerRole FHIR Objects
 * and Converts it to Pracitioner POJO
 * 
 * @author nbashyam
 *
 */
@Service
public class PractitionerProcessor {

	public Practitioner retrievePractitioner(Resource practitioner, Bundle bundle) {
		org.hl7.fhir.r4.model.Practitioner practitionerFhirObj = (org.hl7.fhir.r4.model.Practitioner) practitioner;
		PractitionerRole practitionerRoleFhirObj = getPractitionerRoleByPractitioner(bundle,
				practitionerFhirObj.getIdElement().getIdPart());

		Practitioner practitionerObj = new Practitioner();

		if (practitionerFhirObj.hasId()) {
			practitionerObj.setPractitionerId(practitionerFhirObj.getIdElement().getIdPart());
		}

		if (practitionerFhirObj.hasMeta()) {
			if (practitionerFhirObj.getMeta().hasVersionId()) {
				practitionerObj.setVersion(practitionerFhirObj.getMeta().getVersionId());
			}
			if (practitionerFhirObj.getMeta().hasLastUpdated()) {
				practitionerObj.setLastModifiedTimestamp(practitionerFhirObj.getMeta().getLastUpdated());
			}
		}

		if (practitionerFhirObj.hasIdentifier()) {
			practitionerObj.setIdentifiers(ParserUtil.readIdentfiers(practitionerFhirObj.getIdentifier()));
		}

		if (practitionerFhirObj.hasName()) {
			practitionerObj.setName(ParserUtil.readNameElement(practitionerFhirObj.getName()));
		}

		if (practitionerFhirObj.hasTelecom()) {
			practitionerObj.setTelecomInfo(ParserUtil.readTelecomElement(practitionerFhirObj.getTelecom()));
		}

		if (practitionerFhirObj.hasAddress()) {
			practitionerObj.setAddress(ParserUtil.readAddress(practitionerFhirObj.getAddress()));
		}

		if (practitionerFhirObj.hasBirthDate()) {
			practitionerObj.setBirthDate(practitionerFhirObj.getBirthDate());
		}

		if (practitionerFhirObj.hasCommunication()) {
			ArrayList<CodeElement> communicationsList = new ArrayList<>();
			for (CodeableConcept concept : practitionerFhirObj.getCommunication()) {
				ArrayList<CodeElement> communications = ParserUtil.readListOfCodeElements(concept);
				communicationsList.addAll(communications);
			}
			practitionerObj.setLanguages(communicationsList);
		}

		if (practitionerRoleFhirObj != null) {
			if (practitionerRoleFhirObj.hasOrganization()) {
				practitionerObj
						.setOrganizationId(practitionerRoleFhirObj.getOrganization().getReferenceElement().getIdPart());
			}
		}

		if (practitionerRoleFhirObj != null) {
			if (practitionerRoleFhirObj.hasCode()) {
				ArrayList<CodeElement> rolesList = new ArrayList<>();
				for (CodeableConcept concept : practitionerRoleFhirObj.getCode()) {
					ArrayList<CodeElement> roles = ParserUtil.readListOfCodeElements(concept);
					rolesList.addAll(roles);
				}
				practitionerObj.setRoles(rolesList);
			}
		}
		
		if(practitionerRoleFhirObj != null) {
			if(practitionerRoleFhirObj.hasSpecialty()) {
				ArrayList<CodeElement> specialityList = new ArrayList<>();
				for (CodeableConcept concept : practitionerRoleFhirObj.getSpecialty()) {
					ArrayList<CodeElement> specialities = ParserUtil.readListOfCodeElements(concept);
					specialityList.addAll(specialities);
				}
				practitionerObj.setRoles(specialityList);
			}
		}
		
		if(practitionerRoleFhirObj != null) {
			if(practitionerRoleFhirObj.hasEndpoint()) {
				ArrayList<String> endpoints = new ArrayList<String>();
				for(Reference reference: practitionerRoleFhirObj.getEndpoint()) {
					endpoints.add(reference.getReferenceElement().getIdPart());
				}
			}
		}

		return practitionerObj;
	}

	private PractitionerRole getPractitionerRoleByPractitioner(Bundle inputBundle, String resourceId) {
		PractitionerRole practitionerRole = null;
		if (inputBundle.hasEntry()) {
			for (BundleEntryComponent entryComp : inputBundle.getEntry()) {
				if (entryComp.hasResource()) {
					if (entryComp.getResource().getResourceType().equals(ResourceType.PractitionerRole)) {
						PractitionerRole practitionerRoleObj = (PractitionerRole) entryComp.getResource();
						if (practitionerRoleObj.hasPractitioner()) {
							if (practitionerRoleObj.getPractitioner().getReferenceElement().getIdPart()
									.equals(resourceId)) {
								practitionerRole = practitionerRoleObj;
							}
						}
					}
				}
			}
		}
		return practitionerRole;
	}

}
