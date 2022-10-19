package com.onlinebook.demo.service.author;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.entity.Category;
import com.onlinebook.demo.exception.RestException;
import com.onlinebook.demo.mapper.ServiceMapper;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.repository.AuthorRepository;
import com.onlinebook.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ServiceMapper serviceMapper;


    @Override
    public ApiResult<List<AuthorDTO>> ListOfAllAuthors() {
        List<Author> authors = authorRepository.getAllByAuthorIfNotDeleted(false);
        List<AuthorDTO> authorDTOS = authors
                .stream()
                .map(serviceMapper::mapToAuthorDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(authorDTOS);
    }

    @Override
    public ApiResult<AuthorDTO> getAuthorById(Long id) {
        try {
            Author author = authorRepository.getById(id);

            AuthorDTO authorDTO = serviceMapper.mapToAuthorDTO(author);
            return ApiResult.successResponse(authorDTO,
                    MessageService.getMessage("AUTHOR_FOUND"));
        } catch (EntityNotFoundException exception) {
            throw RestException.restThrow(MessageService
                    .getMessage("AUTHOR_NOT_FOUND"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ApiResult<String> insertNewAuthor(AuthorDTO author) {
        Author author1 = serviceMapper.mapToAuthor(author);
        authorRepository.save(author1);
        return ApiResult.successResponse("Saved successfully");
//        int num = 0;
//        boolean outcome = false;
//        String[] strings = new String[4];
//        for (int i = 1; i <= 4; i++) {
//            switch (i) {
//                case 1:
//                    if (authorRepository.existsByFirstName(author.getFirstName())) {
//                        num = num + 1;
//                        strings[0] = "first name";
//                    }
//                    break;
//                case 2:
//                    if (authorRepository.existsByLastName(author.getLastName())) {
//                        num = num + 1;
//                        strings[1] = "last name";
//                    }
//                    break;
//                case 3:
//                    if (authorRepository.existsByEmail(author.getEmail())) {
//                        num = num + 1;
//                        strings[2] = "email";
//                    }
//                    break;
//                case 4:
//                    if (authorRepository.existsByPhoneNumber(author.getPhoneNumber())) {
//                        num = num + 1;
//                        strings[3] = "phone number";
//                    }
//                    break;
//
//            }
//        }
//        for (String s : strings) {
//            System.out.println(s);
//        }
//        StringBuilder rs = new StringBuilder();
//        switch (num) {
//            case 1:
//                for (String s : strings) {
//                    if (s == null)
//                        continue;
//                    else rs.append(s);
//                    break;
//                }
//                rs.append(" exists");
//                break;
//            case 2:
//
//                for (String s : strings) {
//                    if (s == null)
//                        continue;
//                    else rs.append(s + " and ");
//                }
//                rs = rs.delete(rs.length() - 5, rs.length()).append(" exist");
//                break;
//            case 3:
//                int g = 0;
//                for (int i = 0; i < 4; i++) {
//
//                    if (strings[i] == null)
//                        continue;
//                    else {
//                        if (g > 1) {
//                            rs.append(",").append(strings[i]);
//                            g = g + 1;
//                        }
//                        if (g > 2) {
//                            rs.append(" and").append(strings[i]).append(" exist");
//                            break;
//                        } else {
//                            rs.append(strings[i]);
//                            g = g + 1;
//                        }
//                    }
//                }
//                break;
//            case 4:
//                rs.append(strings[0]).append(",")
//                        .append(strings[1])
//                        .append(",")
//                        .append(strings[2])
//                        .append(" and ")
//                        .append(strings[3] + " exist");
//                break;
//            default:
//                outcome = true;
//
//        }
//
//        return outcome ? ApiResult.successResponse("successfully saved")
//                : ApiResult.errorResponse(rs.toString(), RestConstants.ACCESS_DENIED);
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
    public ApiResult<?> updatingAuthorById(Long id, AuthorDTO authorDTO) {
        Optional<Author> author1 = authorRepository.findById(id);
        if (author1.isPresent()) {
            Author author = authorRepository.getById(id);
            author.setLastName(authorDTO.getLastName());
            author.setFirstName(authorDTO.getFirstName());
            author.setEmail(authorDTO.getEmail());
            author.setPhoneNumber(authorDTO.getPhoneNumber());
            author.setDescription(authorDTO.getDescription());
        } else {
            throw RestException.restThrow("not found", "author",
                    id, MessageService.getMessage("AUTHOR_NOT_FOUND"));
        }
        return ApiResult.successResponse(authorDTO, "category has changed");
    }
}
