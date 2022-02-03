package com.interopx.fhir.parser.processors;

import java.util.ArrayList;
import java.util.HashMap;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Encounter.EncounterHospitalizationComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterParticipantComponent;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.model.Encounter;
import com.interopx.fhir.parser.model.Encounter.EncounterStatus;
import com.interopx.fhir.parser.model.Encounter.ParticipantType;
import com.interopx.fhir.parser.model.Location;
import com.interopx.fhir.parser.model.MetaData;
import com.interopx.fhir.parser.model.Practitioner;
import com.interopx.fhir.parser.model.ReferenceElement;
import com.interopx.fhir.parser.util.FhirUtil;
import com.interopx.fhir.parser.util.ParserConstants;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * 
 * This class will process the Encounter FHIR Object and Converts it to
 * Encounter POJO
 * 
 * @author nbashaym
 *
 */
@Service
public class EncounterProcessor {

	private static PractitionerProcessor practitionerProcessor = new PractitionerProcessor();

	private static OrganizationProcessor organizationProcessor = new OrganizationProcessor();

	private static LocationProcessor locationProcesor = new LocationProcessor();

	public Encounter retrieveEncounter(Resource encounter, Bundle bundle, String fullURL) {
		org.hl7.fhir.r4.model.Encounter encounterFhirObj = (org.hl7.fhir.r4.model.Encounter) encounter;
		Encounter encounterObj = new Encounter();

		if (encounterFhirObj.hasId()) {
			encounterObj.setEncounterId(encounterFhirObj.getIdElement().getIdPart());
		}

		if (encounterFhirObj.hasMeta()) {
			MetaData metaInfo = new MetaData() {
			};
			if (encounterFhirObj.getMeta().hasVersionId()) {
				metaInfo.setVersion(encounterFhirObj.getMeta().getVersionId());
			}
			if (encounterFhirObj.getMeta().hasLastUpdated()) {
				metaInfo.setLastModifiedTimestamp(encounterFhirObj.getMeta().getLastUpdated());
			}
			if (fullURL!=null) {
				metaInfo.setUrl(fullURL);
			}
			encounterObj.setMeta(metaInfo);
		}

		if (encounterFhirObj.hasIdentifier()) {
			encounterObj.setIdentifiers(ParserUtil.readIdentfiers(encounterFhirObj.getIdentifier()));
		}

		if (encounterFhirObj.hasStatus()) {
			encounterObj.setStatus(FhirUtil.getEncounterStatusEnum(encounterFhirObj.getStatus().toCode()));
		}

		if (encounterFhirObj.hasClass_()) {
			encounterObj.setEncounterClass(ParserUtil.readCodingElements(encounterFhirObj.getClass_()));
		}

		if (encounterFhirObj.hasType()) {
			ArrayList<CodeElement> encounterTypes = new ArrayList<CodeElement>();
			for (CodeableConcept concept : encounterFhirObj.getType()) {
				CodeElement codeElement = ParserUtil.readCodeableConceptElements(concept);
				encounterTypes.add(codeElement);
			}
			encounterObj.setEncounterType(encounterTypes);
		}

		if (encounterFhirObj.hasPeriod()) {
			if (encounterFhirObj.getPeriod().hasStart()) {
				encounterObj.setStartDate(encounterFhirObj.getPeriod().getStart());
			}
			if (encounterFhirObj.getPeriod().hasEnd()) {
				encounterObj.setEndDate(encounterFhirObj.getPeriod().getEnd());
			}
		}

		if (encounterFhirObj.hasReasonCode()) {
			ArrayList<CodeElement> codeElements = new ArrayList<CodeElement>();
			for (CodeableConcept concept : encounterFhirObj.getReasonCode()) {
				CodeElement codeElement = ParserUtil.readCodeableConceptElements(concept);
				codeElements.add(codeElement);
			}
			encounterObj.setReasonCode(codeElements);
		}

		if (encounterFhirObj.hasHospitalization()) {
			if (encounterFhirObj.getHospitalization().hasDischargeDisposition()) {
				encounterObj.setDischargeDisposition(ParserUtil
						.readListOfCodeElements(encounterFhirObj.getHospitalization().getDischargeDisposition()));
			}
		}

		if (encounterFhirObj.hasParticipant()) {
			HashMap<ParticipantType, Practitioner> participants = new HashMap<>();
			for (EncounterParticipantComponent participantComp : encounterFhirObj.getParticipant()) {
				Practitioner practitioner = null;
				if (participantComp.hasIndividual()) {
					BundleEntryComponent entryComp = FhirUtil.getResourceById(bundle, ResourceType.Practitioner,
							participantComp.getIndividual().getReferenceElement().getIdPart());
					if (entryComp != null) {
						practitioner = practitionerProcessor.retrievePractitioner(entryComp.getResource(), bundle,
								entryComp.getFullUrl());
					}
				}
				if (participantComp.hasType()) {
					for (CodeableConcept concept : participantComp.getType()) {
						if (concept.hasCoding()) {
							for (Coding coding : concept.getCoding()) {
								if (coding.hasSystem()) {
									if (coding.getSystem().equals(ParserConstants.ENCOUNTER_PARTICIPANT_TYPE_SYSTEM)) {
										ParticipantType parType = FhirUtil
												.getParticipantTypeEnum(coding.getCode());
										if (parType != null && practitioner != null) {
											participants.put(parType, practitioner);
										}
									}
								}
							}
						}
					}

				}
			}
			encounterObj.setPractitioners(participants);
		}

		if (encounterFhirObj.hasServiceProvider()) {
			BundleEntryComponent entryComp = FhirUtil.getResourceById(bundle, ResourceType.Organization,
					encounterFhirObj.getServiceProvider().getReferenceElement().getIdPart());
			if (entryComp != null) {
				if (entryComp.hasResource()) {
					encounterObj.setServiceProviders(organizationProcessor.retrieveOrganization(entryComp.getResource(),
							bundle, entryComp.getFullUrl()));
				}
			}
		}

		if (encounterFhirObj.hasLocation()) {
			ArrayList<Location> locationsList = new ArrayList<Location>();
			for (EncounterLocationComponent locationComp : encounterFhirObj.getLocation()) {
				if (locationComp.hasLocation()) {
					BundleEntryComponent entryComp = FhirUtil.getResourceById(bundle, ResourceType.Location,
							locationComp.getLocation().getReferenceElement().getIdPart());
					if (entryComp != null) {
						if (entryComp.hasResource()) {
							locationsList.add(locationProcesor.retrieveLocation(entryComp.getResource(), bundle,
									entryComp.getFullUrl()));
						}
					}
				}
			}
			encounterObj.setLocations(locationsList);
		}
		return encounterObj;
	}
}
