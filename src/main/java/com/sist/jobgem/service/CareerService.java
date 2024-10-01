package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.repository.CareerRepository;
import com.sist.jobgem.repository.CareersBridgeRepository;
import com.sist.jobgem.dto.CareerDto;
import com.sist.jobgem.entity.Career;
import com.sist.jobgem.entity.CareersBridge;
import com.sist.jobgem.mapper.CareerMapper;

import java.util.List;

@Service
public class CareerService {
    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private CareersBridgeRepository careersBridgeRepository;

    public List<CareerDto> getCar() {
        List<Career> list = careerRepository.findAll();
        return CareerMapper.INSTANCE.toDtoList(list);
    }

    public boolean addCar(String itemName) {
        CareerDto eDto = new CareerDto();
        eDto.setCrName(itemName);

        Career e = CareerMapper.INSTANCE.toEntity(eDto);

        return careerRepository.save(e) != null;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCar(int id) {
        try {
            careersBridgeRepository.deleteByCrIdx(id);
            careerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateCar(int id, String editItemName) {
        return careerRepository.updateCar(id, editItemName) > 0;
    }
}
