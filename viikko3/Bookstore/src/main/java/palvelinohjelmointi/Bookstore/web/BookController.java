package palvelinohjelmointi.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import palvelinohjelmointi.Bookstore.domain.Book;
import palvelinohjelmointi.Bookstore.domain.BookRepository;
import palvelinohjelmointi.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value="/addbook")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categorylist", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveBook(Book book){
		repository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value="/edit/{id}")
		public String editBook(@PathVariable("id") Long id, Model model){
		model.addAttribute("book", repository.findOne(id));
		model.addAttribute("books", repository.findAll());
		model.addAttribute("categorylist", crepository.findAll());
		return "editbook";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model){
		repository.delete(id);
		return "redirect:../booklist";
	}
}
