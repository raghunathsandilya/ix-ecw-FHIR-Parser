package com.interopx.fhir.parser.processors;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceReactionComponent;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.AllergyReaction;
import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.model.MetaData;
import com.interopx.fhir.parser.service.FhirParserService;
import com.interopx.fhir.parser.util.FhirUtil;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * This class will process the AllergyIntolerance FHIR Object and Converts it to
 * AllergyIntolerance POJO
 * 
 * @author nbashyam
 *
 */
@Service
public class AllergyIntoleranceProcessor {

	private static final Logger logger = LoggerFactory.getLogger(AllergyIntoleranceProcessor.class);

	private static PractitionerProcessor practitionerProcessor = new PractitionerProcessor();

	/**
	 * @param allergy
	 * @return
	 */
	public com.interopx.fhir.parser.model.AllergyIntolerance retrieveAllergyIntolerance(Resource allergy, Bundle bundle,
			String fullURL) {
		AllergyIntolerance allergyIntolerance = (AllergyIntolerance) allergy;

		com.interopx.fhir.parser.model.AllergyIntolerance allergyDetails = new com.interopx.fhir.parser.model.AllergyIntolerance();

		if (allergyIntolerance.hasIdElement()) {
			allergyDetails.setAllergyId(allergyIntolerance.getIdElement().getIdPart());
		}

		if (allergyIntolerance.hasMeta()) {
			MetaData metaInfo = new MetaData() {
			};
			if (allergyIntolerance.getMeta().hasVersionId()) {
				metaInfo.setVersion(allergyIntolerance.getMeta().getVersionId());
			}
			if (allergyIntolerance.getMeta().hasLastUpdated()) {
				metaInfo.setLastModifiedTimestamp(allergyIntolerance.getMeta().getLastUpdated());
			}
			if(!fullURL.isEmpty()) {
				metaInfo.setUrl(fullURL);	
			}
			allergyDetails.setMeta(metaInfo);
		}

		if (allergyIntolerance.hasIdentifier()) {
			allergyDetails.setIdentifiers(ParserUtil.readIdentfiers(allergyIntolerance.getIdentifier()));
		}

		if (allergyIntolerance.hasClinicalStatus()) {
			allergyDetails.setClinicalStatus(FhirUtil.getAllergyClinicalStatusEnum(
					ParserUtil.getCodeFromCodeableConcept(allergyIntolerance.getClinicalStatus())));
		}

		if (allergyIntolerance.hasVerificationStatus()) {
			allergyDetails.setVerificationStatus(FhirUtil.getAllergyVerificationStatusEnum(
					ParserUtil.getCodeFromCodeableConcept(allergyIntolerance.getVerificationStatus())));
		}

		if (allergyIntolerance.hasCategory()) {
			if (!allergyIntolerance.getCategory().isEmpty()) {
				allergyDetails.setAllergyCategory(
						FhirUtil.getAllergyCategory(allergyIntolerance.getCategory().get(0).getValue().toCode()));
			}
		}

		if (allergyIntolerance.hasCriticality()) {
			allergyDetails.setAllergyCriticality(
					FhirUtil.getAllergyCriticality(allergyIntolerance.getCriticality().toCode()));
		}

		if (allergyIntolerance.hasCode()) {
			allergyDetails.setAllergyCode(ParserUtil.readCodeableConceptElements(allergyIntolerance.getCode()));
		}

		if (allergyIntolerance.hasEncounter()) {
			allergyDetails.setEncounterId(allergyIntolerance.getEncounter().getReferenceElement().getIdPart());
		}

		if (allergyIntolerance.hasOnsetDateTimeType()) {
			allergyDetails.setOnSetDateTime(allergyIntolerance.getOnsetDateTimeType().getValue());
		}

		if (allergyIntolerance.hasLastOccurrence()) {
			allergyDetails.setLastOccurenceDateTime(allergyIntolerance.getLastOccurrence());
		}
		
		if(allergyIntolerance.hasRecordedDate()) {
			allergyDetails.setRecordedDateTime(allergyIntolerance.getRecordedDate());
		}

		if (allergyIntolerance.hasReaction()) {
			List<AllergyIntoleranceReactionComponent> allergyReactions = allergyIntolerance.getReaction();
			ArrayList<AllergyReaction> allergyReactionsList = new ArrayList<AllergyReaction>();
			for (AllergyIntoleranceReactionComponent reactionComp : allergyReactions) {
				AllergyReaction allergyReaction = new AllergyReaction();
				if (reactionComp.hasSubstance()) {
					CodeElement allergySubstanceCodeElement = ParserUtil
							.readCodeableConceptElements(reactionComp.getSubstance());
					allergyReaction.setSubstance(allergySubstanceCodeElement);
				}
				if (reactionComp.hasManifestation()) {
					for (CodeableConcept concept : reactionComp.getManifestation()) {
						CodeElement allergyReactionManifestationsCodeElement = ParserUtil
								.readCodeableConceptElements(concept);
						allergyReaction.setReaction(allergyReactionManifestationsCodeElement);
					}
				}
				if (reactionComp.hasOnset()) {
					allergyReaction.setReactionDate(reactionComp.getOnset());
				}

				if (reactionComp.hasSeverity()) {
					allergyReaction
							.setSeverity(FhirUtil.getAllergyReactionSeverity(reactionComp.getSeverity().toCode()));
				}
				allergyReactionsList.add(allergyReaction);
			}
			if (!allergyReactionsList.isEmpty()) {
				allergyDetails.setReactions(allergyReactionsList);
			}
		}

		if (allergyIntolerance.hasNote()) {
			allergyDetails.setNotes(allergyIntolerance.getNote().get(0).getText());
		}

		if (allergyIntolerance.hasRecorder()) {
			BundleEntryComponent entryComp = FhirUtil.getResourceById(bundle, ResourceType.Practitioner,
					allergyIntolerance.getRecorder().getReferenceElement().getIdPart());
			if (entryComp != null) {
				if (entryComp.hasResource()) {
					allergyDetails.setRecorder(practitionerProcessor.retrievePractitioner(entryComp.getResource(),
							bundle, entryComp.getFullUrl()));
				}
			}
		}

		if (allergyIntolerance.hasAsserter()) {
			BundleEntryComponent entryComp = FhirUtil.getResourceById(bundle, ResourceType.Practitioner,
					allergyIntolerance.getAsserter().getReferenceElement().getIdPart());
			if (entryComp != null) {
				if (entryComp.hasResource()) {
					allergyDetails.setAsserter(practitionerProcessor.retrievePractitioner(entryComp.getResource(),
							bundle, entryComp.getFullUrl()));
				}
			}
		}

		return allergyDetails;
	}

}
