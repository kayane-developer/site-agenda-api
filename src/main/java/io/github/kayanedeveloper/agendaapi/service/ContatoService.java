package io.github.kayanedeveloper.agendaapi.service;

import io.github.kayanedeveloper.agendaapi.dto.ContatoDTO;
import io.github.kayanedeveloper.agendaapi.model.entity.Contato;
import io.github.kayanedeveloper.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository repository;

    public Contato salvar(ContatoDTO dto) {
        final var contato = new Contato();
        contato.setNome(dto.getNome());
        contato.setEmail(dto.getEmail());
        contato.setFavorito(dto.getFavorito());

        return repository.save(contato);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

    public Page<Contato> listarTodos(Integer pagina, Integer tamanhoPagina) {
        final var sort = Sort.by(Sort.Direction.ASC, "nome");
        final var pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);
        return repository.findAll(pageRequest);
    }

    public void favoritar(Integer id) {
        final var contato = repository.findById(id);
        contato.ifPresent(c -> {
            final var favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }

    public byte[] addPhoto(Integer id, Part arquivo) {
        final var contato = repository.findById(id);
        return contato.map(c -> {
            try {
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes);
                c.setFoto(bytes);
                repository.save(c);
                is.close();
                return bytes;
            } catch (IOException e) {
                return null;
            }
        }).orElse(null);

    }

}
