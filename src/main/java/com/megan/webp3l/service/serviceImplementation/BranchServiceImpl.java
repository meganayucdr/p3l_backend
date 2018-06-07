package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.model.Branch;
import com.megan.webp3l.repository.BranchRepository;
import com.megan.webp3l.request.BranchRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository repository;

    @Override
    public DefaultResponse add(BranchRequest req) {
        if (!repository.existsByLocation(req.getLocation())) {
            Branch branch = Branch.builder()
                    .location(req.getLocation())
                    .status("AKTIF")
                    .build();
            return DefaultResponse.builder()
                    .data(repository.save(branch))
                    .message("Data berhasil disimpan!")
                    .build();
        }
        else
            return DefaultResponse.builder()
                    .message("Lokasi cabang sudah terdaftar!")
                    .build();
    }

    @Override
    public DefaultResponse update(BranchRequest req, int id) {
        if (repository.existsById(id))  {
            int cek = repository.findByLocation(req.getLocation()).getId();
            if (!repository.existsByLocation(req.getLocation()) || cek == id) {
                Branch branch = Branch.builder()
                        .id(id)
                        .location(req.getLocation())
                        .status(req.getStatus())
                        .build();
                return DefaultResponse.builder()
                        .data(repository.save(branch))
                        .message("Data berhasil diperbaharui!")
                        .build();
            }
            else return DefaultResponse.builder()
                    .message("Lokasi cabang sudah terdaftar!")
                    .build();

        }
        else return DefaultResponse.builder()
                .message("Data tidak ditemukan")
                .build();
    }

    @Override
    public DefaultResponse showAll(String location) {
        return DefaultResponse.builder()
                .data(repository.findBranchesByLocationContaining(location))
                .build();
    }

    @Override
    public DefaultResponse showActiveBranch() {
        return DefaultResponse.builder()
                .data(repository.findBranchesByStatus("AKTIF"))
                .build();
    }
}
