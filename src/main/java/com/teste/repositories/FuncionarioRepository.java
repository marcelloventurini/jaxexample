package com.teste.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.teste.model.Funcionario;

public class FuncionarioRepository {
  private final static HashMap<Integer, Funcionario> funcionarios = new HashMap<>();

  public List<Funcionario> getAll() {
    return new ArrayList<Funcionario>(funcionarios.values());
  }

  public Funcionario get(final int id) {
    return funcionarios.get(id);
  }

  public void add(final Funcionario funcionario) {
    if(funcionario.getId() == 0)
      funcionario.setId(generateId(funcionarios.size() + 1));
    funcionarios.put(funcionario.getId(), funcionario);
  }

  public void edit(final Funcionario funcionario) {
    funcionarios.remove(funcionario.getId());
    funcionarios.put(funcionario.getId(), funcionario);
  }

  public void delete(final int id) {
    funcionarios.remove(id);
  }

  private int generateId(final int possible) {
    if(funcionarios.containsKey(possible))
      return generateId(possible + 1);
    return possible;
  }
}
