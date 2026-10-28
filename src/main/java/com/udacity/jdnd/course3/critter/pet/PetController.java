package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO dto) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(dto, pet, "id");
        return toDTO(petService.savePet(pet, dto.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return toDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService.getPets().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwner(ownerId).stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    private PetDTO toDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        BeanUtils.copyProperties(pet, dto);
        dto.setOwnerId(pet.getCustomer().getId());
        return dto;
    }
}