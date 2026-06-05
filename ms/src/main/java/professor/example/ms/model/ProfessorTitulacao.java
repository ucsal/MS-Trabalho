package professor.example.ms.model;

import jakarta.persistence.*;
import lombok.*;
import professor.example.ms.enums.CategoriaTitulacao;
 
@Entity
@Table(name = "professor_titulacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorTitulacao {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaTitulacao categoria;
 
    @Column(name = "instituicao", nullable = false)
    private String instituicao;
 
    @Column(name = "curso", nullable = false)
    private String curso;
 
    @Column(name = "ano_conclusao", nullable = false)
    private Integer anoConclusao;
}
