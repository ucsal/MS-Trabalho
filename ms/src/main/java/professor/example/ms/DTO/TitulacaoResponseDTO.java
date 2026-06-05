package professor.example.ms.DTO;

import professor.example.ms.enums.CategoriaTitulacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TitulacaoResponseDTO {
 
    private Long id;
    private CategoriaTitulacao categoria;
    private String instituicao;
    private String curso;
    private Integer anoConclusao;
}