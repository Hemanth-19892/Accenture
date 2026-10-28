package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        schedule.setEmployees(employeeRepository.findAllById(employeeIds));
        schedule.setPets(petRepository.findAllById(petIds));
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId) {
        return scheduleRepository.findByPetsId(petId);
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        return scheduleRepository.findByEmployeesId(employeeId);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {
        List<Pet> pets = customerRepository.findById(customerId).orElseThrow().getPets();
        return pets.stream()
                .flatMap(p -> scheduleRepository.findByPetsId(p.getId()).stream())
                .distinct()
                .collect(Collectors.toList());
    }
}