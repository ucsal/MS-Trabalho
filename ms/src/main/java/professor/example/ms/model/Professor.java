package professor.example.ms.model;

import jakarta.persistence.*;
import lombok.*;
 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
 
@Entity
@Table(name = "professor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;
 
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
 
    @Column(name = "email", nullable = false, unique = true)
    private String email;
 
    @Column(name = "telefone")
    private String telefone;
 
    @Column(name = "escola_id", nullable = false)
    private Long escolaId;
 
    // @Enumerated(EnumType.STRING)
    // @Column(name = "status", nullable = false)
    // private Status status;
 
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
 
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfessorTitulacao> titulacoes = new ArrayList<>();
 
    // @PrePersist
    // public void prePersist() {
    //     this.createdAt = LocalDateTime.now();
    //     if (this.status == null) {
    //         this.status = Status.ATIVO;
    //     }
    // }
}