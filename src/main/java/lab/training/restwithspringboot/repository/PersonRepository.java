package lab.training.restwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.training.restwithspringboot.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}