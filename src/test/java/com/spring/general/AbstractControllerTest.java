package com.spring.general;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.outbidme.general.AbstractTest;
import com.outbidme.util.StringConstants;

/**
 * Base class for all Spring controller tests in the project, through which Mock MVC library initializations are done.
 */
public abstract class AbstractControllerTest extends AbstractTest {

	protected MockMvc mockMvc;
	
	@Before
	public void initMocks(){
		mockMvc = MockMvcBuilders.standaloneSetup(getController()).build();
	}

	//Object is required for annotation based controllers
	protected abstract Object getController();
	
	public static String objectToJson(Object object){
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			return ow.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return StringConstants.EMPTY;
		}
	}
}
