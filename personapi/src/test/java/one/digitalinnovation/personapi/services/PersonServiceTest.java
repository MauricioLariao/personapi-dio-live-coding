package one.digitalinnovation.personapi.services;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import one.digitalinnovation.personapi.dto.mapper.PersonMapper;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.utils.PersonUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;
	
	  @Mock
	    private PersonMapper personMapper;

	    @InjectMocks
	    private PersonService personService;

	    @Test
	    void testGivenPersonDTOThenReturnSuccessSavedMessage() {
	        PersonDTO personDTO = PersonUtils.createFakeDTO();
	        Person expectedSavedPerson = PersonUtils.createFakeEntity();

	        when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
	        when(personRepository.save(expectedSavedPerson)).thenReturn(expectedSavedPerson);

	        MessageResponseDTO expectedSuccessMessage = createExpectedSuccessMessage(expectedSavedPerson.getId());
	        MessageResponseDTO successMessage = personService.create(personDTO);

	        assertEquals(expectedSuccessMessage, successMessage);
	    }

	    private MessageResponseDTO createExpectedSuccessMessage(Long savedPersonId) {
	        return MessageResponseDTO.builder()
	                .message("Person successfully created with ID " + savedPersonId)
	                .build();
	    }
	
	
	
}
