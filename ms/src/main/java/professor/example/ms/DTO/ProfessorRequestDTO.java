package professor.example.ms.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorRequestDTO {
 
    @NotBlank(message = "Matrícula é obrigatória")
    private String matricula;
 
    @NotBlank(message = "Nome completo é obrigatório")
    private String nomeCompleto;
 
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;
 
    private String telefone;
 
    @NotNull(message = "Escola é obrigatória")
    private Long escolaId;
}