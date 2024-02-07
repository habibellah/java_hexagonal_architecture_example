package com.habibellah.hexagonalArchitecture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


//input dto`s
@Data
@AllArgsConstructor
@NoArgsConstructor
class CreateBookWithNewAuthorInput {
	private String id;
	private String title;
	private InputCreateAuthor inputCreateAuthor;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class InputCreateAuthor{
	private String id;
	private FullNameInputData fullNameInputData;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class InputUpdateAuthor{
	private String id;
	private FullNameInputData fullNameInputData;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FullNameInputData{
	private String fName;
	private String lName;
}
//End input dto`s

//output dto`s
@Data
@AllArgsConstructor
@NoArgsConstructor
class BookOutputData{
	private String id;
	private String title;
	private AuthorOutputData authorOutputData;

	static BookOutputData fromBook(Book book){
		return new BookOutputData(book.getId(),book.getTitle(),new AuthorOutputData(book.getAuthor().getId(),new FullNameOutputData(book.getAuthor().getFullName().getFName(),book.getAuthor().getFullName().getLName())));
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class AuthorOutputData {
	private String id;
	private FullNameOutputData fullNameOutputData;

	public static AuthorOutputData fromAuthor(Author author) {
		return new AuthorOutputData(author.getId(),new FullNameOutputData(author.getFullName().getFName(),author.getFullName().getLName()));
	}

	public static AuthorOutputData fromInputUpdateAuthor(InputUpdateAuthor inputUpdateAuthor) {
		return new AuthorOutputData(inputUpdateAuthor.getId(),new FullNameOutputData(inputUpdateAuthor.getFullNameInputData().getFName(),inputUpdateAuthor.getFullNameInputData().getLName()));
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class OutputUpdatedAuthor {
	private String id;
	private FullNameOutputData fullNameOutputData;

	public static OutputUpdatedAuthor fromInputUpdateAuthor(InputUpdateAuthor inputUpdateAuthor) {
		return new OutputUpdatedAuthor(inputUpdateAuthor.getId(),new FullNameOutputData(inputUpdateAuthor.getFullNameInputData().getFName(),inputUpdateAuthor.getFullNameInputData().getLName()));
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FullNameOutputData {
	private String fName;
	private String lName;
}
//End output dto`s


//business objects
@Data
@AllArgsConstructor
@NoArgsConstructor
class Book{
	private String id;
  private String title;
  private Author author;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Author{
	private String id;
	private FullName fullName;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FullName{
	private String fName;
	private String lName;
}

//End business objects

//application services
interface BookPort{
	void save(BookOutputData bookOutputData);

	void deleteBook(String id);

	List<BookOutputData> findAllBook();
}

interface AuthorPort{
	void save(AuthorOutputData authorOutputData);

	void deleteAuthor(String id);

	List<AuthorOutputData> findAllAuthors();

	OutputUpdatedAuthor updateAuthor(InputUpdateAuthor inputUpdateAuthor);
}

class BookService{
	private final BookPort bookPort;

	public BookService(BookPort bookPort) {
		this.bookPort = bookPort;
	}

	BookOutputData createBookWithNewAuthor(CreateBookWithNewAuthorInput createBookWithNewAuthorInput){
    Book book = new Book(createBookWithNewAuthorInput.getId(),createBookWithNewAuthorInput.getTitle()
			, new Author(createBookWithNewAuthorInput.getInputCreateAuthor().getId(),new FullName(createBookWithNewAuthorInput.getInputCreateAuthor().getFullNameInputData().getFName(),
			createBookWithNewAuthorInput.getInputCreateAuthor().getFullNameInputData().getLName())));
	BookOutputData bookOutputData = BookOutputData.fromBook(book);
	bookPort.save(bookOutputData);
	return bookOutputData;
	}

	void deleteBook(String id){
    bookPort.deleteBook(id);
	}

	List<BookOutputData> findAllBook(){
  return bookPort.findAllBook();
	}
}

class AuthorService{

	private final AuthorPort authorPort;

	public AuthorService(AuthorPort authorPort) {
		this.authorPort = authorPort;
	}

	AuthorOutputData createAuthor(InputCreateAuthor inputCreateAuthor){
		Author author = new Author(inputCreateAuthor.getId(),new FullName(inputCreateAuthor.getFullNameInputData().getFName(),inputCreateAuthor.getFullNameInputData().getLName()));
		AuthorOutputData authorOutputData = AuthorOutputData.fromAuthor(author);
		authorPort.save(authorOutputData);
        return authorOutputData;
	}
	void deleteAuthor(String id){
  authorPort.deleteAuthor(id);
	}

	OutputUpdatedAuthor updateAuthor(String id,InputUpdateAuthor inputUpdateAuthor){
      inputUpdateAuthor.setId(id);
	 return authorPort.updateAuthor(inputUpdateAuthor);
	}

	List<AuthorOutputData> findAllAuthor(){
   return authorPort.findAllAuthors();
	}
}


//Driven Side
//persistence
class InMemory implements BookPort, AuthorPort{
	List<BookOutputData> bookList = new ArrayList<>();
	List<AuthorOutputData> authorOutputDataList = new ArrayList<>();

	@Override
	public void save(BookOutputData bookOutputData) {
		bookList.add(bookOutputData);
	}

	@Override
	public void deleteBook(String id) {
		bookList.removeIf(book -> book.getId().equals(id));
	}

	@Override
	public List<BookOutputData> findAllBook() {
		return bookList;
	}

	@Override
	public void save(AuthorOutputData authorOutputData) {
		authorOutputDataList.add(authorOutputData);
	}

	@Override
	public void deleteAuthor(String id) {
		authorOutputDataList.removeIf(author -> author.getId().equals(id));
	}

	@Override
	public OutputUpdatedAuthor updateAuthor(InputUpdateAuthor inputUpdateAuthor) {
		authorOutputDataList.removeIf(outputAuthor -> outputAuthor.getId().equals(inputUpdateAuthor.getId()));
		authorOutputDataList.add(AuthorOutputData.fromInputUpdateAuthor(inputUpdateAuthor));
		return OutputUpdatedAuthor.fromInputUpdateAuthor(inputUpdateAuthor);
	}

	@Override
	public List<AuthorOutputData> findAllAuthors() {
		return authorOutputDataList;
	}
}
//End Driven Side

//@SpringBootApplication
public class HexagonalArchitectureApplication {

	public static void main(String[] args) {
	//	SpringApplication.run(HexagonalArchitectureApplication.class, args);
      BookService bookService = new BookService(new InMemory());
	  AuthorService authorService = new AuthorService(new InMemory());
		BookOutputData bookOutputData =  bookService.createBookWithNewAuthor(new CreateBookWithNewAuthorInput("id1","book title1",new InputCreateAuthor("authorId1",new FullNameInputData("habibellah1","ayata1"))));
		BookOutputData bookOutputData1 =  bookService.createBookWithNewAuthor(new CreateBookWithNewAuthorInput("id2","book title2",new InputCreateAuthor("authorId2",new FullNameInputData("habibellah2","ayata2"))));
		BookOutputData bookOutputData2 =  bookService.createBookWithNewAuthor(new CreateBookWithNewAuthorInput("id3","book title3",new InputCreateAuthor("authorId3",new FullNameInputData("habibellah3","ayata3"))));
		BookOutputData bookOutputData3 =  bookService.createBookWithNewAuthor(new CreateBookWithNewAuthorInput("id4","book title4",new InputCreateAuthor("authorId4",new FullNameInputData("habibellah4","ayata4"))));
		BookOutputData bookOutputData4 =  bookService.createBookWithNewAuthor(new CreateBookWithNewAuthorInput("id5","book title5",new InputCreateAuthor("authorId5",new FullNameInputData("habibellah5","ayata5"))));
		AuthorOutputData authorOutputData = authorService.createAuthor(new InputCreateAuthor("author1",new FullNameInputData("habibellah1","ayata1")));
		AuthorOutputData authorOutputData1 = authorService.createAuthor(new InputCreateAuthor("author2",new FullNameInputData("habibellah2","ayata2")));
		AuthorOutputData authorOutputData2 = authorService.createAuthor(new InputCreateAuthor("author3",new FullNameInputData("habibellah3","ayata3")));
		AuthorOutputData authorOutputData3 = authorService.createAuthor(new InputCreateAuthor("author4",new FullNameInputData("habibellah4","ayata4")));
		AuthorOutputData authorOutputData4 = authorService.createAuthor(new InputCreateAuthor("author5",new FullNameInputData("habibellah5","ayata5")));

		System.out.println("Book and author creation example");
		System.out.println(bookOutputData.getTitle() + " "+bookOutputData.getAuthorOutputData().getFullNameOutputData().getFName()+" "+bookOutputData.getAuthorOutputData().getFullNameOutputData().getLName());
		System.out.println("author data is : "+ authorOutputData.getFullNameOutputData().getFName()+authorOutputData.getFullNameOutputData().getLName());
		System.out.println("find all books example");
		bookService.findAllBook().forEach(bookOutput -> {
			System.out.println("the id of book "+ bookOutput.getId());
		});
		System.out.println("delete book example");
		bookService.deleteBook("id1");
		System.out.println("find all books after delete one");
		bookService.findAllBook().forEach(bookOutput -> {
			System.out.println("the id of book "+ bookOutput.getId());
		});

		System.out.println("find all authors example");
		authorService.findAllAuthor().forEach(bookOutput -> {
			System.out.println("the id of book "+ bookOutput.getId());
		});
		System.out.println("delete author example example");
		authorService.deleteAuthor("author5");
		System.out.println("find all authors after delete one");
		authorService.findAllAuthor().forEach(bookOutput -> {
			System.out.println("the id of author "+ bookOutput.getId());
		});

		System.out.println("update author example");
		OutputUpdatedAuthor updatedAuthor = authorService.updateAuthor("author4",new InputUpdateAuthor("author4",new FullNameInputData("cherif","Bouchelaghem")));
		System.out.println(updatedAuthor.getFullNameOutputData().getFName());
		System.out.println(updatedAuthor.getFullNameOutputData().getLName());
		System.out.println(updatedAuthor.getId());
		System.out.println("find all authors after update");
		System.out.println();
		authorService.findAllAuthor().forEach(bookOutput -> {
			System.out.println(bookOutput.getFullNameOutputData().getFName());
		});
	}

}
