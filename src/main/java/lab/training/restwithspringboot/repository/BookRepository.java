package lab.training.restwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.training.restwithspringboot.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}