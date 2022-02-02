package com.interopx.fhir.parser.processors;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Patient.PatientCommunicationComponent;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.model.MetaData;
import com.interopx.fhir.parser.model.PatientDemographics;
import com.interopx.fhir.parser.util.ParserConstants;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * The class that will process a Patient FHIR Resource and populate the PatientDemographics POJO.
 * 
 * @author nbashyam
 *
 */
@Service
public class PatientDemographicsProcessor {

	public PatientDemographics retrievePatientDemographics(Resource patientResource,String fullURL) {

		Patient patient = (Patient) patientResource;
		PatientDemographics patientDemographics = new PatientDemographics();

		if (patient.hasIdElement()) {
			patientDemographics.setPatientId(patient.getIdElement().getIdPart());
		}
		
		if(patient.hasMeta()) {
			MetaData metaInfo = new MetaData() {};
			if(patient.getMeta().hasVersionId()) {
				metaInfo.setVersion(patient.getMeta().getVersionId());	
			}
			if(patient.getMeta().hasLastUpdated()) {
				metaInfo.setLastModifiedTimestamp(patient.getMeta().getLastUpdated());
			}
			if(!fullURL.isEmpty()) {
				metaInfo.setUrl(fullURL);	
			}
			patientDemographics.setMeta(metaInfo);
		}
		
		if (patient.hasName()) {
			patientDemographics.setPatientNames(ParserUtil.readNameElement(patient.getName()));
		}
		if (patient.hasIdentifier()) {
			patientDemographics.setIdentifiers(ParserUtil.readIdentfiers(patient.getIdentifier()));
		}
		if (patient.hasBirthDate()) {
			patientDemographics.setDob(patient.getBirthDate());
		}
		if (patient.hasDeceasedDateTimeType()) {
			patientDemographics.setDateOfDeath(patient.getDeceasedDateTimeType().getValue());
		}
		if (patient.hasAddress()) {
			patientDemographics.setAddresses(ParserUtil.readAddress(patient.getAddress()));
		}
		if (patient.hasCommunication()) {
			ArrayList<CodeElement> communicationsList = new ArrayList<>();
			for (PatientCommunicationComponent communicationComp : patient.getCommunication()) {
				ArrayList<CodeElement> communications = ParserUtil
						.readListOfCodeElements(communicationComp.getLanguage());
				communicationsList.addAll(communications);
			}
			patientDemographics.setLanguageCommunication(communicationsList);
		}
		if (patient.hasExtension()) {
			for (Extension extension : patient.getExtension()) {
				if (extension.hasUrl()) {
					if (extension.getUrl().contentEquals(ParserConstants.RACE_URL)) {
						List<Extension> extensionList = extension.getExtension();
						for(Extension subExtension: extensionList) {
							if(subExtension.hasUrl()) {
								if(subExtension.getUrl().contentEquals(ParserConstants.OMBCATGEORY)) {
									if (subExtension.getValue() != null && subExtension.getValue() instanceof Coding) {
										Coding coding = (Coding) subExtension.getValue();
										patientDemographics.setRaceCategory(ParserUtil.readCodingElements(coding));
									}
								}
							}
						}
					}
					if (extension.getUrl().contentEquals(ParserConstants.ETHNICITY_URL)) {
						List<Extension> extensionList = extension.getExtension();
						for(Extension subExtension: extensionList) {
							if(subExtension.hasUrl()) {
								if(subExtension.getUrl().contentEquals(ParserConstants.OMBCATGEORY)) {
									if (subExtension.getValue() != null && subExtension.getValue() instanceof Coding) {
										Coding coding = (Coding) subExtension.getValue();
										patientDemographics.setEthnicity(ParserUtil.readCodingElements(coding));
									}
								}
							}
						}
					}
				}
			}
		}
		if (patient.hasGender()) {
			patientDemographics.setGender(ParserUtil.getCodeElementFromAdministrativeGender(patient.getGender()));
		}

		if (patient.hasMaritalStatus()) {
			patientDemographics.setMaritalStatus(ParserUtil.readCodeableConceptElements(patient.getMaritalStatus()));
		}
		
		if(patient.hasTelecom()) {
			patientDemographics.setTelecoms(ParserUtil.readTelecomElement(patient.getTelecom()));
		}

		return patientDemographics;
	}
}
