package ca.uhn.fhir.example;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;
import org.hl7.fhir.r4.model.Bundle;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Bundle.BundleType;

public class Example06_ClientCreate {
   public static void main(String[] theArgs) {

      Patient pat = new Patient();
      pat.setId("client-id");
      pat.addName().setFamily("Simpson-test Updated2").addGiven("Homer").addGiven("Jtest");
      pat.addIdentifier().setSystem("http://fhir-training/example").setValue("fhir-training-id");
      pat.setGender(AdministrativeGender.MALE);

      //Observation 
      Observation obs = new Observation();
      obs.setId("obs-id");
      obs.setSubject(new Reference("Patient/client-id"));
      obs.setStatus(ObservationStatus.AMENDED);
      obs.setCode(new CodeableConcept().setText("theCoding"));
      // Create a context

      Bundle bundle = new Bundle();
      bundle.setType(BundleType.TRANSACTION);
      bundle.addEntry().setResource(pat).getRequest().setUrl("Patient/client-id")
      .setMethod(Bundle.HTTPVerb.PUT);

      bundle.addEntry().setResource(obs).getRequest().setUrl("Observation/obs-id")
      .setMethod(Bundle.HTTPVerb.PUT);
      

      FhirContext ctx = FhirContext.forR4();

      // Create a client
      String serverBaseUrl = "http://localhost:8081/fhir";
      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);

      // Use the client to store a new resource instance
      // MethodOutcome outcome = client
      //    .create()
      //    .resource(pat)
      //    .execute();
      Bundle response = client.transaction().withBundle(bundle).execute();
      // update 
     // MethodOutcome outcome = client.update().resource(pat).withId("client-id").execute();
      // Print the ID of the newly created resource
     
      System.out.println("........................");
      System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(response));
      //System.out.println(fhirContext.newXmlParser().setPrettyPrint(true).encodeResourceToString(pat));
      System.out.println("........................");
   }
}
