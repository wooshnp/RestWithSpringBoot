package lab.training.restwithspringboot.services;

import java.util.List;

import lab.training.restwithspringboot.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.training.restwithspringboot.converter.DozerConverter;
import lab.training.restwithspringboot.data.model.Book;
import lab.training.restwithspringboot.data.vo.v1.BookVO;
import lab.training.restwithspringboot.repository.BookRepository;

@Service
public class BookServices {
    BookRepository repository;

    @Autowired
    public BookServices(BookRepository repository) {
        this.repository = repository;
    }

    public BookVO create(BookVO book) {
        var entity = DozerConverter.parseObject(book, Book.class);
        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }

    public List<BookVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
    }

    public BookVO findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public BookVO update(BookVO book) {
        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
