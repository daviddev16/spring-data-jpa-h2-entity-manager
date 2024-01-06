package com.daviddev16;

import com.daviddev16.domain.cliente.Cliente;
import com.daviddev16.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(@Autowired ClienteRepository clienteRepository) {
        return args ->
        {
            System.out.println("Criando clientes:");
            clienteRepository.salvarCliente(new Cliente("David Duarte"));
            clienteRepository.salvarCliente(new Cliente("Lucas Santos"));
            clienteRepository.salvarCliente(new Cliente("Guilherme Carvalho"));

            System.out.println("Todos os clientes:");
            List<Cliente> listaClientes = clienteRepository.obterTodos();
            listaClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes:");
            listaClientes.forEach(cliente ->
            {
                cliente.setNome( cliente.getNome().toUpperCase() + " (Atualizado)" );
                clienteRepository.atualizarCliente(cliente);
            });

            System.out.println("Deletando cliente com ID = 2:");
            clienteRepository.deletarPorId(2);

            System.out.println("Todos os clientes novamente:");
            listaClientes = clienteRepository.obterTodos();
            listaClientes.forEach(System.out::println);

            System.out.println("Localizados pelo nome \"David\": "
                    + clienteRepository.obterPorNome("David"));

        };
    }



    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }

}
