package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.model.PaidFacility;
import com.megan.webp3l.repository.PaidFacilityRepository;
import com.megan.webp3l.request.PaidFacilityRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.PaidFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaidFacilityServiceImpl implements PaidFacilityService {
    @Autowired
    private PaidFacilityRepository repository;

    @Override
    public DefaultResponse add(PaidFacilityRequest req) {
        if (!repository.existsByName(req.getName())) {
            PaidFacility paidFacility = PaidFacility.builder()
                    .name(req.getName())
                    .price(req.getPrice())
                    .status("AKTIF")
                    .build();

            return DefaultResponse.builder()
                    .data(repository.save(paidFacility))
                    .message("Data berhasil disimpan!")
                    .build();
        }
        else
            return DefaultResponse.builder()
                    .message("Nama fasilitas berbayar sudah terdaftar!")
                    .build();
    }

    @Override
    public DefaultResponse update(PaidFacilityRequest req, int id) {
        if (repository.existsById(id)) {
            int cek = repository.findByName(req.getName()).getId();
            if (!repository.existsByName(req.getName()) || cek == id) {
                PaidFacility paidFacility = PaidFacility.builder()
                        .id(id)
                        .name(req.getName())
                        .price(req.getPrice())
                        .status(req.getStatus())
                        .build();
                return DefaultResponse.builder()
                        .data(repository.save(paidFacility))
                        .message("Data berhasil diubah!")
                        .build();
            }
            else
                return DefaultResponse.builder()
                        .message("Nama fasilitas berbayar sudah terdaftar!")
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
                .data(repository.findPaidFacilityByNameContaining(name))
                .build();
    }

}
