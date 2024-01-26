package io.github.kayanedeveloper.agendaapi.controller;

import io.github.kayanedeveloper.agendaapi.dto.ContatoDTO;
import io.github.kayanedeveloper.agendaapi.model.entity.Contato;
import io.github.kayanedeveloper.agendaapi.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {

    private final ContatoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody ContatoDTO dto) {
        return service.salvar(dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deletarPorId(id);
    }

    @GetMapping
    public Page<Contato> list(@RequestParam(value = "page", defaultValue = "0") Integer pagina, @RequestParam(value = "size", defaultValue = "10") Integer tamanhoPagina) {
        return service.listarTodos(pagina, tamanhoPagina);
    }

    @PatchMapping("{id}/favorito")
    public void favorite(@PathVariable Integer id) {
        service.favoritar(id);
    }

    @PutMapping("/{id}/foto")
    public byte[] addPhoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo) {
        return service.addPhoto(id, arquivo);
    }

}
