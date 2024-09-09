package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.repository.LocationGuSiRepository;
import com.sist.jobgem.dto.LocationGuSiDto;
import com.sist.jobgem.entity.LocationGuSi;
import com.sist.jobgem.mapper.LocationGuSiMapper;

import java.util.List;

@Service
public class LocationGuSiService {
    @Autowired
    private LocationGuSiRepository locationGuSiRepository;

    public List<LocationGuSi> findByIdIn(List<Integer> lgIdx) {
        return locationGuSiRepository.findByIdIn(lgIdx);
    }

    public List<LocationGuSiDto> getLocGuSi(int ldIdx) {
        List<LocationGuSi> list = locationGuSiRepository.findByLdIdx(ldIdx);
        return LocationGuSiMapper.INSTANCE.toDtoList(list);
    }

    public boolean addLocGuSi(String itemName, int ldIdx) {
        LocationGuSiDto lDto = new LocationGuSiDto();
        lDto.setLgName(itemName);
        lDto.setLdIdx(ldIdx);

        LocationGuSi e = LocationGuSiMapper.INSTANCE.toEntity(lDto);

        return locationGuSiRepository.save(e) != null;
    }

    public boolean removeLocGuSi(int id) {
        try {
            locationGuSiRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean editLocGuSi(int id, String editItemName) {
        return locationGuSiRepository.editLoc(id, editItemName) > 0;
    }
}
