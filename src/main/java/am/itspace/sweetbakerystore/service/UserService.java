package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.City;
import am.itspace.sweetbakerystore.entity.Role;
import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.repository.AddressRepository;
import am.itspace.sweetbakerystore.repository.CityRepository;
import am.itspace.sweetbakerystore.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

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


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    public void saveUser(User user, MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            user.setProfilePic(fileName);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    @PostConstruct
    public void run()  {
        Optional<User> byEmail = userRepository.findByEmail("admin@gmail.com");
        if (byEmail.isEmpty()) {
            City gyumri = null;
            if (cityRepository.findByName("Gyumri").isEmpty()) {
                gyumri = cityRepository.save(City.builder()
                        .name("Gyumri")
                        .build());
            }
            Address adminAddress = null;
            if (addressRepository.findByCity(gyumri).isEmpty()) {
                adminAddress = addressRepository.save(Address.builder()
                        .city(gyumri)
                        .name("Sayat Nova")
                        .build());
            }

            userRepository.save(User.builder()
                    .name("admin")
                    .surname("admin")
                    .email("admin@gmail.com")
                    .phone("+374")
                    .address(adminAddress)
                    .password(passwordEncoder.encode("admin555"))
                    .role(Role.ADMIN)
                    .createAt(new Date())
                    .build());
        }
    }


}
