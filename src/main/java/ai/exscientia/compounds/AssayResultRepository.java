package ai.exscientia.compounds;


import ai.exscientia.compounds.domain.AssayResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssayResultRepository extends JpaRepository<AssayResult, Long> {
}
