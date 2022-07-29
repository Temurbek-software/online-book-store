package com.onlinebook.demo.service.author;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.repository.AuthorRepository;
import com.onlinebook.demo.service.roleservices.ConverterTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ApiResult<String> saveAuthor(AuthorDTO authorDTO) {
        Author author = converterTo.toAuhtor(authorDTO);
        authorRepository.save(author);
        return ApiResult.successResponse("Successfully saved");
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
