package com.onlinebook.demo.service.author;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.exception.RestException;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.repository.AuthorRepository;
import com.onlinebook.demo.service.MessageService;
import com.onlinebook.demo.utils.RestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService
{
    private final AuthorRepository authorRepository;
    @Override
    public Author mapToAuthorDTO(AuthorDTO authorDTO) {
        if (authorDTO == null) {
            return null;
        }
        Author author = new Author();
        author.setFirstName(author.getFirstName());
        author.setLastName(author.getLastName());
        author.setDescription(author.getDescription());
        author.setEmail(author.getEmail());
        author.setPhoneNumber(author.getPhoneNumber());
        return author;
    }


    @Override
    public ApiResult<List<AuthorDTO>> ListOfAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorDTOS = authors
                .stream()
                .map(AuthorDTO::new)
                .collect(Collectors.toList());
        return ApiResult.successResponse(authorDTOS);
    }

    @Override
    public ApiResult<AuthorDTO> getAuthorById(Long id) {
        try {
            Author author = authorRepository.getById(id);
            AuthorDTO authorDTO = new AuthorDTO(author);
            return ApiResult.successResponse(authorDTO,
                    MessageService.getMessage("AUTHOR_FOUND"));
        } catch (EntityNotFoundException exception) {
            throw RestException.restThrow(MessageService
                    .getMessage("AUTHOR_NOT_FOUND"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ApiResult<String> insertNewAuthor(AuthorDTO author) {
        int num = 0;
        boolean outcome = false;
        String[] strings = new String[4];
        for (int i = 1; i <= 4; i++) {
            switch (i) {
                case 1:
                    if (authorRepository.existsByFirstName(author.getFirstName())) {
                        num = num + 1;
                        strings[0] = "first name";
                    }
                    break;
                case 2:
                    if (authorRepository.existsByLastName(author.getLastName())) {
                        num = num + 1;
                        strings[1] = "last name";
                    }
                    break;
                case 3:
                    if (authorRepository.existsByEmail(author.getEmail())) {
                        num = num + 1;
                        strings[2] = "email";
                    }
                    break;
                case 4:
                    if (authorRepository.existsByPhoneNumber(author.getPhoneNumber())) {
                        num = num + 1;
                        strings[3] = "phone number";
                    }
                    break;

            }
        }
        for (String s : strings) {
            System.out.println(s);
        }
        StringBuilder rs = new StringBuilder();
        switch (num) {
            case 1:
                for (String s : strings) {
                    if (s == null)
                        continue;
                    else rs.append(s);
                    break;
                }
                rs.append(" exists");
                break;
            case 2:

                for (String s : strings) {
                    if (s == null)
                        continue;
                    else rs.append(s + " and ");
                }
                rs = rs.delete(rs.length() - 5, rs.length()).append(" exist");
                break;
            case 3:
                int g = 0;
                for (int i = 0; i < 4; i++) {

                    if (strings[i] == null)
                        continue;
                    else {
                        if (g > 1) {
                            rs.append(",").append(strings[i]);
                            g = g + 1;
                        }
                        if (g > 2) {
                            rs.append(" and").append(strings[i]).append(" exist");
                            break;
                        } else {
                            rs.append(strings[i]);
                            g = g + 1;
                        }
                    }
                }
                break;
            case 4:
                rs.append(strings[0]).append(",")
                        .append(strings[1])
                        .append(",")
                        .append(strings[2])
                        .append(" and ")
                        .append(strings[3] + " exist");
                break;
            default:
                outcome = true;

        }

        return outcome ? ApiResult.successResponse("successfully saved")
                : ApiResult.errorResponse(rs.toString(), RestConstants.ACCESS_DENIED);
    }

    @Override
    public ApiResult<?> deleteAuthorById(Long id) {
        if (authorRepository.getOne(id) == null) {
            return ApiResult.unsuccessResponse();
        } else {
            Author author = authorRepository.getOne(id);
            author.setDeleted(true);
            authorRepository.save(author);
            return ApiResult.successResponse();
        }
    }

    @Override
    public ApiResult<String> updatingAuthorById(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id).get();
//        Author author1 = new Author(authorDTO);
//        if (!(author1 == null)) {
//            author1.setFirstName(author.getFirstName());
//            author1.setLastName(author.getLastName());
//            author1.setDescription(author.getDescription());
//            author1.setPhoneNumber(author.getPhoneNumber());
//            author1.setEmail(author.getEmail());
//            authorRepository.save(author1);
//            return new ApiResult<>("This Author has succesfully saved");
//        } else {
        return new ApiResult<>("This Author does not exist");
//        }
    }


}
