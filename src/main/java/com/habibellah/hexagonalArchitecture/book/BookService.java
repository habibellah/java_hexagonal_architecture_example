package com.habibellah.hexagonalArchitecture.book;




import com.habibellah.hexagonalArchitecture.author.domain.Author;
import com.habibellah.hexagonalArchitecture.author.domain.FullName;
import com.habibellah.hexagonalArchitecture.book.domain.Book;
import com.habibellah.hexagonalArchitecture.book.inputDto.CreateBookWithNewAuthorInput;
import com.habibellah.hexagonalArchitecture.book.outputDto.BookOutputData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookPort bookPort;

    public BookService(BookPort bookPort) {
        this.bookPort = bookPort;
    }

    public BookOutputData createBookWithNewAuthor(CreateBookWithNewAuthorInput createBookWithNewAuthorInput){
        Book book = new Book(createBookWithNewAuthorInput.getId()
                ,createBookWithNewAuthorInput.getTitle()
                ,new Author(createBookWithNewAuthorInput.getInputAuthorData().getId(),
                new FullName(createBookWithNewAuthorInput.getInputAuthorData().getFullNameInputData().getFname()
                        ,createBookWithNewAuthorInput.getInputAuthorData().getFullNameInputData().getLname())));

        BookOutputData bookOutputData = BookOutputData.fromBook(book);
        return bookPort.save(bookOutputData);
    }

    public void deleteBook(String id){
        bookPort.deleteBook(id);
    }

    public List<BookOutputData> findAllBook(){
        return bookPort.findAllBook();
    }

    public boolean isExist(String isbn) {
        return bookPort.isExist(isbn);
    }
}
