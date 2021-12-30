package ai.exscientia.compounds.controller;

public class CompoundNotFoundException extends RuntimeException {
    public CompoundNotFoundException(Long id) {
        super("Could not find compound " + id);
    }
}