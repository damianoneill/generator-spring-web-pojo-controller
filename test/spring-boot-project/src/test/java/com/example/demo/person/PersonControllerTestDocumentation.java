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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                .andExpect(status().isCreated())
                .andDo(document(
                        "{class-name}/{method-name}",
                        preprocessResponse(prettyPrint()),
                        requestFields(
                            /* TODO - Describe all mandatory and optional Person JSON request fields. eg;
                             * requestFields(fieldWithPath("mandatoryProperty").description("The Person mandatory mandatoryProperty value.")
                             *  .attributes(key("constraints").value("Must not be null. Must not be empty"))
                             * requestFields((fieldWithPath("optionalProperty").description("The Person optional optionalProperty value.").optional()
                             */
                            fieldWithPath("name").description("The Person's name")
                                .attributes(key("constraints").value("Must not be null. Must not be empty")),
                            fieldWithPath("age").description("The Person's age"),
                            fieldWithPath("email").description("The Person email address")
                        )));
        verify(personService, atLeastOnce()).create(any(Person.class));
    }

    @Test
    public void findOnePerson() throws Exception {
        final Person expected = new Person();
        when(personService.findOne(any(String.class))).thenReturn(expected);
        this.mockMvc
            .perform(get(PATH + "/{id}", "99"))
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
    public void findOnePersonNotFound() throws Exception {
        when(personService.findOne(any(String.class))).thenReturn(null);
        this.mockMvc
            .perform(get(PATH + "/{id}", "invalid"))
            .andExpect(status().isNotFound());
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
                    responseFields(
                        fieldWithPath("[]").description("An array of Persons"))
                ));
        verify(personService, atLeastOnce()).findAll();
    }

    @Test
    public void findAllPeoplePaginated() throws Exception {
        final int TOTAL = 10;
        final int PER_PAGE = TOTAL / 2;
        final List<Person> expected = new ArrayList<>(TOTAL);

        /* TODO - Configure an expected list of People with some identifiable attribute to verify
         * Needs to be greater than the requested page size in order for pagination.
         */
        for (int i = 0; i < TOTAL; i++) {
            final Person person = new Person();
            person.setName("name" + i);
            expected.add(person);
        }
        when(personService.findAll()).thenReturn(expected);

        this.mockMvc
                .perform(get(PATH)
                        /* TODO - Configure the page index to get and the total number per page. eg;
                         * Get the 2nd page, where each page contains half the total collection
                         */
                        .param("page", "1")
                        .param("size", String.valueOf(PER_PAGE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(PER_PAGE)))
                // TODO - Verify the 1st object in the response is the expected object (after pagination)
                .andExpect(jsonPath("$[0].name", is("name" + PER_PAGE)))
                .andDo(document(
                        "{class-name}/{method-name}",
                        preprocessResponse(prettyPrint()),
                        /* TODO - Describe the parameters for the paginated request.
                         * parameterWithName("page").description("The requested page index")
                         * parameterWithName("size").description("The requested number of people per page")
                         */
                        requestParameters(
                                parameterWithName("page").description("The requested page index"),
                                parameterWithName("size").description("The requested number of people per page")
                        )));

        verify(personService, atLeastOnce()).findAll();
    }

    @Test
    public void updatePerson() throws Exception {
        // TODO - Configure the Person's state and an expected updated state. eg;
        final String unchangedValue = "person name";
        final String email = "someemail@email.com";
        final Person original = new Person();
        original.setName(unchangedValue);
        original.setEmail(email);
        original.setAge(18);
        final Person updated = new Person();
        updated.setName(unchangedValue);
        updated.setEmail(email);
        updated.setAge(20);

        when(personService.update(original)).thenReturn(updated);
        this.mockMvc
                .perform(put(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prettyPrintRequest(this.objectMapper.writeValueAsString(original))))
                .andExpect(status().isOk())
                // TODO - Verify the updated object was returned eg;
                .andExpect(jsonPath("$.name", is(unchangedValue)))
                .andExpect(jsonPath("$.age", is(20)))
                .andDo(document(
                        "{class-name}/{method-name}",
                        preprocessResponse(prettyPrint()),
                        /* TODO - If necessary, describe JSON request fields. eg;
                         * requestFields(fieldWithPath("mandatoryProperty").description("The Person mandatory mandatoryProperty value.")
                         *  .attributes(key("constraints").value("Must not be null. Must not be empty"))
                         * requestFields((fieldWithPath("optionalProperty").description("The Person optional optionalProperty value.").optional()
                         */
                        requestFields(
                                fieldWithPath("name").description("The Person's name")
                                        .attributes(key("constraints").value("Must not be null. Must not be empty")),
                                fieldWithPath("age").description("The Person's age"),
                                fieldWithPath("email").description("The Person email address")
                        )));

        verify(personService, atLeastOnce()).update(original);
    }

    @Test
    public void deletePerson() throws Exception {
        this.mockMvc
                .perform(delete(PATH + "/{id}", "99"))
                .andExpect(status().isOk())
                .andDo(document(
                        "{class-name}/{method-name}",
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                             /* TODO - Describe the path parameter. eg;
                             * parameterWithName("id").description("The name of the Person to delete")),
                             */
                                parameterWithName("id").description("The name of the Person to delete")
                        )));
        verify(personService, atLeastOnce()).delete("99");
    }

    @Test
    public void deleteAllPeople() throws Exception {
        this.mockMvc
                .perform(delete(PATH))
                .andExpect(status().isOk())
                .andDo(document(
                        "{class-name}/{method-name}",
                        preprocessResponse(prettyPrint())
                ));
        verify(personService, atLeastOnce()).deleteAll();
    }

        
    @Test
    public void findAllPeopleFiltered() throws Exception {
        /* TODO - Configure and add matching and non-matching people to a List.
         * Add at least one matching and at least one non-matching, in order to
         * verify filter returns the correct collection.
         */
        final Person matching = new Person();
        matching.setEmail("matching");
        final Person nonMatching = new Person();
        nonMatching.setEmail("nonMatching");
        final List<Person> people = new ArrayList<>();
        people.add(matching);
        people.add(nonMatching);

        when(personService.findAll()).thenReturn(people);
        this.mockMvc
                .perform(get(PATH)
                        // TODO - Configure the filter parameter eg; email="matching"
                        .param("filter", "matching"))
                .andExpect(status().isOk())
                /* TODO - Configure and verify the size of the returned array.
                 * Also verify the results are as expected when filtered
                 */
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is("matching")))
                .andDo(document(
                        "{class-name}/{method-name}",
                        preprocessResponse(prettyPrint()),
                        // TODO - Describe the parameters for the filtered value.
                        requestParameters(
                                parameterWithName("filter").description("The filter value for the Person's email")
                        )));
        verify(personService, atLeastOnce()).findAll();
    }
    
    private String prettyPrintRequest(String original) throws IOException {
        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper.writeValueAsString(mapper.readTree(original));
    }

}
