package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.model.Role;
import com.megan.webp3l.model.User;
import com.megan.webp3l.repository.BranchRepository;
import com.megan.webp3l.repository.RoleRepository;
import com.megan.webp3l.repository.UserRepository;
import com.megan.webp3l.request.UserRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public DefaultResponse addCustomer(UserRequest req) {
        if(!repository.existsByEmailAndRole(req.getEmail(), roleRepository.findRoleById(6))
                && !repository.existsByIdentityNumbAndRole(req.getIdentityNumb(), roleRepository.findRoleById(6))) {
            User user = User.builder()
                    .name(req.getName())
                    .email(req.getEmail())
                    .address(req.getAddress())
                    .identityNumb(req.getIdentityNumb())
                    .password(req.getPassword())
                    .telp_numb(req.getTelp_numb())
                    .role(roleRepository.findRoleById(req.getRole()))
                    .instance_name("-")
                    .status("AKTIF")
                    .build();

            return DefaultResponse.builder()
                    .data(repository.save(user))
                    .message("Data berhasil ditambahkan")
                    .build();
        }
        else
            if (repository.existsByEmailAndRole(req.getEmail(), roleRepository.findRoleById(6)))
                return DefaultResponse.builder()
                        .message("Email sudah terdaftar")
                        .build();
            else
                return DefaultResponse.builder()
                        .message("Nomor identitas sudah terdaftar")
                        .build();

    }

    @Override
    public DefaultResponse addEmployee(UserRequest req) {
        if (!repository.existsByEmailAndRoleNotIn(req.getEmail(), roleRepository.findRolesByIdGreaterThan(5)) &&
                !repository.existsByIdentityNumbAndRoleNotIn(req.getIdentityNumb(),
                        roleRepository.findRolesByIdGreaterThan(5))) {
            User user = User.builder()
                    .name(req.getName())
                    .email(req.getEmail())
                    .address(req.getAddress())
                    .identityNumb(req.getIdentityNumb())
                    .password(req.getPassword())
                    .telp_numb(req.getTelp_numb())
                    .role(roleRepository.findRoleById(req.getRole()))
                    .branch(branchRepository.findBranchById(req.getBranch()))
                    .status("Aktif")
                    .build();

            return DefaultResponse.builder()
                    .data(repository.save(user))
                    .message("Data berhasil ditambahkan")
                    .build();
        }
        else
            if(repository.existsByEmailAndRoleNotIn(req.getEmail(), roleRepository.findRolesByIdGreaterThan(5)))
                return DefaultResponse.builder()
                .message("Email sudah terdaftar!")
                .build();
            else
                return DefaultResponse.builder()
                        .message("Nomor identitas sudah terdaftar!")
                        .build();
    }

    @Override
    public DefaultResponse addGuest(UserRequest req) {
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .address(req.getAddress())
                .identityNumb(req.getIdentityNumb())
                .telp_numb(req.getTelp_numb())
                .role(roleRepository.findRoleById(req.getRole()))
                .status("Aktif")
                .build();

        return DefaultResponse.builder()
                .data(repository.save(user))
                .message("Data berhasil ditambahkan")
                .build();
    }

    @Override
    public DefaultResponse updateCustomer(UserRequest req, int id) {
        if (repository.existsById(id))  {
            int cek = repository.findByEmailAndRole(req.getEmail(), roleRepository.findRoleById(req.getRole())).getId();
            if ((!repository.existsByEmailAndRole(req.getEmail(), roleRepository.findRoleById(req.getRole())) &&
                    !repository.existsByIdentityNumbAndRole(req.getIdentityNumb(), roleRepository.findRoleById(req.getRole()))) ||
                    cek == id) {
                User user = User.builder()
                        .id(id)
                        .name(req.getName())
                        .email(req.getEmail())
                        .address(req.getAddress())
                        .identityNumb(req.getIdentityNumb())
                        .password(req.getPassword())
                        .telp_numb(req.getTelp_numb())
                        .role(roleRepository.findRoleById(req.getRole()))
                        .status(req.getStatus())
                        .build();

                return DefaultResponse.builder()
                        .data(repository.save(user))
                        .message("Data berhasil ditambahkan")
                        .build();
            }
            else
                if (repository.existsByEmailAndRole(req.getEmail(), roleRepository.findRoleById(6)))
                    return DefaultResponse.builder()
                            .message("Email sudah terdaftar")
                            .build();
            else
                return DefaultResponse.builder()
                        .message("Nomor identitas sudah terdaftar")
                        .build();

        }
        else
            return DefaultResponse.builder()
                    .message("Data tidak ditemukan")
                    .build();
    }

    @Override
    public DefaultResponse updateEmployee(UserRequest req, int id) {
        if (repository.existsById(id))  {
            int cek = repository.findUserByEmailAndRoleNotIn(req.getEmail(), roleRepository.findRolesByIdGreaterThan(5)).getId();
            if ((!repository.existsByEmailAndRoleNotIn(req.getEmail(), roleRepository.findRolesByIdGreaterThan(5)) &&
                    !repository.existsByIdentityNumbAndRoleNotIn(req.getIdentityNumb(),
                            roleRepository.findRolesByIdGreaterThan(5))) || cek == id) {
                User user = User.builder()
                        .id(id)
                        .name(req.getName())
                        .email(req.getEmail())
                        .address(req.getAddress())
                        .identityNumb(req.getIdentityNumb())
                        .password(req.getPassword())
                        .telp_numb(req.getTelp_numb())
                        .branch(branchRepository.findBranchById(req.getBranch()))
                        .role(roleRepository.findRoleById(req.getRole()))
                        .status(req.getStatus())
                        .build();

                return DefaultResponse.builder()
                        .data(repository.save(user))
                        .message("Data berhasil ditambahkan")
                        .build();
            }
            else
                if(repository.existsByEmailAndRoleNotIn(req.getEmail(), roleRepository.findRolesByIdGreaterThan(5)))
                    return DefaultResponse.builder()
                            .message("Email sudah terdaftar!")
                            .build();
                else
                    return DefaultResponse.builder()
                            .message("Nomor identitas sudah terdaftar!")
                            .build();
        }
        else
            return DefaultResponse.builder()
                    .message("Data tidak ditemukan")
                    .build();
    }

    @Override
    public DefaultResponse getAllCustomer(String name) {
        int id = 6;
        return DefaultResponse.builder()
                .data(repository.findUsersByRoleIsBetweenAndNameContaining(roleRepository.findRoleById(id),
                        roleRepository.findRoleById(7), name))
                .build();
    }

    @Override
    public DefaultResponse getAllEmployee(String name) {
        int id = 6;
        return DefaultResponse.builder()
                .data(repository.findUsersByRoleIsNotAndRoleIsNotAndNameContaining(roleRepository.findRoleById(6),
                        roleRepository.findRoleById(7), name))
                .build();
    }

    @Override
    public DefaultResponse getOneUser(int id) {
        return DefaultResponse.builder()
                .data(repository.findUserById(id))
                .build();
    }

    @Override
    public DefaultResponse getRoles() {
        return DefaultResponse.builder()
                .data(roleRepository.findRolesByIdGreaterThan(5))
                .build();
    }

    @Override
    public DefaultResponse getEmployeeRoles() {
        return DefaultResponse.builder()
                .data(roleRepository.findRolesByIdLessThan(6))
                .build();
    }
}
