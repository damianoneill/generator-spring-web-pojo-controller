package com.example.demo.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersonController.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:/testContext.xml")
public class PersonControllerTestDocumentation {

    public static final String PATH = "/people";

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @After
    public void tearDown() {
        Mockito.reset();
    }

    @Test
    public void createPerson() throws Exception {
        final Person person = new Person();
        /* TODO - Configure the Person's object state. eg;
         * person.setId("id");
         * person.setSomeProperty(9);
         */
        person.setName("person name");
        person.setAge(19);
        person.setEmail("person@email.com");
        this.mockMvc
                .perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prettyPrintRequest(this.objectMapper.writeValueAsString(person))))
<<<<<<< HEAD
                .andExpect(status().isCreated())
=======
                .andExpect(status().isOk())
>>>>>>> 2882481a9e50b26a8c281e6f93b8da424c21c6e9
                .andDo(document(
                        "{class-name}/{method-name}",
                        preprocessResponse(prettyPrint()),
                        /* TODO - Describe all mandatory and optional Person JSON request fields. eg;
                         * requestFields((fieldWithPath("mandatoryProperty").description("The Person mandatory mandatoryProperty value."))
                         * requestFields((fieldWithPath("optionalProperty").description("The Person optional optionalProperty value.").optional()
                         */
                        requestFields(
                            fieldWithPath("name").description("The Person's name"),
                            fieldWithPath("age").description("The Person's age"),
                            fieldWithPath("email").description("The Person email address")
                        )));
        verify(personService, atLeastOnce()).create(any(Person.class));
    }

    @Test
    public void findOnePersonNotFound() throws Exception {
        when(personService.findOne(any(String.class))).thenReturn(null);
        this.mockMvc
            .perform(get(PATH + "/{id}", "invalid"))
            .andExpect(status().isNotFound());
        verify(personService, atLeastOnce()).findOne(any(String.class));
    }

    @Test
    public void findOnePerson() throws Exception {
        final Person expected = new Person();
        when(personService.findOne(any(String.class))).thenReturn(expected);
        this.mockMvc
            .perform(get(PATH + "/{id}", "invalid"))
            .andExpect(status().isOk())
            .andDo(document(
                "{class-name}/{method-name}",
                preprocessResponse(prettyPrint()),
                /* TODO - Replace response fields for the Person object. eg;
                 * responseFields((fieldWithPath("someProperty").description("The Person someProperty value."))
                 * responseFields((fieldWithPath("optionalProperty").description("The Person optional optionalProperty value.").optional()
                 */
                responseFields(
                    fieldWithPath("name").description("The Person's name"),
                    fieldWithPath("age").description("The Person's age"),
                    fieldWithPath("email").description("The Person's email address")
                )));
        verify(personService, atLeastOnce()).findOne(any(String.class));
    }

    @Test
    public void findAllPeople() throws Exception {
        final Person person = new Person();
        /* TODO - Configure and add at least one Person to the expected List.
         * person.setId("id");
         */
        final List<Person> expected = new ArrayList<>();
        expected.add(person);

        when(personService.findAll()).thenReturn(expected);
        this.mockMvc
            .perform(get(PATH))
            .andExpect(status().isOk())
            .andDo(document(
                "{class-name}/{method-name}",
                preprocessResponse(prettyPrint()),
                /* TODO - If necessary, describe the findAll response fields (typically wrapped in an array) eg;
                 * responseFields((fieldWithPath("[]").description("An array of Persons"))
                 * responseFields((fieldWithPath("[].property").description("The Person property value."))
                 */
                responseFields((fieldWithPath("[]").description("An array of Persons"))
            )));
        verify(personService, atLeastOnce()).findAll();
    }

    private String prettyPrintRequest(String original) throws IOException {
        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper.writeValueAsString(mapper.readTree(original));
    }

}
