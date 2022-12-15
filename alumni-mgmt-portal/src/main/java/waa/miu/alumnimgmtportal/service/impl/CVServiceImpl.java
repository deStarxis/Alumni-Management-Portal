package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.CV;
import waa.miu.alumnimgmtportal.entity.dto.CVDto;
import waa.miu.alumnimgmtportal.repository.CVRepo;
import waa.miu.alumnimgmtportal.service.CVService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CVServiceImpl implements CVService {
    private final ModelMapper modelMapper;
    private final CVRepo cvRepo;
    @Override
    public List<CVDto> findAll() {
        List<CVDto> cvs = cvRepo.findAll().stream().filter(cv -> !cv.isDeleted()).map(cv -> modelMapper.map(cv,CVDto.class)).toList();
        return cvs;
    }

    @Override
    public CVDto addCV(CVDto cv) {
        CV cv1 = modelMapper.map(cv, CV.class);
        cvRepo.save(cv1);
        return modelMapper.map(cv1, CVDto.class);
    }

    @Override
    public CVDto updateCV(int id, CVDto cv) {
        CV cv1 = cvRepo.findById(id).orElseThrow(()->new RuntimeException("CV Not found"));
        cv1 = modelMapper.map(cv, CV.class);
        cv1.setId(id);
        cvRepo.save(cv1);
        return modelMapper.map(cv1, CVDto.class);
    }

    @Override
    public void deleteCV(int id) {
        CV cv = cvRepo.findById(id).orElseThrow(()->new RuntimeException("CV Not Found"));
        cv.setDeleted(true);
        cvRepo.save(cv);
    }

    @Override
    public CVDto findById(int id) {
        CV cv = cvRepo.findById(id).filter(cv1 -> !cv1.isDeleted()).orElseThrow(()->new RuntimeException("CV Not found"));
        return modelMapper.map(cv, CVDto.class);
    }
}
