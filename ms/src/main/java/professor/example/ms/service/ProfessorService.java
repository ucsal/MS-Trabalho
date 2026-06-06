package professor.example.ms.service;

import professor.example.ms.DTO.*;
import professor.example.ms.enums.Status;
import professor.example.ms.exceptions.BusinessException;
import professor.example.ms.exceptions.ResourceNotFoundException;
import professor.example.ms.model.Professor;
import professor.example.ms.model.ProfessorTitulacao;
import professor.example.ms.repository.ProfessorRepository;
import professor.example.ms.repository.ProfessorTitulacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
 
import java.util.List;
import java.util.stream.Collectors;
 
@Service
@RequiredArgsConstructor
public class ProfessorService {
 
    private final ProfessorRepository professorRepository;
    private final ProfessorTitulacaoRepository titulacaoRepository;
 
    // ─── ADMIN: Cadastrar professor ─────────────────────────────────────────
 
    @Transactional
    public ProfessorResponseDTO cadastrar(ProfessorRequestDTO dto) {
        if (professorRepository.existsByMatricula(dto.getMatricula())) {
            throw new BusinessException("Já existe um professor com a matrícula: " + dto.getMatricula());
        }
        if (professorRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Já existe um professor com o email: " + dto.getEmail());
        }
 
        Professor professor = Professor.builder()
                .matricula(dto.getMatricula())
                .nomeCompleto(dto.getNomeCompleto())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .escolaId(dto.getEscolaId())
                .status(Status.ATIVO)
                .build();
 
        return toResponse(professorRepository.save(professor));
    }
 
    // ─── ADMIN: Inativar professor (nunca excluir) ───────────────────────────
 
    @Transactional
    public ProfessorResponseDTO inativar(Long id) {
        Professor professor = buscarPorId(id);
        if (professor.getStatus() == Status.INATIVO) {
            throw new BusinessException("Professor já está inativo.");
        }
        professor.setStatus(Status.INATIVO);
        return toResponse(professorRepository.save(professor));
    }

    // ─── ADMIN: Ativar professor ────────────────────────────────────────────

    @Transactional
    public ProfessorResponseDTO ativar(Long id) {
        Professor professor = buscarPorId(id);
        if (professor.getStatus() == Status.ATIVO) {
            throw new BusinessException("Professor já está ativo.");
        }
        professor.setStatus(Status.ATIVO);
        return toResponse(professorRepository.save(professor));
    }
 
    // ─── ADMIN: Listar todos ─────────────────────────────────────────────────
 
    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }
 
    // ─── ADMIN: Listar por status ────────────────────────────────────────────
 
    public List<ProfessorResponseDTO> listarPorStatus(Status status) {
        return professorRepository.findByStatus(status)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }
 
    // ─── PROFESSOR: Adicionar titulação ─────────────────────────────────────
 
    @Transactional
    public ProfessorResponseDTO adicionarTitulacao(Long professorId, TitulacaoRequestDTO dto) {
        Professor professor = buscarPorId(professorId);
 
        ProfessorTitulacao titulacao = ProfessorTitulacao.builder()
                .professor(professor)
                .categoria(dto.getCategoria())
                .instituicao(dto.getInstituicao())
                .curso(dto.getCurso())
                .anoConclusao(dto.getAnoConclusao())
                .build();
 
        professor.getTitulacoes().add(titulacao);
        return toResponse(professorRepository.save(professor));
    }
 
    // ─── PROFESSOR: Consultar próprio cadastro ───────────────────────────────
 
    public ProfessorResponseDTO buscar(Long id) {
        return toResponse(buscarPorId(id));
    }
 
    // ─── Helpers ─────────────────────────────────────────────────────────────
 
    private Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com id: " + id));
    }
 
    private ProfessorResponseDTO toResponse(Professor p) {
        List<TitulacaoResponseDTO> titulacoes = p.getTitulacoes() == null ? new ArrayList<>() : p.getTitulacoes().stream().map(t -> TitulacaoResponseDTO.builder()
                        .id(t.getId())
                        .categoria(t.getCategoria())
                        .instituicao(t.getInstituicao())
                        .curso(t.getCurso())
                        .anoConclusao(t.getAnoConclusao())
                        .build())
                .collect(Collectors.toList());

 
        return ProfessorResponseDTO.builder()
                .id(p.getId())
                .matricula(p.getMatricula())
                .nomeCompleto(p.getNomeCompleto())
                .email(p.getEmail())
                .telefone(p.getTelefone())
                .escolaId(p.getEscolaId())
                .status(p.getStatus())
                .createdAt(p.getCreatedAt())
                .titulacoes(titulacoes)
                .build();
    }
}