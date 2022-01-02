package ai.exscientia.compounds.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AssayResult {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    Long id;

    private String target;
    private String result;
    private String operator;
    private double value;
    private String unit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "compound_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Compound compound;

    public AssayResult() {
    }

    public AssayResult(Long id, String target, String result, String operator, double value,
                       String unit, Compound compound) {
        this.id = id;
        this.target = target;
        this.result = result;
        this.operator = operator;
        this.value = value;
        this.unit = unit;
        this.compound = compound;
    }

    public Long getId() {
        return id;
    }

    public String getTarget() {
        return target;
    }

    public String getResult() {
        return result;
    }

    public String getOperator() {
        return operator;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public Compound getCompound() {
        return compound;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCompound(Compound compound) {
        this.compound = compound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssayResult that = (AssayResult) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AssayResult{" +
                "id=" + id +
                ", target='" + target + '\'' +
                ", result='" + result + '\'' +
                ", operator='" + operator + '\'' +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                '}';
    }
}
