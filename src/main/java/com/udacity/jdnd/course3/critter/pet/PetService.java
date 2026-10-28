package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(Pet pet, Long ownerId) {
        Customer owner = customerRepository.findById(ownerId).orElseThrow();
        pet.setCustomer(owner);
        pet = petRepository.save(pet);
        owner.getPets().add(pet);
        customerRepository.save(owner);
        return pet;
    }

    public Pet getPet(Long id) {
        return petRepository.findById(id).orElseThrow();
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findByCustomerId(ownerId);
    }
}