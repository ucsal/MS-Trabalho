package professor.example.ms.controller;

import professor.example.ms.DTO.*;
import professor.example.ms.enums.Status;
import professor.example.ms.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {
 
    private final ProfessorService professorService;
 
    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> cadastrar(@RequestBody @Valid ProfessorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.cadastrar(dto));
    }
 
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<ProfessorResponseDTO> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.inativar(id));
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<ProfessorResponseDTO> ativar(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.ativar(id));
    }
 
    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(professorService.listarTodos());
    }
 
    @GetMapping("/status/{status}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProfessorResponseDTO>> listarPorStatus(@PathVariable Status status) {
        return ResponseEntity.ok(professorService.listarPorStatus(status));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.buscar(id));
    }
 
    @PostMapping("/{id}/titulacoes")
    public ResponseEntity<ProfessorResponseDTO> adicionarTitulacao(
            @PathVariable Long id,
            @RequestBody @Valid TitulacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.adicionarTitulacao(id, dto));
    }
}