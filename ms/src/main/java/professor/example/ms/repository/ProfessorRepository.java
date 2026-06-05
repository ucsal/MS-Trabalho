package professor.example.ms.repository;

import professor.example.ms.enums.Status;
import professor.example.ms.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.Optional;
 
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
 
    List<Professor> findAll();
 
    List<Professor> findByStatus(Status status);
 
    Optional<Professor> findByMatricula(String matricula);
 
    Optional<Professor> findByEmail(String email);
 
    boolean existsByMatricula(String matricula);
 
    boolean existsByEmail(String email);
}
