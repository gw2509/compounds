package ai.exscientia.compounds.controller;

import ai.exscientia.compounds.AssayResultRepository;
import ai.exscientia.compounds.CompoundRepository;
import ai.exscientia.compounds.domain.AssayResult;
import ai.exscientia.compounds.domain.Compound;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
class AssayResultController {

    private final AssayResultRepository assayResultRepository;
    private final CompoundRepository compoundRepository;

    AssayResultController(AssayResultRepository assayResultRepository, CompoundRepository compoundRepository) {
        this.assayResultRepository = assayResultRepository;
        this.compoundRepository = compoundRepository;
    }

    @ApiOperation(value = "Get an assay result by its ID.")
    @GetMapping("/assayresults/{id}")
    public ResponseEntity<AssayResult> getById(@PathVariable Long id) {
        Optional<AssayResult> assayResult = assayResultRepository.findById(id);
        if (!assayResult.isPresent()) {
            throw new AssayResultNotFoundException(id);
        }

        return ResponseEntity.ok(assayResult.get());
    }

    @ApiOperation(value = "Get all assay results.")
    @GetMapping("/assayresults")
    public ResponseEntity<List<AssayResult>> getAll() {
        return ResponseEntity.ok(assayResultRepository.findAll());
    }

    @ApiOperation(value = "Create a new assay result.")
    @PostMapping("/assayresults")
    public ResponseEntity<AssayResult> createNew(@RequestBody AssayResult assayResult) {
        Optional<Compound> compound = compoundRepository.findById(assayResult.getCompound().getId());
        if (!compound.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        assayResult.setCompound(compound.get());

        AssayResult savedAssayResult = assayResultRepository.save(assayResult);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAssayResult.getId()).toUri();

        return ResponseEntity.created(location).body(savedAssayResult);
    }

    @ApiOperation(value = "Replace an existing (with given ID) assay result.")
    @PutMapping("/assayresults/{id}")
    AssayResult replace(@RequestBody AssayResult newAssayResult, @PathVariable Long id) {
        return assayResultRepository.findById(id)
                .map(assayResult -> {
                    assayResult.setTarget(newAssayResult.getTarget());
                    assayResult.setResult(newAssayResult.getResult());
                    assayResult.setOperator(newAssayResult.getOperator());
                    assayResult.setValue(newAssayResult.getValue());
                    assayResult.setUnit(newAssayResult.getUnit());
                    return assayResultRepository.save(assayResult);
                })
                .orElseGet(() -> {
                    throw new AssayResultNotFoundException(id);
                });
    }

    @ApiOperation(value = "Delete an existing (with given ID) assay result.")
    @DeleteMapping("assayresults/{id}")
    public ResponseEntity<AssayResult> delete(@PathVariable Long id) {
        Optional<AssayResult> assayResult = assayResultRepository.findById(id);
        if (!assayResult.isPresent()) {
            throw new AssayResultNotFoundException(id);
        }
        assayResultRepository.delete(assayResult.get());

        return ResponseEntity.noContent().build();
    }

}