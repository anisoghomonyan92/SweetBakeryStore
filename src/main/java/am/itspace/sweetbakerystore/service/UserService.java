package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    @Value("${sweet.bakery.store.images.folder}")
    private String folderPath;

    public Page<User> findPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    public byte[] getUserImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }


}
