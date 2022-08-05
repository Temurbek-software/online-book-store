package com.onlinebook.demo.service.author;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.repository.AuthorRepository;
import com.onlinebook.demo.service.roleservices.ConverterTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final ConverterTo converterTo;
    private final AuthorRepository authorRepository;

    @Override
    public ApiResult<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorDTOS = authors.stream()
                .map(per -> converterTo.toAuhtorDTO(per))
                .collect(Collectors.toList());
        return ApiResult.successResponse(authorDTOS);
    }

    @Override
    public ApiResult<AuthorDTO> getoneAuthor(Long id) {
        Author author = authorRepository.getById(id);
        return ApiResult.successResponse(converterTo.toAuhtorDTO(author));
    }

    @Override
    public ApiResult<String> saveAuthor( AuthorDTO authorDTO)
    {
        Author author = converterTo.toAuhtor(authorDTO);
        StringBuilder msg=new StringBuilder();
        List<Author> authors= authorRepository.findAll();
        for (Author author1:authors)
        {
            if (author1.getFirstName().equals(author.getFirstName())
                    ||author1.getLastName().equals(author.getLastName()))
            {
              msg.append("FirstName or last name is the same as other '\n");
            }
            if (author1.getEmail().equals(author.getEmail()))
            {
                msg.append("Email has already taken !'\n'");
            }
            if (author1.getPhoneNumber().equals(author.getPhoneNumber()))
            {
                msg.append("phone number has already taken '\n'");
            }
        }
        if (msg.length()==0)
        {
            authorRepository.save(author);
            msg.append("New author saved");
        }
        return ApiResult.successResponse(msg.toString());
    }

    @Override
    public ApiResult<?> deleteAuthor(Long id) {
        if (authorRepository.getOne(id) == null) {
            return ApiResult.successResponse("Not exist");
        } else {
            authorRepository.deleteById(id);
            return ApiResult.successResponse("Successfuly deleted");
        }
    }

    @Override
    public ApiResult<String> updatingAuhtor(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id).get();
        Author author1 = converterTo.toAuhtor(authorDTO);
        if (!(author1 == null)) {
            author1.setFirstName(author.getFirstName());
            author1.setLastName(author.getLastName());
            author1.setDescription(author.getDescription());
            author1.setPhoneNumber(author.getPhoneNumber());
            author1.setEmail(author.getEmail());
            authorRepository.save(author1);
            return new ApiResult<>("This Author has succesfully saved");
        } else {
            return new ApiResult<>("This Author does not exist");
        }
    }
}
