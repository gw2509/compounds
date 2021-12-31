package ai.exscientia.compounds.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Compound {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name="compound_id") Long id;
    private String smiles;
    private double molecularWeight;
    private double alogp;
    private String molecularFormula;
    private int numRings;
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "compound", cascade = CascadeType.ALL)
    private Set<AssayResult> assayResults = new HashSet<>();

    public Compound() {
    }

    public Compound(Long id, String smiles, double molecularWeight, double alogp, String molecularFormula,
                    int numRings, String image, Set<AssayResult> assayResults) {
        this.id = id;
        this.smiles = smiles;
        this.molecularWeight = molecularWeight;
        this.alogp = alogp;
        this.molecularFormula = molecularFormula;
        this.numRings = numRings;
        this.image = image;
        this.assayResults = assayResults;
    }

    public Long getId() {
        return id;
    }

    public String getSmiles() {
        return smiles;
    }

    public double getMolecularWeight() {
        return molecularWeight;
    }

    public double getAlogp() {
        return alogp;
    }

    public String getMolecularFormula() {
        return molecularFormula;
    }

    public int getNumRings() {
        return numRings;
    }

    public String getImage() {
        return image;
    }

    public Set<AssayResult> getAssayResults() {
        return assayResults;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    public void setMolecularWeight(double molecularWeight) {
        this.molecularWeight = molecularWeight;
    }

    public void setAlogp(double alogp) {
        this.alogp = alogp;
    }

    public void setMolecularFormula(String molecularFormula) {
        this.molecularFormula = molecularFormula;
    }

    public void setNumRings(int numRings) {
        this.numRings = numRings;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAssayResults(Set<AssayResult> assayResults) {
        this.assayResults = assayResults;
    }

    @Override
    public boolean equals(Object o) {//todo
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compound compound = (Compound) o;
        return Double.compare(compound.molecularWeight, molecularWeight) == 0 && id.equals(compound.id) && smiles.equals(compound.smiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, smiles, molecularWeight);//todo
    }

    @Override
    public String toString() {
        return "Compound{" +
                "id=" + id +
                ", smiles='" + smiles + '\'' +
                ", molecularWeight=" + molecularWeight +
                ", alogp=" + alogp +
                ", molecularFormula='" + molecularFormula + '\'' +
                ", numRings=" + numRings +
                ", image='" + image + '\'' +
                '}';
    }
}
