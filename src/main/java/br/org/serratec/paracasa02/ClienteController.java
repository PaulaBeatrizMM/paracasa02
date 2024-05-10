package br.org.serratec.paracasa02;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Controller
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienterepo;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> obterTodos() {
		return ResponseEntity.ok(clienterepo.findAll());
				
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente>  obterPorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienterepo.findById(id);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	
	}
	
	@PostMapping
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		clienterepo.save(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterarDadosCliente(@PathVariable Long id, @RequestBody Cliente clienteAlterado) {
		if (clienterepo.existsById(id)) {
			clienteAlterado.setId(id);
			clienterepo.save(clienteAlterado);	
			return ResponseEntity.ok(clienteAlterado);
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
		clienterepo.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	

}

