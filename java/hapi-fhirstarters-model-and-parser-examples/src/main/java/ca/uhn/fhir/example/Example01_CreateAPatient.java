package ca.uhn.fhir.example;

import java.util.ArrayList;
import java.util.List;


import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

//import org.hl7.fhir.dstu3.model.HumanName;
//import org.hl7.fhir.dstu3.model.Identifier;
//import org.hl7.fhir.dstu3.model.Patient;

import ca.uhn.fhir.context.FhirContext;

public class Example01_CreateAPatient {
   
   public static void main(String[] theArgs) {
      
      // Create a resource instance
      // Patient pat = new Patient();
      
      // // Add a "name" element
      // HumanName name = pat.addName();
      // name.setFamily("Simpson").addGiven("Homer").addGiven("J");
      
      // // Add an "identifier" element
      // Identifier identifier = pat.addIdentifier();
      // identifier.setSystem("http://acme.org/MRNs").setValue("7000135");
      
      // // Model is designed to be chained
      // pat.addIdentifier().setSystem("http://acme.org/MRNs").setValue("12345");
      
      Patient pat = new Patient();
      pat.setId("test_id");
      pat.setActive(true);
      // pat.addIdentifier()
      //   List<Identifier> ids = new ArrayList<>();
      //   Identifier id = new Identifier();
      //   id.setSystem("http/example-system");
      //   id.setValue("AA2Test");
      
      //   Identifier id2 = new Identifier();
      //   id2.setSystem("MOHTest");
      //   id2.setValue("MOHTest");
      //   ids.add(id);
      //   ids.add(id2);
      //   pat.setIdentifier(ids);
      //pat.setIde
      pat.addIdentifier().setValue("AA2Test").setSystem("AA2Test");
      pat.addIdentifier().setValue("MOHTest").setSystem("MOHTest");
      pat.addName().addGiven(" first given Name").addGiven(" second given Name").setFamily("family name");
      
      pat.setGender(AdministrativeGender.FEMALE);
      FhirContext fhirContext = FhirContext.forR4();
      System.out.println("........................");
      System.out.println(fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(pat));
      //System.out.println(fhirContext.newXmlParser().setPrettyPrint(true).encodeResourceToString(pat));
      System.out.println("........................");
   }
}
