package ai.exscientia.compounds;


import ai.exscientia.compounds.domain.Compound;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompoundRepository extends JpaRepository<Compound, Long> {
}
