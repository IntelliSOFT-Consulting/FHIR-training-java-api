package ca.uhn.fhir.example;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.context.FhirContext;

public class Example08_ClientSearch {
   public static void main(String[] theArgs) {
      FhirContext ctx = FhirContext.forR4();
      IGenericClient client = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseR4");

      // Build a search and execute it
      Bundle response = client.search()
         .forResource(Patient.class)
         .where(Patient.NAME.matches().value("Nairobi Doe"))
         .and(Patient.BIRTHDATE.before().day("2014-01-01"))
         .count(100)
         .returnBundle(Bundle.class)
         .execute();

      // How many resources did we find?
      //System.out.println("Responses: " + response.getTotal());

      // Print the ID of the first one
      //System.out.println("First response ID: " + response.getEntry().get(0).getResource().getId());
      System.out.println("........................");
		System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(response));
		//System.out.println(fhirContext.newXmlParser().setPrettyPrint(true).encodeResourceToString(pat));
		System.out.println("........................");
   }
}
