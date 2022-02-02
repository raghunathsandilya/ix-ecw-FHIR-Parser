package com.interopx.fhir.parser.util;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.interopx.fhir.parser.model.Address;
import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.model.IdentifierElement;
import com.interopx.fhir.parser.model.NameElement;
import com.interopx.fhir.parser.model.PeriodElement;
import com.interopx.fhir.parser.model.ReferenceElement;
import com.interopx.fhir.parser.model.Telecom;

@Component
public class ParserUtil {

	private static final Logger logger = LoggerFactory.getLogger(ParserUtil.class);
	
	public static ArrayList<NameElement> readNameElement(List<HumanName> humanNames){
		ArrayList<NameElement> names = new ArrayList<NameElement>();
		if(!humanNames.isEmpty()) {
			for(HumanName humanName: humanNames) {
				NameElement nameElement = new NameElement();
				if(humanName.hasFamily()) {
					nameElement.setLastName(humanName.getFamily());
				}
				if(humanName.hasGiven()) {
					nameElement.setFirstName(humanName.getGiven().get(0).getValue());
					if(humanName.getGiven().size()>1) {
						nameElement.addMiddleName(humanName.getGiven().get(1).getValue());	
					}
				}
				if(humanName.hasSuffix()) {
					nameElement.setSuffix(humanName.getSuffix().get(0).getValue());
				}
				if(humanName.hasUse()) {
			//		nameElement.setUseContext(humanName.getUse().name());
				}
				names.add(nameElement);
			}
		}
		return names;
	}
	
	public static ArrayList<IdentifierElement> readIdentfiers(List<Identifier> identifiers){
		ArrayList<IdentifierElement> identifierElements = new ArrayList<IdentifierElement>();
		if(!identifiers.isEmpty()) {
			for(Identifier identifier: identifiers) {
				IdentifierElement identifierElement = new IdentifierElement();
				if(identifier.hasSystem()) {
					identifierElement.setSystemUri(identifier.getSystem());
				}
				if(identifier.hasValue()) {
					identifierElement.setValue(identifier.getValue());
				}
				if(identifier.hasType()) {
					identifierElement.setIdentifierType(readCodeableConceptElements(identifier.getType()));
				}
				identifierElements.add(identifierElement);
			}
		}
		return identifierElements;
	}
	
	
	
	public static ArrayList<CodeElement> readListOfCodeElements(CodeableConcept codeableConcept){
		ArrayList<CodeElement> codeElements = new ArrayList<CodeElement>();
		CodeableConcept cc = codeableConcept;
		if(cc.hasCoding()) {
			List<Coding> codings = cc.getCoding();
			for(Coding coding:codings) {
				CodeElement codeElement = new CodeElement();
				if(coding.hasSystem()) {
					codeElement.setSystem(coding.getSystem());
				}
				if(coding.hasCode()) {
					codeElement.setCode(coding.getCode());
				}
				if(coding.hasDisplay()) {
					codeElement.setDisplay(coding.getDisplay());
				}
				if(cc.hasText()) {
					codeElement.setText(cc.getText());
				}
				codeElements.add(codeElement);
			}
		}
		return codeElements;
	}
	
	public static CodeElement readCodeableConceptElements(CodeableConcept concept) {
		CodeElement codeElement = new CodeElement();
		if(concept.hasCoding()) {
			List<Coding> codings = concept.getCoding();
			for(Coding coding:codings) {
				if(coding.hasSystem()) {
					codeElement.setSystem(coding.getSystem());
				}
				if(coding.hasCode()) {
					codeElement.setCode(coding.getCode());
				}
				if(coding.hasDisplay()) {
					codeElement.setDisplay(coding.getDisplay());
				}
				if(concept.hasText()) {
					codeElement.setText(concept.getText());
				}
			}
		}
		return codeElement;
	}
	
	public static CodeElement readCodingElements(Coding coding) {
		CodeElement codeElement = new CodeElement();
		
		if(coding.hasSystem()) {
			codeElement.setSystem(coding.getSystem());
		}
		if(coding.hasCode()) {
			codeElement.setCode(coding.getCode());
		}
		if(coding.hasDisplay()) {
			codeElement.setDisplay(coding.getDisplay());
		}
		
		return codeElement;
	}
	
	public static CodeElement getCodeElementFromAdministrativeGender(AdministrativeGender administrativeGender) {
		CodeElement codeElement = new CodeElement();
		if(administrativeGender.getSystem() != null) {
			codeElement.setSystem(administrativeGender.getSystem());
		}
		if(administrativeGender.getDisplay() != null) {
			codeElement.setDisplay(administrativeGender.getDisplay());
		}
		if(administrativeGender.name() != null) {
			codeElement.setCode(administrativeGender.name());
		}
		return codeElement;
	}
	
	public static ArrayList<Address> readAddress(List<org.hl7.fhir.r4.model.Address> addresses){
		ArrayList<Address> addressesList = new ArrayList<Address>();
		for(org.hl7.fhir.r4.model.Address address: addresses) {
			Address addressObj = new Address();
			if(address.hasLine()) {
				addressObj.setAddressLine1(address.getLine().get(0).getValue());
				if(address.getLine().size()>1) {
					addressObj.setAddressLine2(address.getLine().get(1).getValue());
				}
			}
			if(address.hasCity()) {
				addressObj.setCity(address.getCity());
			}
			if(address.hasState()) {
				addressObj.setState(address.getState());
			}
			if(address.hasPostalCode()) {
				addressObj.setPostalCode(address.getPostalCode());
			}
			if(address.hasCountry()) {
				addressObj.setCountry(address.getCountry());
			}
			if(address.hasUse()) {
	//			addressObj.setPostalAddressUse(address.getUse().name());
			}
			if(address.hasPeriod()) {
	//			addressObj.setPeriodOfStay(readPeriod(address.getPeriod()));
			}
			addressesList.add(addressObj);
		}
		return addressesList;
	}
	
	public static PeriodElement readPeriod(Period period) {
		PeriodElement periodElement = new PeriodElement();
		if(period.hasStart()) {
			periodElement.setStartDate(period.getStart());
		}
		if(period.hasEnd()) {
			periodElement.setEndDate(period.getEnd());
		}
		return periodElement;
	}
	
	public static ReferenceElement readReferenceElement(Reference reference) {
		ReferenceElement referenceElement = new ReferenceElement();
		
		if(reference.hasReference()) {
			referenceElement.setResourceId(reference.getReferenceElement().getIdPart());
			referenceElement.setResourceName(reference.getReferenceElement().getResourceType());
		}
		
		return referenceElement;
	}
	
	public static String getCodeFromCodeableConcept(CodeableConcept concept) {
		String codeElement = "";
		if(concept.hasCoding()) {
			Coding coding = concept.getCodingFirstRep();
			if(coding.hasCode()) {
				codeElement=coding.getCode();
			}
		}
		return codeElement;
	}
	
	public static ArrayList<Telecom> readTelecomElement(List<ContactPoint> telecomList){
		ArrayList<Telecom> telecoms = new ArrayList<Telecom>();
		for(ContactPoint cp: telecomList) {
			Telecom telecom = new Telecom();
			if(cp.hasSystem()) {
				telecom.setType(FhirUtil.getTelecomType(cp.getSystem()));
			}
			if(cp.hasUse()) {
				telecom.setUse(FhirUtil.getTelecomUse(cp.getUse()));
			}
			if(cp.hasValue()) {
				telecom.setValue(cp.getValue());
			}
			if(cp.hasPeriod()) {
				if(cp.getPeriod().hasStart()) {
					telecom.setStartDate(cp.getPeriod().getStart());
				}
				if(cp.getPeriod().hasEnd()) {
					telecom.setEndDate(cp.getPeriod().getEnd());
				}
			}
			telecoms.add(telecom);
		}
		
		return telecoms;
	}
	
	public static com.interopx.fhir.parser.model.Quantity readQuantity(Quantity quantity) {
		com.interopx.fhir.parser.model.Quantity quantityObj = new com.interopx.fhir.parser.model.Quantity();
		if(quantity.hasValue()) {
			quantityObj.setValue(Double.toString(quantity.getValue().doubleValue()));
		}
		if(quantity.hasUnit()) {
			CodeElement codeElement = new CodeElement();
			codeElement.setCode(quantity.getUnit());
			codeElement.setSystem(quantity.getSystem());
			quantityObj.setUnits(codeElement);
		}
		return quantityObj;
	}
	
	public static void saveDataToFile(String data, String fileName) {

		try (DataOutputStream outStream = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)))) {
			logger.info("Writing data to file: {}", fileName);
			outStream.writeBytes(data);
		} catch (IOException e) {
			logger.debug("Unable to write data to file: {}", fileName, e);
		}
	}

}
