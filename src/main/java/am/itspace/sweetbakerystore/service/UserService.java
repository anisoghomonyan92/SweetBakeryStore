package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.City;
import am.itspace.sweetbakerystore.entity.Role;
import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.repository.AddressRepository;
import am.itspace.sweetbakerystore.repository.CityRepository;
import am.itspace.sweetbakerystore.repository.UserRepository;
import am.itspace.sweetbakerystore.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

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
        user.setRole(Role.USER);
        user.setCreateAt(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false);
        user.setVerifyToken(UUID.randomUUID().toString());
        userRepository.save(user);
        mailService.sendHtmlMail(user.getEmail(), "Please verify your registration.",
                "Hi " + user.getName() + "," + "\n" +
                        "Please verify your account by clicking on this link " +
                        "<a href=\"http://localhost:8080/verify/user?email=" + user.getEmail() +
                        "&token=" + user.getVerifyToken() + "\">Activate</a>");
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @PostConstruct
    public void run() {
        Optional<User> byEmail = userRepository.findByEmail("itacademy30@gmail.com");
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
                    .email("itacademy30@gmail.com")
                    .phone("+374")
                    .address(adminAddress)
                    .isActive(true)
                    .password(passwordEncoder.encode("admin555"))
                    .isActive(true)
                    .role(Role.ADMIN)
                    .createAt(new Date())
                    .build());
        }
    }

    public boolean verifyUser(String email, String token) throws Exception {
        Optional<User> userOptional = userRepository.findByEmailAndVerifyToken(email, token);
        ;
        if (userOptional.isEmpty()) {
//            throw new Exception("User doesn't exist with email and token.");
            return false;
        } else {
            User user = userOptional.get();
            if (user.isActive()) {
                return false;
            }
            userRepository.enable(user.getId());
            user.setVerifyToken(null);
            user.setActive(true);
            userRepository.save(user);
            return true;
        }
    }

    public Optional<User> findByIdAndRole(int userId, Role role) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(role);
            userRepository.save(user);
        }
        return userOptional;
    }

    public Optional<User> getAdminEmail() {
        return userRepository.findByRole(Role.ADMIN);
    }

    public Long getAll() {
        return userRepository.count();
    }

    @Transactional(readOnly = true)
    public Long getCountOfUsers() {
        return userRepository.count();
    }

    public User getByEmail(String email) {
        Optional<User> userByEmail = userRepository.getByEmail(email);
        userByEmail.ifPresent(user -> userByEmail.get());
        return userByEmail.get();
    }


    public void updateUser(User user, MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + filename);
            file.transferTo(newFile);
            user.setProfilePic(filename);
        }
        Optional<User> userById = userRepository.findById(user.getId());
        if (userById.isPresent()) {
            user.setId(userById.get().getId());
            user.setRole(userById.get().getRole());
            user.setCreateAt(userById.get().getCreateAt());
            user.setActive(user.isActive());
            user.setPassword(userById.get().getPassword());
            user.setEmail(userById.get().getEmail());
            user.setActive(true);
        }
        userRepository.save(user);
    }

    public Object saveUserAddress(CurrentUser currentUser) {
        return currentUser.getUser().getAddress();
    }



    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }


}