package com.zera.tarefas.services;

import com.zera.tarefas.models.TarefaModel;
import com.zera.tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    public TarefaModel criarTarefa(TarefaModel tarefaModel ){
        return tarefaRepository.save(tarefaModel);
    }

    public List<TarefaModel> buscarTodasTarefas(){
        return tarefaRepository.findAll();
    }

    public TarefaModel buscarPorId(Long id){
        return tarefaRepository.findById(id).get();
    }

    public void deletar(Long id){
        tarefaRepository.deleteById(id);
    }

    public TarefaModel atualizar(Long id, TarefaModel tarefaModel){
        TarefaModel newTarefa = tarefaRepository.findById(id).get();
        newTarefa.setDescricao(tarefaModel.getDescricao());
        newTarefa.setDataVencimento(tarefaModel.getDataVencimento());
        newTarefa.setConcluida(tarefaModel.getConcluida());
        return tarefaRepository.save(newTarefa);
    }
}
