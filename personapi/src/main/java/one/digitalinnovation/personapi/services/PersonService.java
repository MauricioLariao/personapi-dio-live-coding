package one.digitalinnovation.personapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.mapper.PersonMapper;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundExceptio;
import one.digitalinnovation.personapi.repository.PersonRepository;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public MessageResponseDTO create(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);

        MessageResponseDTO messageResponse = createMessageResponse("Person successfully created with ID ", savedPerson.getId());

        return messageResponse;
    }

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }
    
    public List<PersonDTO> listAll() {
		List<Person> people = personRepository.findAll();
		return people.stream()
				.map(personMapper::toDTO) //cada linha do map converte para DTO usando o mapper
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundExceptio {
		//a partir do java 8 tem optional person, que pode ser utilizado para
		//verificar se a consulta tem resultado ou nao. Evitando o nulo
//		Optional<Person> optionalPerson = personRepository.findById(id);
//		if (optionalPerson.isEmpty()) {
//			throw new PersonNotFoundExceptio(id);
//		}
//		
//		return personMapper.toDTO(optionalPerson.get());
		
		//outra forma sem passar pelo optional, usando expressao lambda para err
		Person person = verifyIfExists(id);
		
		return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundExceptio {
		
		verifyIfExists(id);
		personRepository.deleteById(id);
	}
	
	private Person verifyIfExists(Long id) throws PersonNotFoundExceptio {
		
		return personRepository.findById(id)
		.orElseThrow(() -> new PersonNotFoundExceptio(id));
	}

	
}