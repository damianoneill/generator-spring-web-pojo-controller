package <%= packageName %>.<%= nounLowercase %>;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rx.Observable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = <%= noun %>Controller.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:/testContext.xml")
public class <%= noun %>ControllerTestDocumentation {

    private static final String PATH = "/<%= nounLowercasePlural %>";

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private <%= noun %>Service <%= nounLowercase %>Service;

    @InjectMocks
    private <%= noun %>Controller controller;

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
    public void create<%= noun %>Synchronously() throws Exception {
        create<%= noun %>(true);
    }

    @Test
    public void create<%= noun %>Asynchronously() throws Exception {
        create<%= noun %>(false);
    }


    public void create<%= noun %>(boolean serviceIsBlocking) throws Exception {
        final <%= noun %> <%= nounLowercase %> = new <%= noun %>();
        /* TODO - Configure the <%= noun %>'s object state. eg;
         * <%= nounLowercase %>.setId("id");
         * <%= nounLowercase %>.setSomeProperty(9);
         */
        <%= nounLowercase %>.setName("person name");
        <%= nounLowercase %>.setAge(19);
        <%= nounLowercase %>.setEmail("person@email.com");

        when(<%= nounLowercase %>Service.create(<%= nounLowercase %>)).thenReturn(
        Observable.just(new HttpEntity<>(
                serviceIsBlocking ? <%= nounLowercase %>: null,
                new HttpHeaders())));


        MvcResult mvcResult = this.mockMvc
        .perform(post(PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(prettyPrintRequest(this.objectMapper.writeValueAsString(<%= nounLowercase %>))))
        .andExpect(status().isOk())
        .andExpect(request().asyncStarted()).andReturn();

        this.mockMvc
        .perform(asyncDispatch(mvcResult))
        .andExpect(serviceIsBlocking ? status().isOk(): status().isAccepted() )
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint()),
        requestFields(
                            /* TODO - Describe all mandatory and optional Person JSON request fields. eg;
                             * requestFields(fieldWithPath("mandatoryProperty").description("The Person mandatory mandatoryProperty value.")
                             *  .attributes(key("constraints").value("Must not be null. Must not be empty"))
                             * requestFields((fieldWithPath("optionalProperty").description("The Person optional optionalProperty value.").optional()
                             */
        fieldWithPath("name").description("The <%= noun %>'s name")
        .attributes(key("constraints").value("Must not be null. Must not be empty")),
        fieldWithPath("age").description("The <%= noun %>'s age"),
        fieldWithPath("email").description("The <%= noun %> email address")
        )));
        verify(<%= nounLowercase %>Service, atLeastOnce()).create(<%= nounLowercase %>);
    }

@Test
public void findOne<%= noun %>() throws Exception {
final <%= noun %> expected = new <%= noun %>();
        when(<%= nounLowercase %>Service.findOne(any(<%= type %>.class))).thenReturn(Observable.just(new HttpEntity<>(expected, new HttpHeaders())));
        MvcResult mvcResult = this.mockMvc.perform(get(PATH + "/{id}", "99")).
        andExpect(status().isOk())
        .andExpect(request().asyncStarted()).andReturn();

        this.mockMvc
        .perform(asyncDispatch(mvcResult))
        .andExpect(status().isOk())
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint()),
                /* TODO - Replace response fields for the <%= noun %> object. eg;
                 * responseFields((fieldWithPath("someProperty").description("The <%= noun %> someProperty value."))
                 * responseFields((fieldWithPath("optionalProperty").description("The <%= noun %> optional optionalProperty value.").optional()
                 */
        responseFields(
        fieldWithPath("name").description("The <%= noun %>'s name"),
        fieldWithPath("age").description("The <%= noun %>'s age"),
        fieldWithPath("email").description("The <%= noun %>'s email address")
        )));
        verify(<%= nounLowercase %>Service, atLeastOnce()).findOne(any(<%= type %>.class));
        }

@Test
public void findOne<%= noun %>NotFound() throws Exception {
        when(<%= nounLowercase %>Service.findOne(any(<%= type %>.class))).thenReturn(Observable.just(new HttpEntity<>(null, null)));

        MvcResult mvcResult = this.mockMvc.perform(get(PATH + "/{id}", "1234"))
        .andExpect(status().isOk())
        .andExpect(request().asyncStarted())
        .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isNotFound())
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint())));
        verify(<%= nounLowercase %>Service, atLeastOnce()).findOne(any(<%= type %>.class));
        }

    @Test
    public void findAll<%= nounPlural %>() throws Exception {
        final <%= noun %> <%= nounLowercase %> = new <%= noun %>();
        /* TODO - Configure and add at least one <%= noun %> to the expected List.
         * <%= nounLowercase %>.setId("id");
         */
        final List<<%= noun %>> expected = new ArrayList<>();
        expected.add(<%= nounLowercase %>);

        when(<%= nounLowercase %>Service.findAll()).thenReturn(Observable.just(new HttpEntity<>(expected)));

        MvcResult mvcResult = this.mockMvc.perform(get(PATH))
        .andExpect(status().isOk())
        .andExpect(request().asyncStarted()).andReturn();

        this.mockMvc
        .perform(asyncDispatch(mvcResult))
        .andExpect(status().isOk())
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint()),
                    /* TODO - If necessary, describe the findAll response fields (typically wrapped in an array) eg;
                     * responseFields((fieldWithPath("[]").description("An array of <%= noun %>s"))
                     * responseFields((fieldWithPath("[].property").description("The <%= noun %> property value."))
                     */
        responseFields(
            fieldWithPath("[]").description("An array of <%= noun %>s"))
        ));
        verify(<%= nounLowercase %>Service, atLeastOnce()).findAll();
        }

    @Test
    public void findAll<%= nounPlural %>Paginated() throws Exception {
        final int TOTAL = 10;
        final int PER_PAGE = TOTAL / 2;
        final List<<%= noun %>> expected = new ArrayList<>(TOTAL);

        /* TODO - Configure an expected list of <%= nounPlural %> with some identifiable attribute to verify
         * Needs to be greater than the requested page size in order for pagination.
         */
        for (int i = 0; i < TOTAL; i++) {
        final <%= noun %> <%= nounLowercase %> = new <%= noun %>();
        <%= nounLowercase %>.setName("name" + i);
        expected.add(<%= nounLowercase %>);
        }
        when(<%= nounLowercase %>Service.findAll()).thenReturn(Observable.just(new HttpEntity<>(expected)));

        MvcResult mvcResult =
        this.mockMvc
                .perform(get(PATH)
                        /* TODO - Configure the page index to get and the total number per page. eg;
                         * Get the 2nd page, where each page contains half the total collection
                         */
        .param("page", "1")
        .param("size", String.valueOf(PER_PAGE)))
        .andExpect(status().isOk())
        .andExpect(request().asyncStarted()).andReturn();


        this.mockMvc
        .perform(asyncDispatch(mvcResult))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(PER_PAGE)))
        // TODO - Verify the 1st object in the response is the expected object (after pagination)
        .andExpect(jsonPath("$[0].name", is("name" + PER_PAGE)))
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint()),
                        /* TODO - Describe the parameters for the paginated request.
                         * parameterWithName("page").description("The requested page index")
                         * parameterWithName("size").description("The requested number of <%= nounLowercasePlural %> per page")
                         */
        requestParameters(
        parameterWithName("page").description("The requested page index"),
        parameterWithName("size").description("The requested number of <%= nounLowercasePlural %> per page")
        )));

        verify(<%= nounLowercase %>Service, atLeastOnce()).findAll();
        }

    @Test
    public void update<%= noun %>Synchronously() throws Exception {
        update<%= noun %>(true);
    }

    @Test
    public void update<%= noun %>Asynchronously() throws Exception {
        update<%= noun %>(false);
    }

    public void update<%= noun %>(boolean serviceIsBlocking) throws Exception {
        // TODO - Configure the <%= noun %>'s state and an expected updated state. eg;
        final String unchangedValue = "person name";
        final String email = "someemail@email.com";
        final <%= noun %> original = new <%= noun %>();
        original.setName(unchangedValue);
        original.setEmail(email);
        original.setAge(18);
        final <%= noun %> updated = new <%= noun %>();
        updated.setName(unchangedValue);
        updated.setEmail(email);
        updated.setAge(20);


        when(<%= nounLowercase %>Service.update("99", original)).thenReturn(
            Observable.just(new HttpEntity<>(serviceIsBlocking ? updated: null, new HttpHeaders())));
        MvcResult mvcResult = this.mockMvc.perform(put(PATH + "/{id}", "99")
        .contentType(MediaType.APPLICATION_JSON)
        .content(prettyPrintRequest(this.objectMapper.writeValueAsString(original))))
        .andExpect(status().isOk())
        .andExpect(request().asyncStarted()).andReturn();

        ResultActions resultActions = this.mockMvc
        .perform(asyncDispatch(mvcResult))
        .andExpect(serviceIsBlocking ? status().isOk() : status().isAccepted())
        // TODO - Verify the updated object was returned eg;
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint()),
                        /* TODO - If necessary, describe JSON request fields. eg;
                         * requestFields(fieldWithPath("mandatoryProperty").description("The <%= noun %> mandatory mandatoryProperty value.")
                         *  .attributes(key("constraints").value("Must not be null. Must not be empty"))
                         * requestFields((fieldWithPath("optionalProperty").description("The <%= noun %> optional optionalProperty value.").optional()
                         */
        requestFields(
        fieldWithPath("name").description("The <%= noun %>'s name")
        .attributes(key("constraints").value("Must not be null. Must not be empty")),
        fieldWithPath("age").description("The <%= noun %>'s age"),
        fieldWithPath("email").description("The <%= noun %> email address")
        )));

        if (serviceIsBlocking) {
            resultActions.andExpect(
            jsonPath("$.name", is(unchangedValue)))
            .andExpect(jsonPath("$.age", is(20)));
        }

        verify(<%= nounLowercase %>Service, atLeastOnce()).update("99", original);
        }

    @Test
    public void delete<%= noun %>Synchronously() throws Exception {
        delete<%= noun %>(true);
    }

    @Test
    public void delete<%= noun %>Asynchronously() throws Exception {
        delete<%= noun %>(false);
    }

    public void delete<%= noun %>(boolean serviceIsBlocking) throws Exception {

        final <%= noun %> deleted = new <%= noun %>();
        deleted.setName("<%= nounLowercase %> name");
        deleted.setEmail("someemail@email.com");
        deleted.setAge(20);


        when(<%= nounLowercase %>Service.delete(any(<%= type %>.class))).thenReturn(
        Observable.just(new HttpEntity<>(serviceIsBlocking ? deleted: null, new HttpHeaders())));


        MvcResult mvcResult = this.mockMvc
        .perform(delete(PATH + "/{id}", "99"))
        .andExpect(status().isOk())
        .andExpect(request().asyncStarted()).andReturn();

        this.mockMvc
        .perform(asyncDispatch(mvcResult))
        .andExpect(serviceIsBlocking ? status().isOk() : status().isAccepted())
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint()),
        pathParameters(
                             /* TODO - Describe the path parameter. eg;
                             * parameterWithName("id").description("The name of the <%= noun %> to delete")),
                             */
        parameterWithName("id").description("The name of the <%= noun %> to delete")
        )));
        verify(<%= nounLowercase %>Service, atLeastOnce()).delete(any(<%= type %>.class));
        }

    @Test
    public void deleteAll<%= nounPlural %>Synchronously() throws Exception {
        deleteAll<%= nounPlural %>(true);
    }

    @Test
    public void deleteAll<%= nounPlural %>Asynchronously() throws Exception {
        deleteAll<%= nounPlural %>(false);
    }

    public void deleteAll<%= nounPlural %>(boolean serviceIsBlocking) throws Exception {

    final <%= noun %> <%= nounLowercase %> = new <%= noun %>();
    /* TODO - Configure and add at least one <%= noun %> to the expected List.
    * <%= nounLowercase %>.setId("id");
    */
    final List<<%= noun %>> expected = new ArrayList<>();
        expected.add(<%= nounLowercase %>);

        when(<%= nounLowercase %>Service.deleteAll()).thenReturn(
        Observable.just(new HttpEntity<>(serviceIsBlocking ? expected : null, new HttpHeaders())));

        MvcResult mvcResult = this.mockMvc.perform(delete(PATH))
        .andExpect(status().isOk())
        .andExpect(request().asyncStarted()).andReturn();

        if (serviceIsBlocking) {
        this.mockMvc
        .perform(asyncDispatch(mvcResult))
        .andExpect(status().isOk())
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint()),
        responseFields(
        fieldWithPath("[]").description("An array of <%= noun %>s"))
        ));
        } else {
        this.mockMvc
        .perform(asyncDispatch(mvcResult))
        .andExpect(status().isAccepted())
        .andDo(document(
        "{class-name}/{method-name}",
        preprocessResponse(prettyPrint())
        ));
        }

        verify(<%= nounLowercase %>Service, atLeastOnce()).deleteAll();
 }

<%- include snippets/NounControllerDocumentation-with-filter.ejs -%>

    private String prettyPrintRequest(String original) throws IOException {
        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper.writeValueAsString(mapper.readTree(original));
    }

}
