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
@SpringApplicationConfiguration(classes = <%= noun %>Controller.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:/testContext.xml")
public class <%= noun %>ControllerTestDocumentation {

    public static final String PATH = "/<%= nounLowercasePlural %>";

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    private ObjectMapper objectMapper = new ObjectMapper();

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
    public void create<%= noun %>() throws Exception {
        final <%= noun %> <%= nounLowercase %> = new <%= noun %>();
        /* TODO - Configure the <%= noun %>'s object state. eg;
         * <%= nounLowercase %>.setId("id");
         * <%= nounLowercase %>.setSomeProperty(9);
         */
        <%= nounLowercase %>.setName("person name");
        <%= nounLowercase %>.setAge(19);
        <%= nounLowercase %>.setEmail("person@email.com");
        this.mockMvc
                .perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prettyPrintRequest(this.objectMapper.writeValueAsString(<%= nounLowercase %>))))
<<<<<<< HEAD
                .andExpect(status().isCreated())
=======
                .andExpect(status().isOk())
>>>>>>> 2882481a9e50b26a8c281e6f93b8da424c21c6e9
                .andDo(document(
                        "{class-name}/{method-name}",
                        preprocessResponse(prettyPrint()),
                        /* TODO - Describe all mandatory and optional <%= noun %> JSON request fields. eg;
                         * requestFields((fieldWithPath("mandatoryProperty").description("The <%= noun %> mandatory mandatoryProperty value."))
                         * requestFields((fieldWithPath("optionalProperty").description("The <%= noun %> optional optionalProperty value.").optional()
                         */
                        requestFields(
                            fieldWithPath("name").description("The <%= noun %>'s name"),
                            fieldWithPath("age").description("The <%= noun %>'s age"),
                            fieldWithPath("email").description("The <%= noun %> email address")
                        )));
        verify(<%= nounLowercase %>Service, atLeastOnce()).create(any(<%= noun %>.class));
    }

    @Test
    public void findOne<%= noun %>NotFound() throws Exception {
        when(<%= nounLowercase %>Service.findOne(any(<%= type %>.class))).thenReturn(null);
        this.mockMvc
            .perform(get(PATH + "/{id}", "invalid"))
            .andExpect(status().isNotFound());
        verify(<%= nounLowercase %>Service, atLeastOnce()).findOne(any(<%= type %>.class));
    }

    @Test
    public void findOne<%= noun %>() throws Exception {
        final <%= noun %> expected = new <%= noun %>();
        when(<%= nounLowercase %>Service.findOne(any(<%= type %>.class))).thenReturn(expected);
        this.mockMvc
            .perform(get(PATH + "/{id}", "invalid"))
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
    public void findAll<%= nounPlural %>() throws Exception {
        final <%= noun %> <%= nounLowercase %> = new <%= noun %>();
        /* TODO - Configure and add at least one <%= noun %> to the expected List.
         * <%= nounLowercase %>.setId("id");
         */
        final List<<%= noun %>> expected = new ArrayList<>();
        expected.add(<%= nounLowercase %>);

        when(<%= nounLowercase %>Service.findAll()).thenReturn(expected);
        this.mockMvc
            .perform(get(PATH))
            .andExpect(status().isOk())
            .andDo(document(
                "{class-name}/{method-name}",
                preprocessResponse(prettyPrint()),
                /* TODO - If necessary, describe the findAll response fields (typically wrapped in an array) eg;
                 * responseFields((fieldWithPath("[]").description("An array of <%= noun %>s"))
                 * responseFields((fieldWithPath("[].property").description("The <%= noun %> property value."))
                 */
                responseFields((fieldWithPath("[]").description("An array of <%= noun %>s"))
            )));
        verify(<%= nounLowercase %>Service, atLeastOnce()).findAll();
    }

    private String prettyPrintRequest(String original) throws IOException {
        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper.writeValueAsString(mapper.readTree(original));
    }

}
