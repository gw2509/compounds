package ai.exscientia.compounds.controller;

import ai.exscientia.compounds.AssayResultRepository;
import ai.exscientia.compounds.CompoundRepository;
import ai.exscientia.compounds.domain.Compound;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
class CompoundController {

    private final CompoundRepository compoundRepository;
    private final AssayResultRepository assayResultRepository;

    CompoundController(CompoundRepository compoundRepository, AssayResultRepository assayResultRepository) {
        this.compoundRepository = compoundRepository;
        this.assayResultRepository = assayResultRepository;
    }

    @ApiOperation(value = "Get a compound by its ID.")
    @GetMapping("/compounds/{id}")
    ResponseEntity<Compound> getById(@PathVariable Long id) {//todo call it findById
        Optional<Compound> compound = compoundRepository.findById(id);
        if (!compound.isPresent()) {
            throw new CompoundNotFoundException(id);
        }

        return ResponseEntity.ok(compound.get());
    }

    @ApiOperation(value = "Get all compounds.")
    @GetMapping("/compounds")
    public ResponseEntity<List<Compound>> getAll() {
        return ResponseEntity.ok(compoundRepository.findAll());
    }

    @ApiOperation(value = "Get all compounds. Include paging information.")
    @GetMapping("/compounds/paged")
    public ResponseEntity<Page<Compound>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(compoundRepository.findAll(pageable));
    }

    @ApiOperation(value = "Create a new compound.")
    @PostMapping("/compounds")
    Compound createNew(@RequestBody Compound newCompound) {
        return compoundRepository.save(newCompound);
    }

    @ApiOperation(value = "Replace an existing (with given ID) compound.")
    @PutMapping("/compounds/{id}")
    Compound replace(@RequestBody Compound newCompound, @PathVariable Long id) {
        return compoundRepository.findById(id)
                .map(compound -> {
                    compound.setSmiles(newCompound.getSmiles());
                    compound.setMolecularWeight(newCompound.getMolecularWeight());
                    compound.setAlogp(newCompound.getAlogp());
                    compound.setMolecularFormula(newCompound.getMolecularFormula());
                    compound.setNumRings(newCompound.getNumRings());
                    compound.setImage(newCompound.getImage());
                    return compoundRepository.save(compound);
                })
                .orElseGet(() -> {
                    throw new CompoundNotFoundException(id);
                });
    }

    @ApiOperation(value = "Delete an existing (with given ID) compound.")
    @DeleteMapping("/compounds/{id}")
    public ResponseEntity<Compound> delete(@PathVariable Long id) {
        Optional<Compound> compound = compoundRepository.findById(id);
        if (!compound.isPresent()) {
            throw new CompoundNotFoundException(id);
        }
        compoundRepository.delete(compound.get());

        return ResponseEntity.noContent().build();
    }

}