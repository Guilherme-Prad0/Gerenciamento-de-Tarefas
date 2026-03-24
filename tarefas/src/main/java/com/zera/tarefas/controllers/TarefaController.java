package com.zera.tarefas.controllers;

import com.zera.tarefas.models.TarefaModel;
import com.zera.tarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaModel> criarTarefa(@RequestBody TarefaModel tarefa){
        TarefaModel request = tarefaService.criarTarefa(tarefa);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefa.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<TarefaModel>> findAll(){
        List<TarefaModel> request = tarefaService.buscarTodasTarefas();
        return ResponseEntity.ok().body(request);
    }

    @DeleteMapping
    public ResponseEntity<?> deletar (@PathVariable Long id){
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public TarefaModel buscarPorId(@PathVariable Long id){
        return tarefaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaModel> atualizarCliente(@PathVariable Long id, @RequestBody TarefaModel tarefa){
        TarefaModel model = tarefaService.atualizar(id, tarefa);
        return ResponseEntity.ok().body(model);
    }
}
