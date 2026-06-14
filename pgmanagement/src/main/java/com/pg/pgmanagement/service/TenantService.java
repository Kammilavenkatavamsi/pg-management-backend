package com.pg.pgmanagement.service;

import com.pg.pgmanagement.entity.Room;
import com.pg.pgmanagement.entity.Tenant;
import com.pg.pgmanagement.repository.RoomRepository;
import com.pg.pgmanagement.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Tenant addTenant(Long roomId, Tenant tenant) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        if (room.getOccupiedBeds() >= room.getCapacity()) {
            throw new RuntimeException("Room is already full");
        }

        tenant.setRoom(room);
        tenant.setJoiningDate(LocalDate.now());

        room.setOccupiedBeds(room.getOccupiedBeds() + 1);
        if (room.getOccupiedBeds() >= room.getCapacity()) {
            room.setStatus("FULL");
        }
        roomRepository.save(room);

        return tenantRepository.save(tenant);
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found with id: " + id));
    }

    public void deleteTenant(Long id) {
        Tenant tenant = getTenantById(id);
        Room room = tenant.getRoom();

        room.setOccupiedBeds(room.getOccupiedBeds() - 1);
        room.setStatus("AVAILABLE");
        roomRepository.save(room);

        tenantRepository.deleteById(id);
    }
}