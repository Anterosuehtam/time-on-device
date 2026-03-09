package com.project.time_on_device.service;

import com.project.time_on_device.entity.Device;
import com.project.time_on_device.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    // CREATE (POST)
    public Device createDevice(Device device){
        return deviceRepository.save(device);
    }

    // READ (GET)
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    // UPDATE (PUT)
    public Device updateDevice(Integer id, Device deviceAtualizado) {
        Device deviceExistente = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device não encontrado!"));

        if (deviceAtualizado.getDeviceType() != null) {
            deviceExistente.setDeviceType(deviceAtualizado.getDeviceType());
        }
        if (deviceAtualizado.getName() != null) {
            deviceExistente.setName(deviceAtualizado.getName());
        }
        return deviceRepository.save(deviceExistente);
    }

    // DELETE
    public void deleteDevice(Integer id) {
        deviceRepository.deleteById(id);
    }
}
