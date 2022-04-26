package com.teste.resources;

import java.util.List;

import com.teste.model.Funcionario;
import com.teste.repositories.FuncionarioRepository;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/funcionario")
public class FuncionarioResource {
  private FuncionarioRepository _repositorio = new FuncionarioRepository();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Funcionario> get() {
    return _repositorio.getAll();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Funcionario getById(@PathParam("id") int id) {
    return _repositorio.get(id);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(Funcionario funcionario) {
    try {
      _repositorio.add(funcionario);
      return Response.status(Response.Status.CREATED).entity(funcionario).build();
    } catch(Exception ex) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
    }
  }

  @PUT
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response put(@PathParam("id") int id, Funcionario funcionario) {
    Funcionario f = _repositorio.get(id);
    if(f == null)
      return Response.status(Response.Status.NOT_FOUND).build();
    try {
      funcionario.setId(id);
      _repositorio.edit(funcionario);
      return Response.status(Response.Status.OK).entity(funcionario).build();
    } catch(Exception ex) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
    }
  }

  @DELETE
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response delete(@PathParam("id") int id) {
    Funcionario f = _repositorio.get(id);
    if(f == null)
      return Response.status(Response.Status.NOT_FOUND).build();
    try {
      _repositorio.delete(id);
      return Response.status(Response.Status.OK).build();
    } catch(Exception ex) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
    }
  }
}
