package com.interopx.fhir.parser.processors;

import java.util.ArrayList;

import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition.ConditionEvidenceComponent;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.model.Condition;
import com.interopx.fhir.parser.model.Condition.ConditionCategory;
import com.interopx.fhir.parser.model.MetaData;
import com.interopx.fhir.parser.model.ReferenceElement;
import com.interopx.fhir.parser.util.FhirUtil;
import com.interopx.fhir.parser.util.ParserConstants;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * This class will process the Condition FHIR Object and Converts it to
 * Condition POJO
 * 
 * @author nbashyam
 *
 */
@Service
public class ConditionProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ConditionProcessor.class);

	private static PractitionerProcessor practitionerProcessor = new PractitionerProcessor();

	public Condition retrieveCondition(Resource condition, Bundle bundle, String fullURL) {
		org.hl7.fhir.r4.model.Condition conditionFhirObj = (org.hl7.fhir.r4.model.Condition) condition;
		Condition conditionObj = new Condition();

		if (conditionFhirObj.hasId()) {
			conditionObj.setConditionId(conditionFhirObj.getIdElement().getIdPart());
		}

		if (conditionFhirObj.hasMeta()) {
			MetaData metaInfo = new MetaData() {
			};
			if (conditionFhirObj.getMeta().hasVersionId()) {
				metaInfo.setVersion(conditionFhirObj.getMeta().getVersionId());
			}
			if (conditionFhirObj.getMeta().hasLastUpdated()) {
				metaInfo.setLastModifiedTimestamp(conditionFhirObj.getMeta().getLastUpdated());
			}
			if (fullURL!=null) {
				metaInfo.setUrl(fullURL);
			}
			conditionObj.setMeta(metaInfo);
		}

		if (conditionFhirObj.hasIdentifier()) {
			conditionObj.setIdentifiers(ParserUtil.readIdentfiers(conditionFhirObj.getIdentifier()));
		}

		if (conditionFhirObj.hasClinicalStatus()) {
			conditionObj.setStatus(FhirUtil.getConditionClinicalStatusEnum(
					ParserUtil.getCodeFromCodeableConcept(conditionFhirObj.getClinicalStatus())));
		}

		if (conditionFhirObj.hasVerificationStatus()) {
			conditionObj.setVerificationStatus(FhirUtil.getConditionVerificationStatusEnum(
					ParserUtil.getCodeFromCodeableConcept(conditionFhirObj.getVerificationStatus())));
		}

		if (conditionFhirObj.hasCategory()) {
			ArrayList<ConditionCategory> conditionCategories = new ArrayList<ConditionCategory>();
			for (CodeableConcept concept : conditionFhirObj.getCategory()) {
				if(concept.hasCoding()) {
					for(Coding coding: concept.getCoding()) {
						if(coding.hasSystem()) {
							if(coding.getSystem().equals(ParserConstants.CONDITION_CATEGORY_SYSTEM)) {
								conditionCategories
								.add(FhirUtil.getConditionCategoryEnum(coding.getCode()));
							}
						}
					}
				}
			}
			conditionObj.setConditionCategory(conditionCategories);
		}

		if (conditionFhirObj.hasSeverity()) {
			conditionObj.setSeverity(FhirUtil
					.getConditionSeverityEnum(ParserUtil.getCodeFromCodeableConcept(conditionFhirObj.getSeverity())));
		}

		if (conditionFhirObj.hasCode()) {
			conditionObj.setConditionCode(ParserUtil.readCodeableConceptElements(conditionFhirObj.getCode()));
		}

		if (conditionFhirObj.hasBodySite()) {
			ArrayList<CodeElement> bodySites = new ArrayList<CodeElement>();
			for (CodeableConcept concept : conditionFhirObj.getBodySite()) {
				bodySites.add(ParserUtil.readCodeableConceptElements(concept));
			}
			conditionObj.setConditionBodySite(bodySites);
		}

		if (conditionFhirObj.hasEncounter()) {
			conditionObj.setEncounterId(conditionFhirObj.getEncounter().getReferenceElement().getIdPart());
		}

		if (conditionFhirObj.hasOnsetDateTimeType()) {
			conditionObj.setOnsetDateTime(conditionFhirObj.getOnsetDateTimeType().getValue());
		}

		if (conditionFhirObj.hasAbatementDateTimeType()) {
			conditionObj.setAbatementDateTime(conditionFhirObj.getAbatementDateTimeType().getValue());
		}

		if (conditionFhirObj.hasRecordedDate()) {
			conditionObj.setRecordedDate(conditionFhirObj.getRecordedDate());
		}

		if (conditionFhirObj.hasRecorder()) {
			BundleEntryComponent entryComp = FhirUtil.getResourceById(bundle, ResourceType.Practitioner,
					conditionFhirObj.getRecorder().getReferenceElement().getIdPart());
			if (entryComp != null) {
				if (entryComp.hasResource()) {
					conditionObj.setRecorder(practitionerProcessor.retrievePractitioner(entryComp.getResource(),
							bundle, entryComp.getFullUrl()));
				}
			}
		}

		if (conditionFhirObj.hasNote()) {
			conditionObj.setNotes(conditionFhirObj.getNote().get(0).getText());
		}

		return conditionObj;

	}

}
