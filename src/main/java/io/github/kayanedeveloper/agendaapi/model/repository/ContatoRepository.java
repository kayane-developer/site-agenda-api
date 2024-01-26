package io.github.kayanedeveloper.agendaapi.model.repository;

import io.github.kayanedeveloper.agendaapi.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
