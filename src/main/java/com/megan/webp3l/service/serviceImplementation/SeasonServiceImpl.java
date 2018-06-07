package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.model.Season;
import com.megan.webp3l.repository.BranchRepository;
import com.megan.webp3l.repository.SeasonRepository;
import com.megan.webp3l.request.SeasonRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonServiceImpl implements SeasonService {
    @Autowired
    private SeasonRepository repository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public DefaultResponse add(SeasonRequest req) {
        Season season = Season.builder()
                .name(req.getName())
                .percentage(req.getPercentage())
                .start_date(req.getStart_date())
                .finish_date(req.getFinish_date())
                .status("AKTIF")
                .branch(branchRepository.findBranchById(req.getBranch()))
                .build();

        return DefaultResponse.builder()
                .data(repository.save(season))
                .message("Data berhasil disimpan")
                .build();
    }

    @Override
    public DefaultResponse update(SeasonRequest req, int id) {
        if (repository.existsById(id))  {
            Season season = Season.builder()
                    .id(id)
                    .name(req.getName())
                    .percentage(req.getPercentage())
                    .start_date(req.getStart_date())
                    .finish_date(req.getFinish_date())
                    .status(req.getStatus())
                    .branch(branchRepository.findBranchById(req.getBranch()))
                    .build();

            return DefaultResponse.builder()
                    .data(repository.save(season))
                    .message("Data berhasil diperbaharui")
                    .build();
        }
        else
            return DefaultResponse.builder()
                    .message("Data tidak ditemukan")
                    .build();
    }

    @Override
    public DefaultResponse showAll(String name) {
        return DefaultResponse.builder()
                .data(repository.findSeasonsByNameContaining(name))
                .build();
    }
}
