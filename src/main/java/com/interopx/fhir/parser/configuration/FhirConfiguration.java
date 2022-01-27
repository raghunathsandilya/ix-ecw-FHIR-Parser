package com.interopx.fhir.parser.configuration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.LoggingInterceptor;
import ca.uhn.fhir.spring.boot.autoconfigure.FhirRestfulServerCustomizer;

@Configuration
public class FhirConfiguration implements IResourceProvider, FhirRestfulServerCustomizer{
	
	private static final Logger logger = LoggerFactory.getLogger(FhirConfiguration.class);
	
	@Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }
	
	@Bean
    public FhirContext getFhirContext() {
        return FhirContext.forR4();
    }

	@Override
	public void customize(RestfulServer server) {
		try {
			Collection<IResourceProvider> c = server.getResourceProviders();			
			List<IResourceProvider> l = c.stream().filter(p -> p != this).collect(Collectors.toList());
			// server.setServerConformanceProvider(new CapabilityStatementResourceProvider());
			server.setResourceProviders(l);
		} catch(Exception e){
			logger.error("Error in Initializing the FHIR Server");
		}finally {
			logger.info("Initialized all the Resource Providers");
		}
	}

	@Override
	public Class<? extends IBaseResource> getResourceType() {
		return null;
	}	

}
