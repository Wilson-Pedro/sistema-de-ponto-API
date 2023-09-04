package com.wamk.sistemaponto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.projections.RegistroMinProjection;

public interface RegistroRepository extends JpaRepository<Registro, Long>{
	
	@Query(nativeQuery = true, value = """
			SELECT 
			TB_REGISTRO.id, 
			TB_REGISTRO.tipo_registro, 
			TB_REGISTRO.data_hora AS dataHora, 
			TB_REGISTRO.frequencia, 
			TB_FUNCIONARIO.nome 
			FROM TB_REGISTRO INNER JOIN TB_FUNCIONARIO 
			ON TB_REGISTRO.funcionario_id = TB_FUNCIONARIO .id
			WHERE FUNCIONARIO_ID = :id
			""")
	List<RegistroMinProjection> searchById(Long id);
}
