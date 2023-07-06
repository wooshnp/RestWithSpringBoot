package lab.training.restwithspringboot.services;

import java.util.List;

import lab.training.restwithspringboot.converter.DozerConverter;
import lab.training.restwithspringboot.exception.ResourceNotFoundException;
import lab.training.restwithspringboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.training.restwithspringboot.data.model.Person;
import lab.training.restwithspringboot.data.vo.v1.PersonVO;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public List<PersonVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
