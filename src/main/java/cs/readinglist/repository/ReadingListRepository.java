package cs.readinglist.repository;

import cs.readinglist.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<Book, Long> {
    @Override
    List<Book> findAll();

    @Override
    List<Book> findAllById(Iterable<Long> longs);

    Book findFirstById(Long id);

    List<Book> findByReader(String reader);

    List<Book> findByAuthor(String author);

    List<Book> findByMaklube(String maklube);
}
