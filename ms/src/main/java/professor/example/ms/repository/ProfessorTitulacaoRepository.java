package professor.example.ms.repository;

import professor.example.ms.model.ProfessorTitulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface ProfessorTitulacaoRepository extends JpaRepository<ProfessorTitulacao, Long> {
 
    List<ProfessorTitulacao> findByProfessorId(Long professorId);
}
