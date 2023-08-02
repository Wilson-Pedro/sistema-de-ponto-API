package com.wamk.sistemaponto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wamk.sistemaponto.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long>{

	@Query(nativeQuery = true, value = """
			SELECT * FROM TB_REGISTRO WHERE FUNCIONARIO_ID = :funciorarioId
			""")
	List<Registro> searchById(Long funciorarioId);
}
