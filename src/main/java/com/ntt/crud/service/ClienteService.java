package com.ntt.crud.service;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;
import com.ntt.crud.Utils.Exceptions.ViaCepException;
import com.ntt.crud.Utils.Exceptions.ViaCepFormatException;
import com.ntt.crud.entity.Cliente;
import com.ntt.crud.entity.Endereco;
import com.ntt.crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        Cep cep = ViaCepClient.findCep(cliente.getCep());
        if (cliente.getEndereco() == null) {
            Endereco enderecoFake = new Endereco();
            cliente.setEndereco(enderecoFake);
            cliente.getEndereco().setBairro(cep.getBairro());
            cliente.getEndereco().setCidade(cep.getLocalidade());
            cliente.getEndereco().setCep(cep.getCep());
            cliente.getEndereco().setComplemento(cep.getComplemento());
            cliente.getEndereco().setLogradouro(cep.getLogradouro());
            cliente.getEndereco().setNumero(Integer.parseInt(cep.getIbge()));
            cliente.getEndereco().setEstado(cep.getUf());
        } else {
            if (cliente.getEndereco().getBairro() == null) {
                cliente.getEndereco().setBairro(cep.getBairro());
            }
            if (cliente.getEndereco().getCidade() == null) {
                cliente.getEndereco().setCidade(cep.getLocalidade());
            }
            if (cliente.getEndereco().getCep() == null) {
                cliente.getEndereco().setCep(cep.getCep());
            }
            if (cliente.getEndereco().getComplemento() == null) {
                cliente.getEndereco().setComplemento(cep.getComplemento());
            }
            if (cliente.getEndereco().getLogradouro() == null) {
                cliente.getEndereco().setLogradouro(cep.getLogradouro());
            }
            if (cliente.getEndereco().getNumero() == null) {
                cliente.getEndereco().setNumero(Integer.parseInt(cep.getIbge()));
            }
            if (cliente.getEndereco().getEstado() == null) {
                cliente.getEndereco().setEstado(cep.getUf());
            }

        }

        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Cliente cliente, String clienteCep) {
        Cliente client = clienteRepository.findById(cliente.getId()).get();
        if (!client.getEndereco().getCep().equals(clienteCep)) {
            Cep cep = ViaCepClient.findCep(cliente.getCep());
            cliente.getEndereco().setBairro(cep.getBairro());
            cliente.getEndereco().setCidade(cep.getLocalidade());
            cliente.getEndereco().setCep(cep.getCep());
            cliente.getEndereco().setComplemento(cep.getComplemento());
            cliente.getEndereco().setLogradouro(cep.getLogradouro());
            cliente.getEndereco().setNumero(Integer.parseInt(cep.getIbge()));
            cliente.getEndereco().setEstado(cep.getUf());

            return clienteRepository.save(cliente);
        } else {
            return clienteRepository.save(cliente);
        }
    }


    public List<Cliente> listaCliente(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public void removerPorId(Long id){
        clienteRepository.deleteById(id);
    }

    public void update(Long id, Cliente cliente){
        Cliente n = clienteRepository.findById(id).get();
    }
}