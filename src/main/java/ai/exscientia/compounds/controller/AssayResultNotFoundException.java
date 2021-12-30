package ai.exscientia.compounds.controller;

public class AssayResultNotFoundException extends RuntimeException {
    public AssayResultNotFoundException(Long id) {
        super("Could not find assay result " + id);
    }
}
