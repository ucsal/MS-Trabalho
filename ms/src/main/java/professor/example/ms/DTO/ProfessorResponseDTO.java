package professor.example.ms.DTO;

import professor.example.ms.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorResponseDTO {
 
    private Long id;
    private String matricula;
    private String nomeCompleto;
    private String email;
    private String telefone;
    private Long escolaId;
    private Status status;
    private LocalDateTime createdAt;
    private List<TitulacaoResponseDTO> titulacoes;
}