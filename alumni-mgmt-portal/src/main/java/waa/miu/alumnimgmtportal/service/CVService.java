package waa.miu.alumnimgmtportal.service;

import waa.miu.alumnimgmtportal.entity.dto.CVDto;

import java.util.List;

public interface CVService {
    List<CVDto> findAll();
    CVDto addCV(CVDto cv);
    CVDto updateCV(int id, CVDto cv);
    void deleteCV(int id);
    CVDto findById(int id);
}
