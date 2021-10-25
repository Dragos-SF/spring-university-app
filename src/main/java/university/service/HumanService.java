package university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.repository.Human;
import university.repository.HumanRepository;
import university.service.DTO.HumanDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HumanService {

    private final HumanRepository humanRepository;

    @Autowired
    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public List<HumanDTO> getAllHumans() {
        final List<Human> all = this.humanRepository.findAll();
        return all.stream()
                .map(this::mapToHumanDto)
                .collect(Collectors.toList());
    }

    public HumanDTO mapToHumanDto(Human humanEntity) {
        HumanDTO createdHuman  = new HumanDTO();
        createdHuman.setId(humanEntity.getId());
        createdHuman.setCnp(humanEntity.getCnp());
        createdHuman.setFirstname(humanEntity.getFirstname());
        createdHuman.setLastname(humanEntity.getLastname());
        return createdHuman;
    }

    public HumanDTO getHumanById(Long humanId) {
           Optional<Human> humanEntity = this.humanRepository.findById(humanId);
           Human human = humanEntity.orElseThrow(() -> new RuntimeException("Human not found"));
           return this.mapToHumanDto(human);


    }

    public void createOrUpdateHuman(HumanDTO toCreate) {
        Human createOrUpdateMe = new Human();
        createOrUpdateMe.setId(toCreate.getId());
        createOrUpdateMe.setFirstname(toCreate.getFirstname());
        createOrUpdateMe.setLastname(toCreate.getLastname());
        createOrUpdateMe.setCnp((toCreate.getCnp()));
        this.humanRepository.save(createOrUpdateMe);
    }

    public void deleteHumanById(Long humanIdToDelete) {
        this.humanRepository.deleteById(humanIdToDelete);
    }

}
