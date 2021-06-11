package one.digitalinnovation.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.personapi.entities.Person;

@Repository
public interface  PersonRepository extends JpaRepository<Person, Long>{

}
