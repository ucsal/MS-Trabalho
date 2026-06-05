package professor.example.ms.DTO;

import professor.example.ms.enums.CategoriaTitulacao;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TitulacaoRequestDTO {
 
    @NotNull(message = "Categoria é obrigatória")
    private CategoriaTitulacao categoria;
 
    @NotBlank(message = "Instituição é obrigatória")
    private String instituicao;
 
    @NotBlank(message = "Curso é obrigatório")
    private String curso;
 
    @NotNull(message = "Ano de conclusão é obrigatório")
    @Min(value = 1900, message = "Ano inválido")
    @Max(value = 2100, message = "Ano inválido")
    private Integer anoConclusao;
}


