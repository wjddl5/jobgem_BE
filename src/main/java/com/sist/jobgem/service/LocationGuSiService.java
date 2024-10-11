package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.repository.LocationBridgeRepository;
import com.sist.jobgem.repository.LocationGuSiRepository;
import com.sist.jobgem.dto.LocationGuSiDto;
import com.sist.jobgem.entity.LocationGuSi;
import com.sist.jobgem.mapper.LocationGuSiMapper;

import java.util.List;

@Service
public class LocationGuSiService {
    @Autowired
    private LocationGuSiRepository locationGuSiRepository;

    @Autowired
    private LocationBridgeRepository locationBridgeRepository;

    public List<LocationGuSiDto> getLocGuSi(int ldIdx) {
        List<LocationGuSi> list = locationGuSiRepository.findByLdIdx(ldIdx);
        return LocationGuSiMapper.INSTANCE.toDtoList(list);
    }

    public boolean addLocGuSi(String itemName, int ldIdx) {
        LocationGuSiDto lDto = new LocationGuSiDto();
        Integer i = locationGuSiRepository.findByLgName(itemName);

        if (i == null) {
            lDto.setLgName(itemName);
            lDto.setLdIdx(ldIdx);
            LocationGuSi e = LocationGuSiMapper.INSTANCE.toEntity(lDto);
            return locationGuSiRepository.save(e) != null;

        } else {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteLocGuSi(int id) {
        try {
            locationBridgeRepository.deleteByLgIdx(id);
            locationGuSiRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateLocGuSi(int id, String editItemName) {
        String itemName = editItemName;
        Integer i = locationGuSiRepository.findByLgName(itemName);

        if (i == null) {
            return locationGuSiRepository.updateLoc(id, editItemName) > 0;
        } else {
            return false;
        }
    }
}
