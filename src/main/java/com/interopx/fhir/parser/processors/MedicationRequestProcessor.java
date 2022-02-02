package com.interopx.fhir.parser.processors;

import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.Dosage.DosageDoseAndRateComponent;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.MedicationRequest;
import com.interopx.fhir.parser.model.MetaData;
import com.interopx.fhir.parser.util.FhirUtil;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * This class will process the MedicationRequest FHIR Object and Converts it to
 * MedicationRequest POJO
 * 
 * @author nbashyam
 *
 */
@Service
public class MedicationRequestProcessor {

	private static PractitionerProcessor practitionerProcessor = new PractitionerProcessor();

	public MedicationRequest retrieveMedicationRequest(Resource medicationRequest, Bundle bundle, String fullURL) {
		org.hl7.fhir.r4.model.MedicationRequest medicationRequestFhirObj = (org.hl7.fhir.r4.model.MedicationRequest) medicationRequest;

		MedicationRequest medicaiontRequestObj = new MedicationRequest();

		if (medicationRequestFhirObj.hasId()) {
			medicaiontRequestObj.setMedicationRequestId(medicationRequestFhirObj.getIdElement().getIdPart());
		}

		if (medicationRequestFhirObj.hasMeta()) {
			MetaData metaInfo = new MetaData() {
			};
			if (medicationRequestFhirObj.getMeta().hasVersionId()) {
				metaInfo.setVersion(medicationRequestFhirObj.getMeta().getVersionId());
			}
			if (medicationRequestFhirObj.getMeta().hasLastUpdated()) {
				metaInfo.setLastModifiedTimestamp(medicationRequestFhirObj.getMeta().getLastUpdated());
			}
			if (!fullURL.isEmpty()) {
				metaInfo.setUrl(fullURL);
			}
			medicaiontRequestObj.setMeta(metaInfo);
		}

		if (medicationRequestFhirObj.hasStatus()) {
			// medicaiontRequestObj.setStatus(medicationRequestFhirObj.getStatus().name());
		}

		if (medicationRequestFhirObj.hasIntent()) {
			// medicaiontRequestObj.setIntent(medicationRequestFhirObj.getIntent().name());
		}

		if (medicationRequestFhirObj.hasMedicationCodeableConcept()) {
			medicaiontRequestObj.setMedication(
					ParserUtil.readCodeableConceptElements(medicationRequestFhirObj.getMedicationCodeableConcept()));
		}

		if (medicationRequestFhirObj.hasAuthoredOn()) {
			medicaiontRequestObj.setAuthoredOnDateTime(medicationRequestFhirObj.getAuthoredOn());
		}

		if (medicationRequestFhirObj.hasRequester()) {
			BundleEntryComponent entryComp = FhirUtil.getResourceById(bundle, ResourceType.Practitioner,
					medicationRequestFhirObj.getRequester().getReferenceElement().getIdPart());
			if (entryComp != null) {
				if (entryComp.hasResource()) {
					medicaiontRequestObj.setRequester(practitionerProcessor
							.retrievePractitioner(entryComp.getResource(), bundle, entryComp.getFullUrl()));
				}
			}
		}

		if (medicationRequestFhirObj.hasReported()) {
			medicaiontRequestObj.setReported(medicationRequestFhirObj.getReportedBooleanType().getValue());
		}

		if (medicationRequestFhirObj.hasEncounter()) {
			medicaiontRequestObj
					.setEncounterId(medicationRequestFhirObj.getEncounter().getReferenceElement().getIdPart());
		}

		if (medicationRequestFhirObj.hasDosageInstruction()) {
			for (Dosage dosage : medicationRequestFhirObj.getDosageInstruction()) {
				if (dosage.hasPatientInstruction()) {
					medicaiontRequestObj.setPatientInstructions(dosage.getPatientInstruction());
				}
				if (dosage.hasText()) {
					medicaiontRequestObj.setDosageSig(dosage.getText());
				}
				if (dosage.hasAsNeeded()) {
					medicaiontRequestObj.setAsNeededFlag(dosage.getAsNeededBooleanType().getValue());
				}
				if (dosage.hasRoute()) {
					medicaiontRequestObj.setRoute(ParserUtil.readCodeableConceptElements(dosage.getRoute()));
				}
				if (dosage.hasSite()) {
					medicaiontRequestObj.setBodySite(ParserUtil.readCodeableConceptElements(dosage.getSite()));
				}
				if (dosage.hasMethod()) {
					medicaiontRequestObj.setMethod(ParserUtil.readCodeableConceptElements(dosage.getMethod()));
				}
				if (dosage.hasDoseAndRate()) {
					for (DosageDoseAndRateComponent doseAndRateComp : dosage.getDoseAndRate()) {
						if (doseAndRateComp.hasDoseQuantity()) {
							medicaiontRequestObj
									.setDoseQuantity(ParserUtil.readQuantity(doseAndRateComp.getDoseQuantity()));
						}
					}
				}
			}
		}
		
		if(medicationRequestFhirObj.hasNote()) {
			if(!medicationRequestFhirObj.getNote().isEmpty()) {
				medicaiontRequestObj.setNotes(medicationRequestFhirObj.getNote().get(0).getText());	
			}
		}

		return medicaiontRequestObj;
	}
}
