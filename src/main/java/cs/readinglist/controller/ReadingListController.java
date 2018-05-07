package cs.readinglist.controller;

import cs.readinglist.entity.Book;
import cs.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);

        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String readersBookDetail(@PathVariable Long id, Model model){
        // todo
        Book book = readingListRepository.findFirstById(id);
        model.addAttribute("book",book);
        return "booksDetail2";
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable Long id,Book book){
        readingListRepository.save(book);
        return "redirect:/book/{id}";
    }
}
