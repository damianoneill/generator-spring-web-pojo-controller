package <%= packageName %>.<%= nounLowercase %>;

import <%= packageName %>.ClientErrorInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static <%= packageName %>.ControllerHelper.toOkResponseEntity;
import static <%= packageName %>.ControllerHelper.toResponseEntity;

/**
 * The <%= noun %> REST Controller.
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 */
@RestController
public class <%= noun %>Controller {

    private static final String NOT_AVAILABLE = "Not Available";
    private static final String FIELD_ERROR_IN_OBJECT = "Field error in object \'";
    private static final String ON_FIELD = "\' on field \'";
    private static final String COLON = "\': ";

    @Autowired
    private <%= noun %>Service <%= nounLowercase %>Service;

    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<<%= noun %>>> create(@RequestBody @Valid <%= noun %> <%= nounLowercase %>) {
        DeferredResult<ResponseEntity<<%= noun %>>> result = new DeferredResult<>();

        <%= nounLowercase %>Service.create(<%= nounLowercase %>).subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.ACCEPTED)), result::setErrorResult);

        return result;
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<<%= noun %>>> findOne(@PathVariable("id") <%= type %> id) {
        final DeferredResult<ResponseEntity<<%=noun%>>> result = new DeferredResult<>();

        <%=nounLowercase%>Service.findOne(id).subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.NOT_FOUND)),
                result::setErrorResult);

        return result;
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<List<<%= noun %>>>> findAll() {
        final DeferredResult<ResponseEntity<List<<%= noun %>>>> result = new DeferredResult<>();
        <%=nounLowercase%>Service.findAll().subscribe(
                he -> result.setResult(
                        toOkResponseEntity(he.getBody(), he.getHeaders())),
                result::setErrorResult);
        return result;
    }

    /**
     * For e.g. http://localhost:8080/<%= nounLowercasePlural %>?page=0&size=2
     */
    @RequestMapping(value = "/<%= nounLowercasePlural %>", params = {"page", "size"}, method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<List<<%= noun %>>>> findPaginated(@RequestParam("page") long page, @RequestParam("size") long size) {
        final DeferredResult<ResponseEntity<List<<%= noun %>>>> result = new DeferredResult<>();
        <%= nounLowercase %>Service.findAll().subscribe(he -> {
            final List<<%= noun %>> pagedList = he.getBody().stream().skip(page * size).limit(size)
                    .collect(Collectors.toCollection(ArrayList::new));
            result.setResult(toOkResponseEntity(pagedList, he.getHeaders()));
        }, result::setErrorResult);
        return result;
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.PUT)
    public DeferredResult<ResponseEntity<<%= noun %>>> update(@RequestBody @Valid <%= noun %> <%= nounLowercase %>) {
        DeferredResult<ResponseEntity<<%= noun %>>> result = new DeferredResult<>();

        <%= nounLowercase %>Service.update(<%= nounLowercase %>).subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.ACCEPTED)), result::setErrorResult);
        return result;

    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.DELETE)
    public DeferredResult<ResponseEntity<<%= noun %>>> delete(@PathVariable("id") <%= type %> id) {
        DeferredResult<ResponseEntity<<%= noun %>>> result = new DeferredResult<>();
        <%= nounLowercase %>Service.delete(id).subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.ACCEPTED)), result::setErrorResult);
        return result;
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.DELETE)
    public DeferredResult<ResponseEntity<List<<%= noun %>>>> deleteAll() {
        final DeferredResult<ResponseEntity<List<<%= noun %>>>> result = new DeferredResult<>();
        <%= nounLowercase %>Service.deleteAll().subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.ACCEPTED)), result::setErrorResult);
        return result;
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ClientErrorInformation> handleUnsupportedOperation(HttpServletRequest req, Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClientErrorInformation> handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException e) {
        String message = NOT_AVAILABLE;
        FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
        if (fieldError != null) {
            message = FIELD_ERROR_IN_OBJECT + fieldError.getObjectName() + ON_FIELD + fieldError.getField() + COLON +
                    fieldError.getDefaultMessage();
        }
        ClientErrorInformation error = new ClientErrorInformation(message, req.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    <%- include snippets/NounController-with-filter.ejs -%>

}
