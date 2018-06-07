package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.request.FacilityRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.FacilityService;
import com.megan.webp3l.model.Facility;
import com.megan.webp3l.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    FacilityRepository repository;

    @Override
    public DefaultResponse add(FacilityRequest req) {
        if (!repository.existsByName(req.getName())) {
            Facility facility = Facility.builder()
                    .name(req.getName())
                    .status("AKTIF")
                    .build();
            return DefaultResponse.builder()
                    .data(repository.save(facility))
                    .message("Data berhasil disimpan!")
                    .build();
        }
        else
            return DefaultResponse.builder()
                .message("Nama fasilitas udah terdaftar!")
                .build();

    }

    @Override
    public DefaultResponse update(FacilityRequest req, int id) {
        if (repository.existsById(id)) {
            int cek = repository.findByName(req.getName()).getId();
            if (!repository.existsByName(req.getName()) || cek == id) {
                Facility facility = Facility.builder()
                        .id(id)
                        .name(req.getName())
                        .status(req.getStatus())
                        .build();
                return DefaultResponse.builder()
                        .data(repository.save(facility))
                        .message("Data berhasil diubah!")
                        .build();
            }
            else
                return DefaultResponse.builder()
                        .message("Nama fasilitas udah terdaftar!")
                        .build();
        }
        else
            return DefaultResponse.builder()
                    .message("Data tidak ditemukan!")
                    .build();
    }

    @Override
    public DefaultResponse showAll(String name) {
        return DefaultResponse.builder()
                .data(repository.findFacilitiesByNameContaining(name))
                .build();
    }

    @Override
    public DefaultResponse showActiveFacilities() {
        return DefaultResponse.builder()
                .data(repository.findFacilitiesByStatus("AKTIF"))
                .build();
    }

}
